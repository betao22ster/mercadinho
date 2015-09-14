package br.com.mercadinho.service.principal;

import java.util.List;

import br.com.mercadinho.utils.json.PrincipalListaJson;

public interface PrincipalService {

	List<PrincipalListaJson> carregarLista();

}
