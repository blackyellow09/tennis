package de.blackyellow.tennis.schlaeger;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerView.BespannungSchlaegerViewListener;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;

public class SchlaegerDetailsViewImpl extends FormLayout implements View, SchlaegerDetailsView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private SchlaegerDetailsViewListener listener;
	private Schlaeger schlaeger;
	
	@Override
	public void enter(ViewChangeEvent event) {
		listener.getSchlaeger(event.getParameters());

		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSpacing(true);
		setMargin(true);

		BeanItem<Schlaeger> schlaegerBean = new BeanItem<Schlaeger>(schlaeger);
		
		TextField marke = new TextField("Marke", schlaeger.getMarke());
		addComponent(marke);
		TextField bezeichnung = new TextField("Bezeichnung", schlaeger.getBezeichnung());
		bezeichnung.setWidth(100, Unit.PERCENTAGE);
		addComponent(bezeichnung);
		TextField mains = new TextField("Anzahl Längsseiten", schlaegerBean.getItemProperty(Schlaeger.MAINS));
		addComponent(mains);
		TextField crosses = new TextField("Anzahl Querseiten", schlaegerBean.getItemProperty(Schlaeger.CROSSES));
		addComponent(crosses);
		TextField kopfgroesse = new TextField("Kopfgröße", schlaegerBean.getItemProperty(Schlaeger.KOPFGROESSE));
		addComponent(kopfgroesse);
		TextField gewicht = new TextField("Gewicht", schlaegerBean.getItemProperty(Schlaeger.GEWICHT));
		addComponent(gewicht);
		TextField seitenlaenge = new TextField("Empfohlene Seitenlänge", schlaegerBean.getItemProperty(Schlaeger.SEITENLAENGE));
		addComponent(seitenlaenge);
		TextField seitenlaengeOpt = new TextField("Optimierte Seitenlänge", schlaegerBean.getItemProperty(Schlaeger.SEITENLAENGE_OPT));
		addComponent(seitenlaengeOpt);
		
		
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
