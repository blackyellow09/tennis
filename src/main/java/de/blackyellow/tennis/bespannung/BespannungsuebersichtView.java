package de.blackyellow.tennis.bespannung;

public interface BespannungsuebersichtView {

	interface BespannungsuebersichtViewListener
	{

		BespannungsuebersichtModel ermittleDaten(String parameters);

	}

	public final String BESPANNUNGSUEBERSICHT = "bespannungsuebersicht";;
	
	public void addListener(BespannungsuebersichtViewListener listener);

	@Override
	String toString();
}
