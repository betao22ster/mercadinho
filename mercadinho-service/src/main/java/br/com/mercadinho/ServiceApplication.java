package br.com.mercadinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
    
    
    /**
     * 
     * Criando a regra de acesso.
     * 
     * @author marcelo
     *
     */
    @Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.httpBasic()
					.and()
					.authorizeRequests()
					.anyRequest().hasAnyRole("USER").and().csrf()
					.disable();
			;
			// @formatter:on
		}
	}
    
}
