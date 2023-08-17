package model;
import java.util.Set;
import java.time.LocalDate;
import java.util.Collections;

public class GiorniFestivi {

	private static Set<LocalDate> dateFestive= Collections.emptySet();
	
	private GiorniFestivi() {}
	
	public static void aggiungi(LocalDate data) {
		dateFestive.add(data);
	}
	
	public static Set<LocalDate> getDate(){
		return dateFestive;
	}
	
	public static void rimuovi(LocalDate data) {
		dateFestive.remove(data);
	}
}
