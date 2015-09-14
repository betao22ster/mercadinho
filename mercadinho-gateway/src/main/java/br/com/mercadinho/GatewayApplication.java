package br.com.mercadinho;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * 
 * Classe principal que vai startar o servidor, setar os códigos de login.
 * 
 * @author marcelo
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@EnableRedisHttpSession
@EnableZuulProxy
public class GatewayApplication {

	@RequestMapping("/user")
	@ResponseBody
	public Map<String, Object> user(Principal user) {
		return Collections
				.<String, Object> singletonMap("name", user.getName());
	}

	@RequestMapping("/login")
	public String login() {
		return "forward:/";
	}
	
	@RequestMapping("/token")
	public String token(Principal principal) {
		return "forward:/";
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/**
	 * 
	 * Definido aqui o código para login e buscar os dados no banco
	 * 
	 * @author marcelo
	 *
	 */
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends
			WebSecurityConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		@Autowired
		public void globalUserDetails(AuthenticationManagerBuilder auth)
				throws Exception {

			auth.jdbcAuthentication()
					.dataSource(dataSource)
					.usersByUsernameQuery(
							"select email, senha, fl_habilitado from usuario where email = ? ")
					.authoritiesByUsernameQuery(
							"select email, role from usuario_perm where email = ? ");

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// algumas telas que estou dando permissão para abrir sem estar
			// logado.
			String[] permis = new String[] { "/index.html", "/login",
					"/reguser.html", "/logar.html", "/", "/template/**", "/upload/**",
					"/scripts/**", "/fonts/**", "/styles/**" };

			http.httpBasic().and().logout().and()
					// setando as permissoes
					.authorizeRequests().antMatchers(permis).permitAll()

					.anyRequest().authenticated().and()
					// controle de token
					.csrf().disable();
//					csrfTokenRepository(csrfTokenRepository()).and()
//					.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);

		}

		private Filter csrfHeaderFilter() {

			return new OncePerRequestFilter() {

				@Override
				protected void doFilterInternal(HttpServletRequest request,
						HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {

					CsrfToken csrf = (CsrfToken) request
							.getAttribute(CsrfToken.class.getName());

					if (csrf != null) {
						Cookie cookie = WebUtils.getCookie(request,
								"XSRF-TOKEN");
						String token = csrf.getToken();

						if (cookie == null || token != null
								&& !token.equals(cookie.getValue())) {
							cookie = new Cookie("XSRF-TOKEN", token);
							cookie.setPath("/");
							response.addCookie(cookie);

						}

						response.setHeader("X-CSRF-HEADER",
								csrf.getHeaderName());
						response.setHeader("X-CSRF-PARAM",
								csrf.getParameterName());
						response.setHeader("X-CSRF-TOKEN", csrf.getToken());

					}
					filterChain.doFilter(request, response);

				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
	}

}
