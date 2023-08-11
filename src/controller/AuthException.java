package controller;

public class AuthException extends Exception {

	public AuthException() {
		System.out.println("Utente non autenticato");
	}
}
