package de.blackyellow.tennis.json;

import java.util.ArrayList;

import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.person.Kunde;

public class SchlaegerZuKunde {

	private Kunde kunde;
	private ArrayList<BespannungKurzItem> schlaeger;

	public SchlaegerZuKunde(Kunde kunde, ArrayList<BespannungKurzItem> schlaeger) {
		this.kunde = kunde;
		this.schlaeger = schlaeger;
	}

}
