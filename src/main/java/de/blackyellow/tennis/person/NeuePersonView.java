package de.blackyellow.tennis.person;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class NeuePersonView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		addComponent(new Label("Hello Vaadin!"));
		
		Notification.show("Welcome to the Animal Farm");

	}

}
