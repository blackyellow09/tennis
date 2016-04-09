package de.blackyellow.tennis.bespannung;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.CompositeValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.schlaeger.Schlaeger;
import de.blackyellow.tennis.util.TennisLayout;

public class BespannungSchlaegerViewImpl extends TennisLayout implements View, BespannungSchlaegerView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private BespannungSchlaegerViewListener listener;
	private BespannungSchlaegerModel model;
	private Table table;
	private ComboBox combobox;
	
	@Override
	public void enter(ViewChangeEvent event) {
		model = listener.ermittleDaten(event.getParameters());
		if(getComponentCount() > 0)
		{
			return;
		}
		initDefaultLayout();
		
		String ueberschrift;
		if(model.isSchlaegerEnabled())
		{
			ueberschrift = "Neuer Schläger von " + model.getKunde().getName();
		}
		else
		{
			ueberschrift = model.getKunde().getName() + " - Schläger " + model.getSchlaeger().getSchlaegerNr();
		}
		Label lblUeberschrift = new Label(ueberschrift);
		lblUeberschrift.setStyleName(ValoTheme.LABEL_H2);
		setHeader(lblUeberschrift, Alignment.MIDDLE_CENTER);
		
		VerticalLayout body = new VerticalLayout();
		body.addComponent(addSchlaegerAuswahl());
		body.addComponent(addTableBespannungen());
		setBody(body, Alignment.MIDDLE_CENTER, true);
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		buttons.addComponent(addSpeichernButton());
		setFooter(buttons, Alignment.MIDDLE_CENTER);
		
	}

	private Button addSpeichernButton() {
		return new Button("Speichern", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				boolean erfolgreich = listener.speichern((BeanItemContainer<Bespannung>) table.getContainerDataSource(), (Schlaeger) combobox.getValue());
				if(erfolgreich)
				{
					getUI().getNavigator().navigateTo(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT 
	                		+ "/" + model.getKunde().getKundennummer());	
				}
			}
		});
	}

	private ComboBox addSchlaegerAuswahl() {
		
		List<Schlaeger> colSchlaeger = new ArrayList<Schlaeger>();
		
		combobox = new ComboBox("Modell");
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
		
		return combobox;
		
	}
	
	private Table addTableBespannungen()
	{
		BeanItemContainer<Bespannung> bespannungen = model.getBespannungen();
		table = new Table();
		table.setWidth(100, Unit.PERCENTAGE);
		table.setEditable(true);
		table.setContainerDataSource(bespannungen);
		
		table.setTableFieldFactory(new DefaultFieldFactory() {
			
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				Date aktuellstesDatum = model.getAktuellstesDatum();
				boolean enable = true;
				Bespannung bespannung = (Bespannung)itemId;
				if(!model.isSchlaegerEnabled() && bespannung.getId() > 0 
					&& bespannung.getDatum().before(aktuellstesDatum))// <= 0)
				{
					enable = false;
				}
				if(Bespannung.SAITE.equals(propertyId))
				{
					ComboBox cboSaite = new ComboBox();
					cboSaite.setSizeFull();
					cboSaite.addItems(model.getSaiten());
//					cboSaite.addStyleName(ValoTheme.COMBOBOX_TINY);
					cboSaite.setEnabled(enable);
					if(!enable)
					{
						
//						cboSaite.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
						cboSaite.setStyleName(ValoTheme.COMBOBOX_TINY);
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
					final TextField textfield = new TextField();
					textfield.setWidth(7, Unit.EM);
					textfield.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
					ausgrauen(enable, textfield);
					textfield.addValueChangeListener(new ValueChangeListener() {
						
						@Override
						public void valueChange(ValueChangeEvent event) {
							try {
								Integer result = Integer.parseInt((String) event.getProperty().getValue());
							} catch (NumberFormatException nfe) {
								textfield.setValidationVisible(true);
								throw new InvalidValueException("Ganze Zahlen");

							}
						}
					});
//					textfield.setConverter(new MyConverter());
//					textfield.addValidator(new CustomIntegerRangeValidator("Bitte nur ganze Zahlen eingeben!", 0, 99));
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
		
		Bespannung newItem = new Bespannung(-1, new java.sql.Date(Calendar.getInstance().getTime().getTime()), 0, 0, 0);
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
		return table;
	}

	@Override
	public void addListener(BespannungSchlaegerViewListener listener) {
		this.listener = listener;
	}

//	public class CustomIntegerRangeValidator implements Validator {
//
//	    private static final long serialVersionUID = 1L;
//
//	    private IntegerRangeValidator integerRangeValidator;
//
//	    public CustomIntegerRangeValidator(String errorMessage, Integer minValue, Integer maxValue) {
//	        this.integerRangeValidator = new IntegerRangeValidator(errorMessage, minValue, maxValue);
//	    }
//
//		@Override
//		public void validate(Object value) throws InvalidValueException {
//			if(!(value instanceof Integer)) 
//			{
//				try {
//		            Integer result = Integer.parseInt((String) value);
//		            integerRangeValidator.validate(result);
//		        } catch (NumberFormatException nfe) {
//		            throw new InvalidValueException("Ganze Zahlen");
//		        } catch (Exception e) {
//		            // TODO: handle exception
//		            System.out.println("Unknown exception: " + e);
//		        }
//			}
//		}
//
//	}
	
//	public class MyConverter extends StringToIntegerConverter
//	{
//		StringToIntegerConverter stiConv;
//		public MyConverter() {
//			stiConv = new StringToIntegerConverter();
//		}
//		
//		@Override
//		public Integer convertToModel(String value, Class<? extends Integer> targetType, Locale locale)
//				throws com.vaadin.data.util.converter.Converter.ConversionException {
//			try
//			{
//				return super.convertToModel(value, targetType, locale);
//			}
//			catch (ConversionException e)
//			{
//				throw new ConversionException("geht nicht");
//			}
//			 
//		}
//	}
	
}
