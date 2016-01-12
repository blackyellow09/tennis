package de.blackyellow.tennis.bespannung;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;

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
		
		addComponent(new Label("Das wird die Übersichtsseite über die Bespannungen von " + kunde.getName()));
		
		Button schlaeger1 = new Button("Schläger 1",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER);
            }
        });
		addComponent(schlaeger1);
		addComponent(new Button("Schläger 2"));
		addComponent(new Button("Schläger 3"));
		
		@SuppressWarnings("serial")
		Button button = new Button("Neuen Schläger zuordnen",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER_EDITABLE);
            }
        });
        button.setIcon(FontAwesome.CART_PLUS);
        addComponent(button);
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
