package de.blackyellow.tennis.menu;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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

	@Override
    protected void init(VaadinRequest request) {
        VerticalLayout view = new VerticalLayout();
        view.addComponent(new Label("Hello Vaadin!"));
        setContent(view);
    }


}
