package br.com.cadastro.usuario.fila.config.exceptions;

public class UserPermissionsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserPermissionsException() {
		super();
		
	}

	public UserPermissionsException(String message) {
		super(message);
		
	}
}