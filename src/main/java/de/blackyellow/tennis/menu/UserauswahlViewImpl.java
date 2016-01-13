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
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
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
//		setSizeFull();
		setColumns(3);
		
		TabSheet tabsheet = new TabSheet();
		tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		tabsheet.addTab(addTabKunden(), "Kunden");
		tabsheet.addTab(addTabSchlaeger(), "Schläger");
		tabsheet.addTab(addTabSaiten(), "Saiten");
		
//        addButtonNeuerSchlaeger();
//        addButtonNeueSaite();
//        
//        
//        addTableKunden();
//        addTableKunden();
        addComponent(tabsheet);
	}


	private VerticalLayout addTabKunden() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(addButtonNeuePerson());
		layout.addComponent(addTableKunden());
		return layout;
	}
	
	private VerticalLayout addTabSchlaeger() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(addButtonNeuerSchlaeger());
		layout.addComponent(addTableKunden());
		return layout;
	}
	
	private VerticalLayout addTabSaiten() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(addButtonNeueSaite());
		layout.addComponent(addTableKunden());
		return layout;
	}


	private Button addButtonNeueSaite() {
		@SuppressWarnings("serial")
		Button button = new Button("Neue Saite",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(NeuePersonView.NEUE_PERSON);
            }
        });
        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        button.setIcon(FontAwesome.USER_PLUS);
        return button;
	}


	private Button addButtonNeuerSchlaeger() {
		@SuppressWarnings("serial")
		Button button = new Button("Neues Schlägermodell",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(NeuePersonView.NEUE_PERSON);
            }
        });
        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        button.setIcon(FontAwesome.USER_PLUS);
        return button;
	}


	private Button addButtonNeuePerson() {
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
        return button;
	}
	

	@Override
	public void addListener(UserauswahlViewListener listener) {
		listeners = listener;
	}

	private Grid addTableKunden() {
		//Set the data source (IndexedContainer)
		BeanItemContainer<Kunde> kunden = listeners.getKunden();
		Grid table = new Grid();
		table.setContainerDataSource(kunden);
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
		
		return table;
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
