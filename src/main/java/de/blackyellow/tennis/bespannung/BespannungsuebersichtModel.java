package de.blackyellow.tennis.bespannung;

import java.util.ArrayList;
import java.util.Date;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungsuebersichtModel {

	private Kunde kunde;
	private ArrayList<BespannungKurzItem> bespannungen;

	public void setKunde(Kunde liefereKunde) {
		this.kunde = liefereKunde;
	}

	public void setBespannungen(ArrayList<BespannungKurzItem> bespannungsliste) {
		this.bespannungen = bespannungsliste;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public BeanItemContainer<BespannungKurzItem> getBespannungen() {
		BeanItemContainer<BespannungKurzItem> container = new BeanItemContainer<BespannungKurzItem>(BespannungKurzItem.class);
		container.addAll(bespannungen);
		return container;
	}

}
