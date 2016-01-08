package de.blackyellow.tennis.bespannung;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.blackyellow.tennis.person.Kunde;

public class BespannungsuebersichtViewImpl extends VerticalLayout implements View, BespannungsuebersichtView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private BespannungsuebersichtViewListener listener;
	private Kunde kunde;
	
	@Override
	public void enter(ViewChangeEvent event) {
		listener.ermittleKundendaten(event.getParameters());

		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSizeFull();
		
		addComponent(new Label("Das wird die Übersichtsseite über die Bespannungen von" + kunde.getName()));
	}

	@Override
	public void addListener(BespannungsuebersichtViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

}
