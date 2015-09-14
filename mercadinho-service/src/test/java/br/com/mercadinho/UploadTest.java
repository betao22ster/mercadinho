package br.com.mercadinho;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.mercadinho.service.produto.IncluirProdutoService;
import br.com.mercadinho.utils.json.ProdutoJson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@IntegrationTest({"server.port:0", "security.user.password:foo", "security.user.name:marcelo@com"})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UploadTest {

	@Value("${local.server.port}")
	private int port;
	
	private String host;
	
	private RestTemplate template = new TestRestTemplate("marcelo@com", "foo");

	@Autowired
	private IncluirProdutoService incluirProdutoService;
	
	@Before
	public void init(){
		host = "http://localhost:"	+ port;
	}

	@Test
	public void testA_uploadPost() {
		
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("id", "1");
		parts.add("file", new FileSystemResource("/home/marcelo/Downloads/foto1.jpg"));
		
		ResponseEntity<ProdutoJson> response = template.postForEntity(host + "/fotos/upload", parts, ProdutoJson.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	public void testB_consultarLista(){
		
		ResponseEntity<Object> response = template.getForEntity(host + "/fotos/findImagens/1", Object.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
}
