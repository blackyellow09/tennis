package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.bespannung.BespannungsuebersichtView.BespannungsuebersichtViewListener;

public interface BespannungSchlaegerView {

	interface BespannungSchlaegerViewListener
	{

		
	}

	public final String BESPANNUNG_SCHLAEGER = "bespannungSchlaeger";
	public final String BESPANNUNG_SCHLAEGER_EDITABLE = BESPANNUNG_SCHLAEGER + "/" + 1;
	
	public void addListener(BespannungSchlaegerViewListener listener);
}
