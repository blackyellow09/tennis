package de.blackyellow.tennis.person;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.NeuePersonView.NeuePersonViewListener;

public class NeuePersonPresenter implements NeuePersonViewListener{

	private NeuePersonView view;

	public NeuePersonPresenter(NeuePersonView neuePersonView) {
		this.view = neuePersonView;
		
		view.addListener(this);
	}

	@Override
	public boolean speichern(Kunde kunde) {
		return DatabaseHandler.speichereNeuenKunden(kunde);
	}

}
