package br.com.sicredi.desafiopauta.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FieldError {
	
	private String name;
	private String error;

}