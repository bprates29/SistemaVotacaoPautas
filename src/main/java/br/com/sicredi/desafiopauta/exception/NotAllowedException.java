package br.com.sicredi.desafiopauta.exception;

public class NotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = 8848112773936020205L;
	
	private static final String  MESSAGE = "Não permitido!";

	public NotAllowedException() {
		super(MESSAGE);
	}

	public NotAllowedException(String message) {
		super(message);
	}
	
	public NotAllowedException(Throwable throwable) {
		super(MESSAGE,throwable);
	}
}