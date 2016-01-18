package de.blackyellow.tennis.bespannung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.CompositeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.Saite;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerView.BespannungSchlaegerViewListener;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungSchlaegerViewImpl extends VerticalLayout implements View, BespannungSchlaegerView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private BespannungSchlaegerViewListener listener;
	private BespannungSchlaegerModel model;
	
	@Override
	public void enter(ViewChangeEvent event) {
		model = listener.ermittleDaten(event.getParameters());
		if(getComponentCount() > 0)
			
		{
			removeAllComponents();
		}

		setSpacing(true);
		setMargin(true);
		String ueberschrift;
		if(model.isSchlaegerEnabled())
		{
			ueberschrift = "Neuer Schläger von " + model.getKunde().getName();
		}
		else
		{
			ueberschrift = model.getKunde().getName() + " - Schläger " + model.getSchlaeger().getId();
		}
		Label lblUeberschrift = new Label(ueberschrift);
		lblUeberschrift.setStyleName(ValoTheme.LABEL_H2);
		addComponent(lblUeberschrift);
		addSchlaegerAuswahl();
		addTableBespannungen();
	}

	private void addSchlaegerAuswahl() {
		
		List<Schlaeger> colSchlaeger = new ArrayList<Schlaeger>();
		
		ComboBox combobox = new ComboBox("Modell");
		combobox.setWidth(100, Unit.PERCENTAGE);
		if(model.isSchlaegerEnabled())
		{
			colSchlaeger.addAll(model.getAlleSchlaeger());
		}
		else
		{
			colSchlaeger.add(model.getSchlaeger());
			combobox.setEnabled(false);
		}
		BeanItemContainer<Schlaeger> items = new BeanItemContainer<Schlaeger>(Schlaeger.class, colSchlaeger);
		combobox.setContainerDataSource(items);
		combobox.select(items.getIdByIndex(0));
		combobox.setItemCaptionPropertyId(Schlaeger.NAME);
		combobox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		combobox.setNullSelectionAllowed(false);
		
		addComponent(combobox);
		
	}
	
	private void addTableBespannungen()
	{
		BeanItemContainer<Bespannung> bespannungen = model.getBespannungen();
		BeanItemContainer<ComponentBean> container = new BeanItemContainer<BespannungSchlaegerViewImpl.ComponentBean>(ComponentBean.class);
		for (int i = 0; i < bespannungen.size(); i++) {
			container.addBean(new ComponentBean(bespannungen.getIdByIndex(i), new BeanItemContainer<Saite>(Saite.class)));
			
		}
		Table table = new Table();
//		table.setSizeFull();
		table.setWidth(100, Unit.PERCENTAGE);
//		table.setEnabled(true);
		table.setEditable(true);
		table.setContainerDataSource(bespannungen);
		
		table.setTableFieldFactory(new DefaultFieldFactory() {
			
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				Date aktuellstesDatum = model.getAktuellstesDatum();
				boolean enable = true;
				if(((Bespannung)itemId).getDatum().before(aktuellstesDatum))
				{
					enable = false;
				}
				if(Bespannung.SAITE.equals(propertyId))
				{
					ComboBox cboSaite = new ComboBox();
					cboSaite.setSizeFull();
					cboSaite.addItems(model.getSaiten());
//					cboSaite.addStyleName(ValoTheme.COMBOBOX_TINY);
					if(!enable)
					{
						cboSaite.setEnabled(enable);
//						cboSaite.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
						cboSaite.addStyleName(ValoTheme.COMBOBOX_TINY);
					}
					return cboSaite;
				}
				if(Bespannung.DT.equals(propertyId))
				{
					TextField textfield = new TextField();
					textfield.setWidth(3, Unit.EM);
					textfield.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
					CompositeValidator validator = new CompositeValidator();
//					validator.addValidator(new IntegerRangeValidator("Der DT-Wert muss zwischen 28 und 55 liegen", 28, 55));
					ausgrauen(enable, textfield);
					textfield.addValidator(validator);
							
					return textfield;
				}
				if(Bespannung.LAENGS.equals(propertyId)
						|| Bespannung.QUER.equals(propertyId))
				{
					TextField textfield = new TextField();
					textfield.setWidth(7, Unit.EM);
					textfield.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
					ausgrauen(enable, textfield);
//					textfield.addValidator(new IntegerValidator("Bitte nur ganze Zahlen eingeben!"));
//							new RegexpValidator("[0-9]+", "Bitte nur ganze Zahlen eingeben!"));
					return textfield;
				}
				if(Bespannung.PREIS.equals(propertyId))
				{
					TextField textfield = new TextField();
					textfield.setWidth(5, Unit.EM);
					textfield.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
					ausgrauen(enable, textfield);
					return textfield;
				}
				Field createField = super.createField(container, itemId, propertyId, uiContext);
				ausgrauen(enable, createField);
				return createField;
			}

			private void ausgrauen(boolean enable, Field textfield) {
				if(!enable)
				{
					textfield.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
				}
				textfield.setEnabled(enable);
			}
		});
		
		Bespannung newItem = new Bespannung(new Date(), 0, 0, 0);
		bespannungen.addBean(newItem);
		
		table.setVisibleColumns(Bespannung.DATUM, Bespannung.DT,Bespannung.LAENGS, 
				Bespannung.QUER, Bespannung.SAITE, Bespannung.PREIS);
		table.setColumnHeader(Bespannung.DATUM, "Letzte Bespannung");
		table.setColumnHeader(Bespannung.DT, "DT");
		table.setColumnHeader(Bespannung.LAENGS, "kg Längsseiten");
		table.setColumnHeader(Bespannung.QUER, "kg Querseiten");
		table.setColumnHeader(Bespannung.SAITE, "Saite");
		table.setColumnHeader(Bespannung.PREIS, "Preis in €");
		
//		table.setColumnWidth(Bespannung.DT, 10);
		
		// Set the selection mode
		table.setSortContainerPropertyId(Bespannung.DATUM);
		table.setSortAscending(false);
		// Add filtering fields in the header
		table.setPageLength(7);
		addComponent(table);
	}

	@Override
	public void addListener(BespannungSchlaegerViewListener listener) {
		this.listener = listener;
	}

	public class ComponentBean implements Serializable
	{
		public static final String SAITE = "saite";
		public static final String DATUM = "datum";
		
		private Bespannung bespannung;
		private ComboBox saite = new ComboBox();
		
		public ComponentBean(Bespannung bespannung, BeanItemContainer<Saite> saiten) {
			this.bespannung = bespannung;
			saite.setContainerDataSource(saiten);
		}
		
		public Date getDatum()
		{
			return bespannung.getDatum();
		}
		public ComboBox getSaite()
		{
			return saite;
		}
	}
	
//	AbstractValidator<Integer>("message") extends AbstractValidator<T> {
		
//	}
}
