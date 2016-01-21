package de.blackyellow.tennis.bespannung;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.Saite;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungSchlaegerModel {

	private boolean schlaegerEnabled;
	private Kunde kunde;
	private Schlaeger schlaeger;
	private List<Schlaeger> alleSchlaeger;
	private ArrayList<Saite> saiten;
	private ArrayList<Bespannung> bespannungen;

	

	public boolean isSchlaegerEnabled() {
		return schlaegerEnabled;
	}

	public void setSchlaegerEnabled(boolean enabled) {
		this.schlaegerEnabled = enabled;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	public Kunde getKunde() {
		return kunde;
	}

	public Schlaeger getSchlaeger() {
		return schlaeger;
	}

	public void setSchlaeger(Schlaeger schlaeger) {
		this.schlaeger = schlaeger;
	}

	public List<Schlaeger> getAlleSchlaeger() {
		return alleSchlaeger;
	}

	public void setAlleSchlaeger(List<Schlaeger> alleSchlaeger) {
		this.alleSchlaeger = alleSchlaeger;
	}

	public BeanItemContainer<Bespannung> getBespannungen() {
		BeanItemContainer<Bespannung> itemsBespannungen = new BeanItemContainer<Bespannung>(Bespannung.class);
		if(bespannungen != null)
		{
			itemsBespannungen.addAll(bespannungen);
		}
		return itemsBespannungen;
	}

	public ArrayList<Saite> getSaiten() {
		return saiten;
	}

	public void setSaiten(ArrayList<Saite> liefereSaiten) {
		this.saiten = liefereSaiten;
	}

	public void setBespannungen(ArrayList<Bespannung> liefereBespannung) {
		this.bespannungen = liefereBespannung;
	}
	
	public Date getAktuellstesDatum()
	{
		if(bespannungen == null || bespannungen.isEmpty())
		{
			return new java.sql.Date(Calendar.getInstance().getTime().getTime());
		}
		Date aktuellstesDatum = bespannungen.get(0).getDatum();
		Iterator<Bespannung> it = bespannungen.iterator();
		while(it.hasNext())
		{
			Date vergleichsdatum = it.next().getDatum();
			if(vergleichsdatum.after(aktuellstesDatum))
			{
				aktuellstesDatum = vergleichsdatum;
			}
		}
		return aktuellstesDatum;
	}

}
