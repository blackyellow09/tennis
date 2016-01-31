package de.blackyellow.tennis.menu;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.saite.Saite;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public interface UserauswahlView {

	public static final String ROOT_VIEW = "";
	
	interface UserauswahlViewListener
	{
		BeanItemContainer<Kunde> getKunden();

		BeanItemContainer<Schlaeger> getSchlaeger();

		BeanItemContainer<Saite> getSaiten();
	}
	
	public void addListener(UserauswahlViewListener listener);
}