package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.person.Kunde;

public interface BespannungsuebersichtView {

	interface BespannungsuebersichtViewListener
	{

		void ermittleKundendaten(String parameters);
		
	}

	public final String BESPANNUNGSUEBERSICHT = "bespannungsuebersicht";;
	
	public void addListener(BespannungsuebersichtViewListener listener);

	public void setKunde(Kunde liefereKunde);
}
