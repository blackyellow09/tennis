package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerView.BespannungSchlaegerViewListener;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtView.BespannungsuebersichtViewListener;
import de.blackyellow.tennis.db.DatabaseHandler;

public class BespannungSchlaegerPresenter implements BespannungSchlaegerViewListener{

	private BespannungSchlaegerView view;

	public BespannungSchlaegerPresenter(BespannungSchlaegerView view) {
//		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

}
