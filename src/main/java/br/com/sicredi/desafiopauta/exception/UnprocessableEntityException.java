package br.com.sicredi.desafiopauta.exception;

import lombok.Getter;

import java.util.List;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = 4491207973507803550L;

	private static final String  MESSAGE = "Existem erros de validacao no request enviado";
	
	@Getter
	private List<FieldError> validationErrors;
	
	public UnprocessableEntityException(List<FieldError> validationErrors) {
		super(MESSAGE);
		this.validationErrors = validationErrors;
	}
}