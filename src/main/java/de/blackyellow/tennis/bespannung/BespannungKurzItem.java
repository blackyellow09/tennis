package de.blackyellow.tennis.bespannung;

import java.io.Serializable;
import java.util.Date;

import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungKurzItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2306081499795261991L;
	public static final String DOT = ".";
	public static final String BESPANNUNG = "bespannung";
	public static final String SCHLAEGER = "schlaeger";
	public static final String DATUM = Bespannung.DATUM;
	public static final String DT = Bespannung.DT;
	public static final String LAENGS = Bespannung.LAENGS;
	public static final String QUER = Bespannung.QUER;
	public static final String BILD = Schlaeger.BILD;
	public static final String NAME = Schlaeger.NAME;
	public static final String ID = Schlaeger.ID;
	
	private Schlaeger schlaeger;
	private Bespannung bespannung;

	public BespannungKurzItem(Schlaeger schlaeger, Bespannung bespannung) {
		this.setSchlaeger(schlaeger);
		this.setBespannung(bespannung);
	}

	public Schlaeger getSchlaeger() {
		return schlaeger;
	}

	public void setSchlaeger(Schlaeger schlaeger) {
		this.schlaeger = schlaeger;
	}

	public Bespannung getBespannung() {
		return bespannung;
	}

	public void setBespannung(Bespannung bespannung) {
		this.bespannung = bespannung;
	}
	
	public int getQuer()
	{
		return getBespannung() != null ? getBespannung().getQuer() : 0;
	}
	
	public Date getDatum() {
		return getBespannung() != null ? getBespannung().getDatum() : null;
	}
	public int getDt() {
		return getBespannung() != null ? getBespannung().getDt() : 0;
	}
	public int getLaengs() {
		return getBespannung() != null ? getBespannung().getLaengs() : 0;
	}
	
	public String getName()
	{
		return getSchlaeger().getName();
	}
	
	public String getBild()
	{
		return getSchlaeger().getBild();
	}
	
	public int getId()
	{
		return getSchlaeger().getSchlaegerNr();
	}
}
