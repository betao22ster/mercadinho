package br.com.mercadinho;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.mercadinho.utils.json.ExceptionJson;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionJson>  tratarErro(MethodArgumentNotValidException e, HttpServletResponse response){
		
		ExceptionJson json = new ExceptionJson();
		json.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		
	    return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionJson> tratarErro(Exception e, HttpServletResponse response){
		
		ExceptionJson json = new ExceptionJson();
		json.setMessage(e.getMessage());
		
		return new ResponseEntity<ExceptionJson>(json, HttpStatus.BAD_REQUEST);
	}
}
