package de.blackyellow.tennis.json;

import java.util.ArrayList;

import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.person.Kunde;

public class SchlaegerZuKunde {

	private Kunde kunde;
	private ArrayList<BespannungKurzItem> schlaeger;
	private int anzahlBespannungenGesamt;

	public SchlaegerZuKunde(Kunde kunde, ArrayList<BespannungKurzItem> schlaeger, int anzahlBespannungen) {
		this.kunde = kunde;
		this.schlaeger = schlaeger;
		this.anzahlBespannungenGesamt = anzahlBespannungen;
	}

}
