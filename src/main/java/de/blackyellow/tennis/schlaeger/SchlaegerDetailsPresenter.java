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
		view.setSchlaeger(DatabaseHandler.liefereSchlaeger(id));
	}

}
