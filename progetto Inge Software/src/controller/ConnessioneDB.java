package controller;
import java.sql.*;

public class ConnessioneDB {
	private Connection c;
	
	public ConnessioneDB(String url, String nome, String password) throws Exception{
		c=DriverManager.getConnection(url, nome, password);
	}
	
	public Connection getConnessione() {
		return c;
	}
	
	public void chiudiConnessione()throws Exception {
		c.close();
	}
}
	
