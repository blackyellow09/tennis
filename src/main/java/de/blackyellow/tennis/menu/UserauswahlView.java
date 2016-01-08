package de.blackyellow.tennis.menu;

import com.vaadin.data.util.BeanItemContainer;

import de.blackyellow.tennis.person.Kunde;

public interface UserauswahlView {

	public static final String ROOT_VIEW = "";
	
	interface UserauswahlViewListener
	{
		BeanItemContainer<Kunde> getKunden();
	}
	
	public void addListener(UserauswahlViewListener listener);
}