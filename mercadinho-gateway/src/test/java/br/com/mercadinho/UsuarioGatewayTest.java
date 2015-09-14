package br.com.mercadinho;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import br.com.mercadinho.GatewayApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GatewayApplication.class)
@IntegrationTest({ "server.port:0", "security.user.password:foo",
		"security.user.name:marcelo@com" })
@WebAppConfiguration
@ImportResource("classpath:usuario.hbm.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioGatewayTest {

	@Value("${local.server.port}")
	private int port;

	private String host;

	private RestTemplate template = new TestRestTemplate();// ("marcelo@com",
															// "foo");
	@Before
	public void init() {
		host = "http://localhost:" + port;
	}

	@Test
	public void teste(){
		
	}
	
}
