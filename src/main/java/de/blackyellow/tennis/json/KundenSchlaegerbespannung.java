package de.blackyellow.tennis.json;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class KundenSchlaegerbespannung {

	private Bespannung bespannung;
	private Kunde kunde;
	private Schlaeger schlaeger;

	public KundenSchlaegerbespannung(Bespannung bespannung, Kunde kunde, Schlaeger schlaeger) {
		this.bespannung = bespannung;
		this.kunde = kunde;
		this.schlaeger = schlaeger;


	}

}
