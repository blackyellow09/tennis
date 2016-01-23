package de.blackyellow.tennis.bespannung;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.schlaeger.Schlaeger;

public interface BespannungSchlaegerView {

	interface BespannungSchlaegerViewListener
	{

		BespannungSchlaegerModel ermittleDaten(String parameters);

		boolean speichern(BeanItemContainer<Bespannung> container, Schlaeger object);

		
	}

	public final String BESPANNUNG_SCHLAEGER = "bespannungSchlaeger";
	public final String BESPANNUNG_SCHLAEGER_EDITABLE = BESPANNUNG_SCHLAEGER + "/" + 1;
	
	public void addListener(BespannungSchlaegerViewListener listener);
}
