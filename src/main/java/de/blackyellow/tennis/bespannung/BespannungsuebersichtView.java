package de.blackyellow.tennis.bespannung;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.person.Kunde;

public interface BespannungsuebersichtView {

	interface BespannungsuebersichtViewListener
	{

		BespannungsuebersichtModel ermittleDaten(String parameters);

	}

	public final String BESPANNUNGSUEBERSICHT = "bespannungsuebersicht";;
	
	public void addListener(BespannungsuebersichtViewListener listener);

}
