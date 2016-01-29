package de.blackyellow.tennis.person;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.menu.UserauswahlView;
import de.blackyellow.tennis.util.HomeButton;

public class NeuePersonViewImpl extends FormLayout implements View, NeuePersonView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NeuePersonViewListener listener;
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSpacing(true);
		setMargin(true);

//		BeanItem<Kunde> schlaegerBean = new BeanItem<Kunde>(kunde);
		
		Label ueberschrift = new Label("Neuer Kunde");
		ueberschrift.setStyleName(ValoTheme.LABEL_H2);
		addComponent(ueberschrift);
		
		TextField marke = new TextField("Vorname");
		marke.setRequired(true);
		addComponent(marke);
		TextField bezeichnung = new TextField("Nachname");
		bezeichnung.setRequired(true);
//		bezeichnung.setWidth(100, Unit.PERCENTAGE);
		addComponent(bezeichnung);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.addComponent(addSpeichernButton(marke, bezeichnung));
		hl.addComponent(new HomeButton("Abbrechen"));
		addComponent(hl);
	}

	private Button addSpeichernButton(final TextField vorname, final TextField nachname) {
		return new Button("Speichern", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				boolean erfolgreich = listener.speichern(new Kunde(0, vorname.getValue(), nachname.getValue()));
				if(erfolgreich)
				{
					getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);	
				}
			}
		});
	}
	
	@Override
	public void addListener(NeuePersonViewListener listener) {
		this.listener = listener;
	}

}
