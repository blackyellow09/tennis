package de.blackyellow.tennis.bespannung;

import java.math.BigDecimal;

import com.vaadin.data.util.BeanItemContainer;

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

	@Override
	public boolean speichern(BeanItemContainer<Bespannung> container, Schlaeger schlaeger) {
		if(model.isSchlaegerEnabled())
		{
			//neuer Schlaeger, nur 1 Bespannung
			return DatabaseHandler.speichereNeuenSchlaeger(getKunde().getKundennummer(), schlaeger.getModellNr(), container.getIdByIndex(0));
			
		}
		else
		{
			//ggf. eine neue Bespannung, aktuellste geändert?
			Bespannung neueBespannung = null;
			Bespannung aktuellsteBespannung = null;
			for (Bespannung bespannung : container.getItemIds()) {
				if(bespannung.getId() == -1)
				{
					neueBespannung = bespannung;
				}
				else if(bespannung.getDatum().equals(model.getAktuellstesDatum()))
				{
					aktuellsteBespannung = bespannung;
				}
				if(neueBespannung != null && aktuellsteBespannung != null)
				{
					break;
				}
			}
			boolean succesInsert = true;
			boolean succesUpdate = true;
			if(neueBespannungErfasst(neueBespannung))
			{
				succesInsert = DatabaseHandler.speichereNeueBespannung(neueBespannung, getKunde().getKundennummer(), getSchlaeger().getSchlaegerNr());
			}
			if(aktuellsteBespannung != null)
			{
				succesUpdate = DatabaseHandler.updateBespannung(aktuellsteBespannung, getKunde().getKundennummer(), getSchlaeger().getSchlaegerNr());
			}
			return succesInsert && succesUpdate;
		}
	}

	private boolean neueBespannungErfasst(Bespannung neueBespannung) {
		return neueBespannung.getDt() > 0 
				|| neueBespannung.getLaengs() > 0 
				|| neueBespannung.getQuer() > 0
				|| neueBespannung.getSaite() != null 
				|| neueBespannung.getPreis().compareTo(BigDecimal.ZERO) > 0;
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
