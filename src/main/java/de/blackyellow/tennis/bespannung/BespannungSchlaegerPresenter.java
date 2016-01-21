package de.blackyellow.tennis.bespannung;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerView.BespannungSchlaegerViewListener;
import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungSchlaegerPresenter implements BespannungSchlaegerViewListener{

	private BespannungSchlaegerView view;
	private BespannungSchlaegerModel model;

	public BespannungSchlaegerPresenter(BespannungSchlaegerView view, BespannungSchlaegerModel model) {
		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public BespannungSchlaegerModel ermittleDaten(String parameters) {
		String[] params = parameters.split("/");
		String kundennr = params[1];
		String schlaegerEnabled = params[0];
		if(params.length > 2)
		{
			String schlaegerId = params[2];
			model.setSchlaeger(DatabaseHandler.liefereSchlaeger(Integer.parseInt(schlaegerId)));
			model.setBespannungen(DatabaseHandler.liefereBespannung(Integer.parseInt(schlaegerId)));
		}
		else
		{
			model.setSchlaeger(null);
			model.setBespannungen(null);
		}
		
		model.setKunde(DatabaseHandler.liefereKunde(Integer.parseInt(kundennr)));
		model.setSchlaegerEnabled("1".equals(schlaegerEnabled));
		
		if(model.isSchlaegerEnabled())
		{
			model.setAlleSchlaeger(DatabaseHandler.liefereSchlaegernamen());
		}
		model.setSaiten(DatabaseHandler.liefereSaiten());
		
		
		return model;
	}

	public boolean isSchlaegerEnabled() {
		return model.isSchlaegerEnabled();
	}

	public Kunde getKunde() {
		return model.getKunde();
	}

	public Schlaeger getSchlaeger() {
		return model.getSchlaeger();
	}

}
