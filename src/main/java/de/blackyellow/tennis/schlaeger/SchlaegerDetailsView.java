package de.blackyellow.tennis.schlaeger;

public interface SchlaegerDetailsView {

	interface SchlaegerDetailsViewListener
	{

		void getSchlaeger(String parameters);

		
	}

	public final String SCHLAEGER_DETAILS = "schlaegerDetails";
	
	public void addListener(SchlaegerDetailsViewListener listener);

	public void setSchlaeger(Schlaeger schlaeger);
}
