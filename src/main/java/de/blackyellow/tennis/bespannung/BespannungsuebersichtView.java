package de.blackyellow.tennis.bespannung;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.person.Kunde;

public interface BespannungsuebersichtView {

	interface BespannungsuebersichtViewListener
	{

		void ermittleKundendaten(String parameters);

		BeanItemContainer<BespannungKurzItem> getBespannungsliste();
		
	}

	public final String BESPANNUNGSUEBERSICHT = "bespannungsuebersicht";;
	
	public void addListener(BespannungsuebersichtViewListener listener);

	public void setKunde(Kunde liefereKunde);
}
