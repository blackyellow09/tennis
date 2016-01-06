package de.blackyellow.tennis.menu;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.blackyellow.tennis.person.NeuePersonModel;
import de.blackyellow.tennis.person.NeuePersonPresenter;
import de.blackyellow.tennis.person.NeuePersonView;

import com.vaadin.ui.Label;

/**
 * Servlet implementation class Userauswahl
 */
//@WebServlet(description = "Hauptmenü der Anwendung, Auswahl des Kunden", urlPatterns = { "/Userauswahl" })
public class Userauswahl extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5514058771932638536L;

	Navigator navigator;
	
	@Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("Hello Vaadin!"));
        setContent(layout);
        
        UserauswahlModel model = new UserauswahlModel();
        UserauswahlView view = new UserauswahlView();
        new UserauswahlPresenter(model, view);
        
        NeuePersonModel neuePersonModel = new NeuePersonModel();
        NeuePersonView neuePersonView = new NeuePersonView();
        new NeuePersonPresenter(neuePersonModel, neuePersonView);
        
        layout.addComponent(view);
        
        navigator = new Navigator(this, this);
        
        navigator.addView("", view);
        navigator.addView("neuePerson", neuePersonView);
    }


}
