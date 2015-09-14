package br.com.mercadinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import br.com.mercadinho.service.produto.ExcluirProdutoService;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;
import br.com.mercadinho.utils.json.ProdutoJson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@IntegrationTest({ "server.port:0", "security.user.password:foo",
		"security.user.name:marcelo@com" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExcluirProdutoTest {

	@Value("${local.server.port}")
	private int port;

	private String host;

	private RestTemplate template = new TestRestTemplate("marcelo@com", "foo");

	@Autowired
	private ExcluirProdutoService excluirProdutoService;

	@Before
	public void init() {
		host = "http://localhost:" + port;
	}

	@Test
	public void produtoNulo() {

		ProdutoEntity produto = null;

		try {
			excluirProdutoService.excluir(produto);
			fail();
		} catch (RegraNegocioException ex) {
			assertEquals("Produto é obrigatório.", ex.getMessage());
		}
	}

	@Test
	public void produtoExcluidoController() {

		ProdutoJson produto = new ProdutoJson();
		produto.setId(4L);
		produto.setNome("Leite excluir");

		ResponseEntity<ProdutoJson> response = template.postForEntity(host
				+ "/produto/excluir", produto, ProdutoJson.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

}
