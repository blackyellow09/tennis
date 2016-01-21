package de.blackyellow.tennis.bespannung;

public interface BespannungSchlaegerView {

	interface BespannungSchlaegerViewListener
	{

		BespannungSchlaegerModel ermittleDaten(String parameters);

		
	}

	public final String BESPANNUNG_SCHLAEGER = "bespannungSchlaeger";
	public final String BESPANNUNG_SCHLAEGER_EDITABLE = BESPANNUNG_SCHLAEGER + "/" + 1;
	
	public void addListener(BespannungSchlaegerViewListener listener);
}
