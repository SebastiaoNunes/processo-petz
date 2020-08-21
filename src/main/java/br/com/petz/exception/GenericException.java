package br.com.petz.exception;

import java.util.ArrayList;
import java.util.List;

public class GenericException  extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AppMessage> messages;
	
	public GenericException(AppMessage menssage) {
		if (messages == null) {
			messages = new ArrayList<AppMessage>();
		}
		
		messages.add(menssage);
	}
	
	public List<AppMessage> getErros() {
		return messages;
	}
}
