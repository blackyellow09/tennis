package de.blackyellow.tennis.person;

import java.io.Serializable;

public class Kunde implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -636821238712611505L;

	public static final String VORNAME = "vorname";

	public static final String NACHNAME = "nachname";

	public static final String NAME = "name";

	private int kundennummer;
	
	private String vorname;
	
	private String nachname;
	
	private String name;
	
	public Kunde(int kundennummer, String vorname, String nachname) {
		this.kundennummer = kundennummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.name = vorname + " " + nachname;
	}
	public int getKundennummer() {
		return kundennummer;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String getName()
	{
		return vorname + " " + nachname;
	}
	
}
