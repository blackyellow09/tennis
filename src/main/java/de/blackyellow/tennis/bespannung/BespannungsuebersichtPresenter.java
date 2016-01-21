package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.bespannung.BespannungsuebersichtView.BespannungsuebersichtViewListener;
import de.blackyellow.tennis.db.DatabaseHandler;

public class BespannungsuebersichtPresenter implements BespannungsuebersichtViewListener{

	private BespannungsuebersichtView view;
	private BespannungsuebersichtModel model;

	public BespannungsuebersichtPresenter(BespannungsuebersichtView view, BespannungsuebersichtModel model) {
		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public BespannungsuebersichtModel ermittleDaten(String parameters) {
		int kundennummer = Integer.parseInt(parameters);
		model.setKunde(DatabaseHandler.liefereKunde(kundennummer));
		model.setBespannungen(DatabaseHandler.liefereSchlaegerZuKunde(kundennummer));
		return model;
	}

}
