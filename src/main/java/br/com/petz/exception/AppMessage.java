package br.com.petz.exception;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class AppMessage {
	
	private String message;
}
