package de.blackyellow.tennis.bespannung;

import java.util.Date;

public interface IBespannung {

	public static final String DATUM = "datum";
	public static final String DT = "dt";
	public static final String LAENGS = "laengs";
	public static final String QUER = "quer";
	public static final String BILD = "bild";
	
	
	public Date getDatum();
	public void setDatum(Date datum);
	public int getDt();
	public void setDt(int dt);
	public int getLaengs();
	public void setLaengs(int laengs);
	public int getQuer();
	public void setQuer(int quer);
	public String getBild();
	public void setBild(String bild);
}
