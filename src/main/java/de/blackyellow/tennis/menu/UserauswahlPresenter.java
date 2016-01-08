package de.blackyellow.tennis.menu;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.menu.UserauswahlView.UserauswahlViewListener;
import de.blackyellow.tennis.person.Kunde;

public class UserauswahlPresenter implements UserauswahlViewListener{

	private UserauswahlModel model;
	private UserauswahlView view;

	public UserauswahlPresenter(UserauswahlModel model, UserauswahlView view) {
		this.model = model;
		this.view = view;
		
		view.addListener(this);
	}

	@Override
	public BeanItemContainer<Kunde> getKunden() {
		return DatabaseHandler.readAllKunden();
	}

}
