package de.blackyellow.tennis.json;

import java.util.ArrayList;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class Kundenbespannungen {

	private ArrayList<Bespannung> bespannungen;
	private Kunde kunde;
	private Schlaeger schlaeger;
	

	public Kundenbespannungen(ArrayList<Bespannung> bespannungen, Kunde kunde, Schlaeger schlaeger) {
		this.bespannungen = bespannungen;
		this.kunde = kunde;
		this.schlaeger = schlaeger;
	}

}
