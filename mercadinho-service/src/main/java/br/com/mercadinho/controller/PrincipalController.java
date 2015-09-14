package br.com.mercadinho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.service.principal.PrincipalService;
import br.com.mercadinho.utils.json.PrincipalListaJson;

@RestController
@RequestMapping("/principal")
public class PrincipalController {

	@Autowired
	private PrincipalService principalService;
	
	@RequestMapping(value = "/carregar", method = RequestMethod.GET)
	public List<PrincipalListaJson> carregarLista(){
		return principalService.carregarLista();
	}
	
}
