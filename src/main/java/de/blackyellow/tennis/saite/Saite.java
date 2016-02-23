package de.blackyellow.tennis.saite;

import java.io.Serializable;
import java.math.BigDecimal;

public class Saite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "name";

	public static final String MARKE = "marke";

	public static final String BEZEICHNUNG = "bezeichnung";

	public static final String TYP = "typ";
	
	public static final String PREIS = "preis";
	
	private int id;
	private String marke;
	private String bezeichnung;
	private String typ;
	private BigDecimal preis = BigDecimal.ZERO;

	private String name;

	public Saite(int id, String marke, String bezeichnung, String typ, BigDecimal preis) {
		this.id = id;
		this.marke = marke;
		this.bezeichnung = bezeichnung;
		this.typ = typ;
		this.name = marke + " " + bezeichnung;
		this.preis = preis;
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

	public BigDecimal getPreis() {
		return preis != null ? preis : BigDecimal.ZERO;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
}
