package de.blackyellow.tennis.saite;

import com.vaadin.data.util.BeanItem;
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

public class SaiteDetailsViewImpl extends FormLayout implements View, SaiteDetailsView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaiteDetailsViewListener listener;
	private Saite saite;
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters().isEmpty())
		{
			listener.createNewSaite();
		}
		else
		{
			listener.getSaite(event.getParameters());
		}
		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSpacing(true);
		setMargin(true);

		BeanItem<Saite> saitenBean = new BeanItem<Saite>(saite);
		
		Label ueberschrift = new Label("Details zur Saite");
		ueberschrift.setStyleName(ValoTheme.LABEL_H2);
		addComponent(ueberschrift);
		
		TextField marke = new TextField("Marke", saitenBean.getItemProperty(Saite.MARKE));
		marke.setRequired(true);
		addComponent(marke);
		TextField bezeichnung = new TextField("Bezeichnung", saitenBean.getItemProperty(Saite.BEZEICHNUNG));
		bezeichnung.setRequired(true);
		addComponent(bezeichnung);
		TextField typ = new TextField("Typ", saitenBean.getItemProperty(Saite.TYP));
		addComponent(typ);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.addComponent(addSpeichernButton());
		hl.addComponent(new HomeButton("Abbrechen"));
		addComponent(hl);
	}

	private Button addSpeichernButton() {
		return new Button("Speichern", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				boolean erfolgreich = listener.speichern(saite);
				if(erfolgreich)
				{
					getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);	
				}
			}
		});
	}
	
	@Override
	public void addListener(SaiteDetailsViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setSaite(Saite saite) {
		this.saite = saite;
	}

}
