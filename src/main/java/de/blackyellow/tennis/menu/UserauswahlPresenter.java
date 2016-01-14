package de.blackyellow.tennis.menu;

import java.util.ArrayList;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Notification;

import de.blackyellow.tennis.Saite;
import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.menu.UserauswahlView.UserauswahlViewListener;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class UserauswahlPresenter implements UserauswahlViewListener{

	private UserauswahlModel model;
	private UserauswahlView view;

	private Notification notification;

	public UserauswahlPresenter(UserauswahlModel model, UserauswahlView view) {
		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public BeanItemContainer<Kunde> getKunden() {
		return DatabaseHandler.readAllKunden();
	}

	@Override
	public BeanItemContainer<Schlaeger> getSchlaeger() {
		ArrayList<Schlaeger> listSchlaeger = DatabaseHandler.liefereSchlaegernamen();
		BeanItemContainer<Schlaeger> beanItems = new BeanItemContainer<Schlaeger>(Schlaeger.class);
		beanItems.addAll(listSchlaeger);
		return beanItems;
	}

	@Override
	public BeanItemContainer<Saite> getSaiten() {
		ArrayList<Saite> listSaiten;
		BeanItemContainer<Saite> beanItems = new BeanItemContainer<Saite>(Saite.class);
		listSaiten = DatabaseHandler.liefereSaiten();
		beanItems.addAll(listSaiten);
		return beanItems;
	}

}
