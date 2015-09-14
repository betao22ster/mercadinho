package br.com.mercadinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Map;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import br.com.mercadinho.service.produto.AlterarProdutoService;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;
import br.com.mercadinho.utils.json.ProdutoJson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@IntegrationTest({"server.port:0", "security.user.password:foo", "security.user.name:marcelo@com"})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlterarProdutoTest  {
	
	@Value("${local.server.port}")
	private int port;
	
	private String host;
	
	private RestTemplate template = new TestRestTemplate("marcelo@com", "foo");

	@Autowired
	private AlterarProdutoService alterarProdutoService;
	
	@Before
	public void init(){
		host = "http://localhost:"	+ port;
	}
	
	@Test
	public void produtoJaExiste() {
		
		ProdutoEntity produto = new ProdutoEntity();
		produto.setId(2L);
		produto.setNome("Leite");
		
		try{
			alterarProdutoService.alterar(produto);
			fail();
		}catch(RegraNegocioException ex){
			assertEquals("O nome do produto já existe.", ex.getMessage());
		}
	}
	
	@Test
	public void produtoNulo() {
		
		ProdutoEntity produto = null;
		
		try{
			alterarProdutoService.alterar(produto);
			fail();
		}catch(RegraNegocioException ex){
			assertEquals("Produto é obrigatório.", ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void produtoJaExisteController(){
	
		ProdutoJson produto = new ProdutoJson();
		produto.setId(2L);
		produto.setNome("Leite");
		
		ResponseEntity<Object> response = template.postForEntity(host + "/produto/alterar", produto, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		Map<String, String> erro = (Map<String, String>) response.getBody();
		assertNotNull(erro);
		assertEquals(erro.get("message"), "O nome do produto já existe.");
	}
	
	@Test
	public void produtoGravadoController(){
	
		ProdutoJson produto = new ProdutoJson();
		produto.setId(1L);
		produto.setNome("Feijão novo");
		
		ResponseEntity<ProdutoJson> response = template.postForEntity(host + "/produto/alterar", produto, ProdutoJson.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals("Feijão novo", response.getBody().getNome());
	}
	
	
	
	
	
	
	
	
	
	
}
