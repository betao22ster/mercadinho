package br.com.mercadinho.service.produto;

import org.springframework.web.multipart.MultipartFile;

import br.com.mercadinho.utils.exception.GravacaoImagemException;

public interface IncluirFotoProdutoService {

	void incluir(Long idProduto, MultipartFile arquivo) throws GravacaoImagemException;

}
