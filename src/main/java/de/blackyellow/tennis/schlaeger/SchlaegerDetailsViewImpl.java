package de.blackyellow.tennis.schlaeger;

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
import de.blackyellow.tennis.util.TextfieldMitUnit;

public class SchlaegerDetailsViewImpl extends FormLayout implements View, SchlaegerDetailsView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private SchlaegerDetailsViewListener listener;
	private Schlaeger schlaeger;
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters().isEmpty())
		{
			listener.createNewSchlaeger();
		}
		else
		{
			listener.getSchlaeger(event.getParameters());
		}

		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSpacing(true);
		setMargin(true);

		BeanItem<Schlaeger> schlaegerBean = new BeanItem<Schlaeger>(schlaeger);
		
		Label ueberschrift = new Label("Schlägerdetails");
		ueberschrift.setStyleName(ValoTheme.LABEL_H2);
		addComponent(ueberschrift);
		
		TextField marke = new TextField("Marke", schlaegerBean.getItemProperty(Schlaeger.MARKE));
		marke.setRequired(true);
		addComponent(marke);
		TextField bezeichnung = new TextField("Bezeichnung", schlaegerBean.getItemProperty(Schlaeger.BEZEICHNUNG));
		bezeichnung.setRequired(true);
		bezeichnung.setWidth(100, Unit.PERCENTAGE);
		addComponent(bezeichnung);
		TextField mains = new TextField("Anzahl Längsseiten", schlaegerBean.getItemProperty(Schlaeger.MAINS));
		mains.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
		addComponent(mains);
		TextField crosses = new TextField("Anzahl Querseiten", schlaegerBean.getItemProperty(Schlaeger.CROSSES));
		crosses.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
		addComponent(crosses);
		TextfieldMitUnit kopfgroesse = new TextfieldMitUnit("Kopfgröße", "cm²", schlaegerBean.getItemProperty(Schlaeger.KOPFGROESSE));
		addComponent(kopfgroesse);
		TextfieldMitUnit gewicht = new TextfieldMitUnit("Gewicht","g", schlaegerBean.getItemProperty(Schlaeger.GEWICHT));
		addComponent(gewicht);
		TextfieldMitUnit seitenlaenge = new TextfieldMitUnit("Empfohlene Seitenlänge", "m", schlaegerBean.getItemProperty(Schlaeger.SEITENLAENGE));
		addComponent(seitenlaenge);
		TextfieldMitUnit seitenlaengeOpt = new TextfieldMitUnit("Optimierte Seitenlänge", "m", schlaegerBean.getItemProperty(Schlaeger.SEITENLAENGE_OPT));
		addComponent(seitenlaengeOpt);
		
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
				
				boolean erfolgreich = listener.speichern(schlaeger);
				if(erfolgreich)
				{
					getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);	
				}
			}
		});
	}
	
	@Override
	public void addListener(SchlaegerDetailsViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setSchlaeger(Schlaeger schlaeger) {
		this.schlaeger = schlaeger;
	}
}
