package de.blackyellow.tennis.saite;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.saite.SaiteDetailsView.SaiteDetailsViewListener;

public class SaiteDetailsPresenter implements SaiteDetailsViewListener{

	private SaiteDetailsView view;

	public SaiteDetailsPresenter(SaiteDetailsView saiteDetaisView) {
		this.view = saiteDetaisView;
		
		view.addListener(this);
	}

	@Override
	public boolean speichern(Saite saite) {
		boolean erfolgreich = false;
		if(saite.getId() == 0)
		{
			erfolgreich = DatabaseHandler.speichereNeueSaite(saite);
		}
		else
		{
			erfolgreich = DatabaseHandler.aktualisiereSaite(saite);
		}
		return erfolgreich;
	}

	@Override
	public void createNewSaite() {
		view.setSaite(new Saite());
	}

	@Override
	public void getSaite(String parameters) {
		int id = Integer.parseInt(parameters);
		view.setSaite(DatabaseHandler.liefereSaite(id));
	}

}
