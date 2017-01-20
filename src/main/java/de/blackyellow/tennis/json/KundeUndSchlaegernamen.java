package de.blackyellow.tennis.json;

import java.util.ArrayList;

import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class KundeUndSchlaegernamen {

	private Kunde kunde;
	private ArrayList<Schlaeger> schlaeger;

	public KundeUndSchlaegernamen(Kunde kunde, ArrayList<Schlaeger> schlaeger) {
		this.kunde = kunde;
		this.schlaeger = schlaeger;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public ArrayList<Schlaeger> getSchlaeger() {
		return schlaeger;
	}

	public void setSchlaeger(ArrayList<Schlaeger> schlaeger) {
		this.schlaeger = schlaeger;
	}

}
