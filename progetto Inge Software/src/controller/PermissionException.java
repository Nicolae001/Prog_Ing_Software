package controller;

public class PermissionException extends Exception{

	public PermissionException() {
		System.out.println("Utente non autorizzato");
	}
}
