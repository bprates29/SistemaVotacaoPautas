package br.com.sicredi.desafiopauta.exception;

import lombok.Getter;

import java.util.List;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -5908498177085520161L;

	private static final String  MESSAGE = "Requisicao mal formatada";
	
	@Getter
	private List<FieldError> validationErrors;
	
	public BadRequestException() {
		super(MESSAGE);
	}
	
	public BadRequestException(List<FieldError> validationErrors) {
		super(MESSAGE);
		this.validationErrors = validationErrors;
	}
}