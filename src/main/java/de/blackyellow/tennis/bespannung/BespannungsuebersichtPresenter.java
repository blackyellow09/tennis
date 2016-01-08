package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.bespannung.BespannungsuebersichtView.BespannungsuebersichtViewListener;
import de.blackyellow.tennis.db.DatabaseHandler;

public class BespannungsuebersichtPresenter implements BespannungsuebersichtViewListener{

	private BespannungsuebersichtView view;

	public BespannungsuebersichtPresenter(BespannungsuebersichtView view) {
//		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public void ermittleKundendaten(String parameters) {
		int kundennummer = Integer.parseInt(parameters);
		view.setKunde(DatabaseHandler.liefereKunde(kundennummer));
		
	}
}
