package de.blackyellow.tennis.bespannung;

import java.util.Date;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.bespannung.BespannungsuebersichtView.BespannungsuebersichtViewListener;
import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.schlaeger.Schlaeger;

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

	@Override
	public BeanItemContainer<BespannungKurzItem> getBespannungsliste() {
		BeanItemContainer<BespannungKurzItem> container = new BeanItemContainer<BespannungKurzItem>(BespannungKurzItem.class);
		Schlaeger schlaeger = DatabaseHandler.liefereSchlaeger(1);
		Bespannung bespannung = new Bespannung(new Date(), 36, 25, 26);
		container.addBean(new BespannungKurzItem(schlaeger, bespannung));
		return container;
	}
}
