package de.blackyellow.tennis;

import java.io.Serializable;

public class Saite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "name";
	
	private int id;
	private String marke;
	private String bezeichnung;
	private String typ;

	private String name;

	public Saite(int id, String marke, String bezeichnung, String typ) {
		this.id = id;
		this.marke = marke;
		this.bezeichnung = bezeichnung;
		this.typ = typ;
		this.name = marke + " " + bezeichnung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getName()
	{
		return marke + " " + bezeichnung;
	}
}
