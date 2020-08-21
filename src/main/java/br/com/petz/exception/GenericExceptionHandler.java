package br.com.petz.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.petz.dto.Response;

@ControllerAdvice
public class GenericExceptionHandler {
	
	@ExceptionHandler(value = { GenericException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		GenericException pamcaryException = (GenericException) ex;
		
		List<String> messages = pamcaryException.getErros().stream()
				.map(erro -> erro.getMessage())
				.collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(Response.create(messages));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> invalidInput(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		
		List<String> messages = result.getAllErrors().stream()
				.map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity
				.badRequest()
				.body(Response.create(messages));
	}
}
