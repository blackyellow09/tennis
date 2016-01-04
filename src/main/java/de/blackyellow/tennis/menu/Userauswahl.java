package de.blackyellow.tennis.menu;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Servlet implementation class Userauswahl
 */
//@WebServlet(description = "Hauptmenü der Anwendung, Auswahl des Kunden", urlPatterns = { "/Userauswahl" })
public class Userauswahl extends UI {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout view = new VerticalLayout();
        view.addComponent(new Label("Hello this is Vaadin!"));
        setContent(view);
	}

}
