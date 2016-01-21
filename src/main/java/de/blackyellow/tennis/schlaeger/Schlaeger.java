package de.blackyellow.tennis.schlaeger;

import java.io.Serializable;

import com.vaadin.data.fieldgroup.PropertyId;

public class Schlaeger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6129822417686257690L;

	public static final String NAME = "name";
	public static final String MARKE = "marke";
	public static final String BEZEICHNUNG = "bezeichnung";

	public static final String MAINS = "mains";
	public static final String CROSSES = "crosses";
	public static final String KOPFGROESSE = "kopfgroesse";
	public static final String GEWICHT = "gewicht";
	public static final String SEITENLAENGE = "seitelaenge";
	public static final String SEITENLAENGE_OPT = "seitenlaengeOpt";

	public static final String BILD = "bild";

	public static final String ID = "id";
	
	private String marke;
	private String bezeichnung;

	@PropertyId(NAME)
	private String name;

	private int mains;
	private int crosses;
	private int kopfgroesse;
	private double seitelaenge;
	private double gewicht;
	private double seitenlaengeOpt;

	private int schlaegerNr;
	private int modellNr;

	public Schlaeger(int modellNr, String marke, String bezeichnung) {
		this.modellNr = modellNr;
		this.marke = marke;
		this.bezeichnung = bezeichnung;
		this.name = marke + " " + bezeichnung;
	}

	public Schlaeger(int modellNr, String marke, String bezeichnung, int mains, int crosses, int kopfgroesse,
			double gewicht, double seitenlaenge, double seitenlaengeOpt) {
		this(modellNr, marke, bezeichnung);
		this.mains = mains;
		this.crosses = crosses;
		this.kopfgroesse = kopfgroesse;
		this.gewicht = gewicht;
		this.seitelaenge = seitenlaenge;
		this.seitenlaengeOpt = seitenlaengeOpt;
	}

	public Schlaeger(int schlaegerNr, int modellId, int kundennummer, String marke2, String bezeichnung2, int mains2,
			int crosses2, int kopfgroesse2, double gewicht2, double seitenlaenge2, double seitenlaengeOpt2) {
		this(kundennummer, marke2, bezeichnung2, mains2, crosses2, kopfgroesse2, gewicht2, seitenlaenge2, seitenlaengeOpt2);
		this.setSchlaegerNr(schlaegerNr);
		this.setModellNr(modellId);
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
	
	public String getName()
	{
		name = marke + " " + bezeichnung;
		return name;
	}

	public int getMains() {
		return mains;
	}

	public void setMains(int mains) {
		this.mains = mains;
	}

	public int getCrosses() {
		return crosses;
	}

	public void setCrosses(int crosses) {
		this.crosses = crosses;
	}

	public int getKopfgroesse() {
		return kopfgroesse;
	}

	public void setKopfgroesse(int kopfgroesse) {
		this.kopfgroesse = kopfgroesse;
	}

	public double getSeitelaenge() {
		return seitelaenge;
	}

	public void setSeitelaenge(double seitelaenge) {
		this.seitelaenge = seitelaenge;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public double getSeitenlaengeOpt() {
		return seitenlaengeOpt;
	}

	public void setSeitenlaengeOpt(double seitenlaengeOpt) {
		this.seitenlaengeOpt = seitenlaengeOpt;
	}
	
	public String getBild()
	{
		if(getMains() > 0 && getCrosses() > 0)
		{
			return getMains() + "/" + getCrosses();
		}
		else if(getMains() > 0)
		{
			return getMains()+"";
		}
		else
		{
			return getCrosses()+"";
		}
	}

	public int getSchlaegerNr() {
		return schlaegerNr;
	}

	public void setSchlaegerNr(int schlaegerNr) {
		this.schlaegerNr = schlaegerNr;
	}

	public int getModellNr() {
		return modellNr;
	}

	public void setModellNr(int modellNr) {
		this.modellNr = modellNr;
	}
}
