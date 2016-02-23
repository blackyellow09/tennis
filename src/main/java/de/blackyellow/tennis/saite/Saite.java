package de.blackyellow.tennis.saite;

import java.io.Serializable;

public class Saite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "name";

	public static final String MARKE = "marke";

	public static final String BEZEICHNUNG = "bezeichnung";

	public static final String TYP = "typ";
	
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

	public Saite() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarke() {
		return marke != null ? marke : "";
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getBezeichnung() {
		return bezeichnung != null ? bezeichnung : "";
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getTyp() {
		return typ != null ? typ : "";
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getName()
	{
		return marke + " " + bezeichnung;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
