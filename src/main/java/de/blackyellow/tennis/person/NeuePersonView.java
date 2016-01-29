package de.blackyellow.tennis.person;

public interface NeuePersonView {

	interface NeuePersonViewListener
	{

		boolean speichern(Kunde kunde);
		
	}
	
	public static final String NEUE_PERSON = "neuePerson";
	
	public void addListener(NeuePersonViewListener listener);
	
}
