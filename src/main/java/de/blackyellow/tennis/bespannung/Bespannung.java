package de.blackyellow.tennis.bespannung;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import de.blackyellow.tennis.saite.Saite;

public class Bespannung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6490881318243112568L;
	public static final String DATUM = "datum";
	public static final String DT = "dt";
	public static final String LAENGS = "laengs";
	public static final String QUER = "quer";
	public static final String SAITE = "saite";
	public static final String PREIS = "preis";
	
	Date datum;
	int dt;
	int laengs;
	int quer;
	Saite saite;
	BigDecimal preis = BigDecimal.ZERO;
	private int id;
	
	public Bespannung(int id, Date datum, int dt, int kgLaengs, int kgQuer) {
		this.id = id;
		this.datum = datum;
		this.dt = dt;
		this.laengs = kgLaengs;
		this.quer = kgQuer;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public int getLaengs() {
		return laengs;
	}
	public void setLaengs(int laengs) {
		this.laengs = laengs;
	}
	public int getQuer() {
		return quer;
	}
	public void setQuer(int quer) {
		this.quer = quer;
	}

	public Saite getSaite() {
		return saite;
	}

	public void setSaite(Saite saite) {
		this.saite = saite;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
}
