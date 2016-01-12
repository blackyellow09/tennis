package de.blackyellow.tennis.menu;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerPresenter;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerView;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerViewImpl;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtPresenter;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtView;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtViewImpl;
import de.blackyellow.tennis.person.NeuePersonModel;
import de.blackyellow.tennis.person.NeuePersonPresenter;
import de.blackyellow.tennis.person.NeuePersonView;

import com.vaadin.ui.Label;

/**
 * Servlet implementation class Userauswahl
 */
//@WebServlet(description = "Hauptmenü der Anwendung, Auswahl des Kunden", urlPatterns = { "/Userauswahl" })
@Theme("valo")
public class Tennisapp extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5514058771932638536L;

	Navigator navigator;
	
	@Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Tennis-App");
        UserauswahlModel model = new UserauswahlModel();
        UserauswahlViewImpl view = new UserauswahlViewImpl();
        new UserauswahlPresenter(model, view);
        
        NeuePersonModel neuePersonModel = new NeuePersonModel();
        NeuePersonView neuePersonView = new NeuePersonView();
        new NeuePersonPresenter(neuePersonModel, neuePersonView);
        
        BespannungsuebersichtViewImpl bespannungsView = new BespannungsuebersichtViewImpl();
        new BespannungsuebersichtPresenter(bespannungsView);
        
        BespannungSchlaegerViewImpl bespannungSchlaegerView = new BespannungSchlaegerViewImpl();
        new BespannungSchlaegerPresenter(bespannungSchlaegerView);
        
        navigator = new Navigator(this, this);
        
        navigator.addView(UserauswahlView.ROOT_VIEW, view);
        navigator.addView(NeuePersonView.NEUE_PERSON, neuePersonView);
        navigator.addView(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT, bespannungsView);
        navigator.addView(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER, bespannungSchlaegerView);
    }


}
