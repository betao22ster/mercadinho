package br.com.mercadinho.utils.exception;

/**
 * 
 * Exception para ser usada em regras de negócio.
 * 
 * @author marcelo
 *
 */
public class RegraNegocioException extends Exception {

	private static final long serialVersionUID = -2283528645123727022L;

	public RegraNegocioException(String msg) {
		super(msg);
	}
	
}
