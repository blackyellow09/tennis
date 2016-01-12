package de.blackyellow.tennis.menu;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.bespannung.BespannungsuebersichtView;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;

public class UserauswahlViewImpl extends GridLayout implements UserauswahlView, View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1667952908909776359L;
	private UserauswahlViewListener listeners;

	@Override
	public void enter(ViewChangeEvent event) {
		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSizeFull();
		setColumns(2);
		
		BeanItemContainer<Kunde> kunden = listeners.getKunden();
		addSearchBox(kunden);
		
		addUserButtons(kunden);
		
        @SuppressWarnings("serial")
		Button button = new Button("Neue Person",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(NeuePersonView.NEUE_PERSON);
            }
        });
        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        button.setIcon(FontAwesome.USER_PLUS);
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        
        addTableSchlaeger();
	}
	
	private void addSearchBox(BeanItemContainer<Kunde> colKunde) {
		// Creates a new combobox using an existing container
        ComboBox combobox = new ComboBox("Personensuche",colKunde);
 
        combobox.setInputPrompt("Nach Person suchen");
 
        // Sets the combobox to show a certain property as the item caption
        combobox.setItemCaptionPropertyId(Kunde.NAME);
        combobox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
 
        // Set full width
        combobox.setWidth(100.0f, Unit.PERCENTAGE);
 
        // Set the appropriate filtering mode for this example
        combobox.setFilteringMode(FilteringMode.CONTAINS);
        combobox.setImmediate(true);
 
        // Disallow null selections
        combobox.setNullSelectionAllowed(false);
 
        addComponent(combobox);
        
        // Check if the caption for new item already exists in the list of item
        // captions before approving it as a new item.
//        combobox.setNewItemHandler(new NewItemHandler() {
//            @Override
//            public void addNewItem(final String newItemCaption) {
//                boolean newItem = true;
//                for (final Object itemId : combobox.getItemIds()) {
//                    if (newItemCaption.equalsIgnoreCase(combobox
//                            .getItemCaption(itemId))) {
//                        newItem = false;
//                        break;
//                    }
//                }
//                if (newItem) {
//                    // Adds new option
//                    if (combobox.addItem(newItemCaption) != null) {
//                        final Item item = combobox.getItem(newItemCaption);
//                        item.getItemProperty(ExampleUtil.iso3166_PROPERTY_NAME)
//                                .setValue(newItemCaption);
//                        combobox.setValue(newItemCaption);
//                    }
//                }
//            }
//        });
	}

	public void addUserButtons(BeanItemContainer<Kunde> kunden)
	{
		for (int i = 0; i < kunden.size(); i++) {
			String kundenname = kunden.getIdByIndex(i).getName();
			final int kundennummer = kunden.getIdByIndex(i).getKundennummer();
			
			@SuppressWarnings("serial")
			Button button = new Button(kundenname,//"User "+i,
	                new Button.ClickListener() {
	            @Override
	            public void buttonClick(ClickEvent event) {
	                getUI().getNavigator().navigateTo(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT 
	                		+ "/" + kundennummer);
	            }
	        });
	        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
	        button.setIcon(FontAwesome.USER);
	        addComponent(button);
	        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		}
	}

	@Override
	public void addListener(UserauswahlViewListener listener) {
		listeners = listener;
	}

	private void addTableSchlaeger() {
		//Set the data source (IndexedContainer)
		BeanItemContainer<Kunde> schlaeger = listeners.getKunden();
		Grid table = new Grid();
		table.setContainerDataSource(schlaeger);
		table.setColumns(Kunde.NACHNAME, Kunde.VORNAME);
		// Set the selection mode
		table.setSelectionMode(SelectionMode.SINGLE);
		
		// Add filtering fields in the header
		setColumnFiltering(table, true);
		
		table.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				int kundennummer = ((Kunde)event.getItemId()).getKundennummer();
				getUI().getNavigator().navigateTo(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT 
                		+ "/" + kundennummer);
			}
		});
		
		addComponent(table);
	}
	
	private void setColumnFiltering(Grid table, boolean filtered) {
        HeaderRow filteringHeader = table.appendHeaderRow();
 
        // Add new TextFields to each column which filters the data from
        // that column
        String columnId = Kunde.NACHNAME.toString();
        TextField filter = getColumnFilter(columnId, table);
        filteringHeader.getCell(columnId).setComponent(filter);
        filteringHeader.getCell(columnId).setStyleName("filter-header");
    }
 
    private TextField getColumnFilter(final Object columnId, final Grid table) {
        TextField filter = new TextField();
//        filter.setWidth("100%");
        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filter.setInputPrompt("Filter");
        filter.addTextChangeListener(new TextChangeListener() {
 
            SimpleStringFilter filter = null;
 
            @Override
            public void textChange(TextChangeEvent event) {
                Filterable f = (Filterable) table.getContainerDataSource();
 
                // Remove old filter
                if (filter != null) {
                    f.removeContainerFilter(filter);
                }
 
                // Set new filter for the "Name" column
                filter = new SimpleStringFilter(columnId, event.getText(),
                        true, true);
                f.addContainerFilter(filter);
 
                table.cancelEditor();
            }
        });
        return filter;
    }

}
