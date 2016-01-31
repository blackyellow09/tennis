package de.blackyellow.tennis.saite;

public interface SaiteDetailsView {

	interface SaiteDetailsViewListener
	{

		boolean speichern(Saite saite);

		void createNewSaite();

		void getSaite(String parameters);
		
	}
	
	public static final String SAITE_DETAILS = "saiteDetails";
	
	public void addListener(SaiteDetailsViewListener listener);

	public void setSaite(Saite saite);
	
}
