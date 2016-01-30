package de.blackyellow.tennis.schlaeger;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsView.SchlaegerDetailsViewListener;

public class SchlaegerDetailsPresenter implements SchlaegerDetailsViewListener{

	private SchlaegerDetailsView view;

	public SchlaegerDetailsPresenter(SchlaegerDetailsView view) {
//		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public void getSchlaeger(String parameters) {
		int id = Integer.parseInt(parameters);
		view.setSchlaeger(DatabaseHandler.liefereSchlaegermodell(id));
	}

	@Override
	public void createNewSchlaeger() {
		view.setSchlaeger(new Schlaeger());
	}

	@Override
	public boolean speichern(Schlaeger schlaeger) {
		boolean erfolgreich = false;
		if(schlaeger.getModellNr() == 0)
		{
			erfolgreich = DatabaseHandler.speichereNeuesSchlaegermodell(schlaeger);
		}
		else
		{
			erfolgreich = DatabaseHandler.aktualisiereSchlaegermodell(schlaeger);
		}
		return erfolgreich;
	}

}
