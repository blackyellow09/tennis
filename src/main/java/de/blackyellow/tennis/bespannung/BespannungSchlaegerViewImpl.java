package de.blackyellow.tennis.bespannung;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerView.BespannungSchlaegerViewListener;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;

public class BespannungSchlaegerViewImpl extends VerticalLayout implements View, BespannungSchlaegerView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private BespannungSchlaegerViewListener listener;
	private Kunde kunde;
	
	@Override
	public void enter(ViewChangeEvent event) {
//		listener.ermittleKundendaten(event.getParameters());

		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSizeFull();
		
		addComponent(new Label("Das wird die Detailseite eines Schlägers über seine Bespannungen."));
		
		if(event.getParameters().isEmpty())
		{
			addComponent(new Label("In diesem Fall kann nur die Tabelle mit den Bespannungsdetails editiert werden."));
		}
		else if(Integer.parseInt(event.getParameters()) == 1)
		{
			addComponent(new Label("In diesem Fall ist kann noch ein Schlägermodell ausgewählt werden."));
		}
		
	}

	@Override
	public void addListener(BespannungSchlaegerViewListener listener) {
		this.listener = listener;
	}

}
