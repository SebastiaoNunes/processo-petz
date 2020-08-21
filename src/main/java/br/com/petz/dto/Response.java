package br.com.petz.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Response<T> {
	
	private T result;
	private List<String> messages;
	
	public static <T> Response<T> create(T entity, String mensagem) {
		Response<T> response = new Response<T>();
		response.result = entity;
		response.messages = Arrays.asList(mensagem);
		return response;
	}
	
	public static <T> Response<T> create(String mensagem) {
		Response<T> response = new Response<T>();
		response.messages = Arrays.asList(mensagem);
		return response;
	}
	
	public static <T> Response<T> create(List<String> messages) {
		Response<T> response = new Response<T>();
		response.messages = messages;
		return response;
	}
}
