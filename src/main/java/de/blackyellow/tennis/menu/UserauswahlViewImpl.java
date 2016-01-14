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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.Saite;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtView;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;
import de.blackyellow.tennis.schlaeger.Schlaeger;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsView;

public class UserauswahlViewImpl extends VerticalLayout implements UserauswahlView, View {

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

		Label label = new Label("CM-super-duper-App");
		label.setStyleName(ValoTheme.LABEL_H1);
		addComponent(label);
		setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		
		TabSheet tabsheet = new TabSheet();
		tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		tabsheet.addTab(addTabKunden(), "Kunden");
		tabsheet.addTab(addTabSchlaeger(), "Schläger");
		tabsheet.addTab(addTabSaiten(), "Saiten");
//		tabsheet.setSizeFull();
		
        addComponent(tabsheet);
	}


	private VerticalLayout addTabKunden() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.setSizeFull();
		Button button = addButton("Neue Person", NeuePersonView.NEUE_PERSON);
		layout.addComponent(button);
		layout.setComponentAlignment(button, Alignment.MIDDLE_LEFT);
		layout.addComponent(addTableKunden());
		return layout;
	}
	
	private VerticalLayout addTabSchlaeger() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addComponent(addButton("Neues Schlägermodell", NeuePersonView.NEUE_PERSON));
		layout.addComponent(addTableSchlaeger());
		return layout;
	}
	
	private VerticalLayout addTabSaiten() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addComponent(addButton("Neue Saite", NeuePersonView.NEUE_PERSON));
		layout.addComponent(addTableSaiten());
		return layout;
	}

	private Button addButton(String text, final String nextView) {
		@SuppressWarnings("serial")
		Button button = new Button(text,
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(nextView);
            }
        });
//        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_);
        button.setIcon(FontAwesome.USER_PLUS);
        return button;
	}


	@Override
	public void addListener(UserauswahlViewListener listener) {
		listeners = listener;
	}

	@SuppressWarnings("serial")
	private Grid addTableKunden() {
		//Set the data source (IndexedContainer)
		BeanItemContainer<Kunde> kunden = listeners.getKunden();
		Grid table = new Grid();
		table.setSizeFull();
		table.setContainerDataSource(kunden);
		table.setColumns(Kunde.NACHNAME, Kunde.VORNAME);
		// Set the selection mode
		table.setSelectionMode(SelectionMode.SINGLE);
		
		// Add filtering fields in the header
		setColumnFiltering(table, true, Kunde.NACHNAME);
		
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
	
	@SuppressWarnings("serial")
	private Grid addTableSchlaeger() {
		//Set the data source (IndexedContainer)
		BeanItemContainer<Schlaeger> schlaeger = listeners.getSchlaeger();
		Grid table = new Grid();
		table.setSizeFull();
		table.setContainerDataSource(schlaeger);
		table.setColumns(Schlaeger.NAME);
		// Set the selection mode
		table.setSelectionMode(SelectionMode.SINGLE);
		
		// Add filtering fields in the header
		setColumnFiltering(table, true, Schlaeger.NAME);
		
		table.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				int id = ((Schlaeger)event.getItemId()).getId();
				getUI().getNavigator().navigateTo(SchlaegerDetailsView.SCHLAEGER_DETAILS 
                		+ "/" + id);
			}
		});
		
		return table;
	}
	
	@SuppressWarnings("serial")
	private Grid addTableSaiten() {
		//Set the data source (IndexedContainer)
		BeanItemContainer<Saite> saite = listeners.getSaiten();
		Grid table = new Grid();
		table.setSizeFull();
		table.setContainerDataSource(saite);
		table.setColumns(Saite.NAME);
		// Set the selection mode
		table.setSelectionMode(SelectionMode.SINGLE);
		
		// Add filtering fields in the header
		setColumnFiltering(table, true, Saite.NAME);
		
		table.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				int id = ((Saite)event.getItemId()).getId();
				getUI().getNavigator().navigateTo(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT 
                		+ "/" + id);
			}
		});
		
		return table;
	}
	
	private void setColumnFiltering(Grid table, boolean filtered, String filteredColumn) {
        HeaderRow filteringHeader = table.appendHeaderRow();
 
        // Add new TextFields to each column which filters the data from
        // that column
        String columnId = filteredColumn.toString();
        TextField filter = getColumnFilter(columnId, table);
        filteringHeader.getCell(columnId).setComponent(filter);
        filteringHeader.getCell(columnId).setStyleName("filter-header");
    }
 
    @SuppressWarnings("serial")
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
                        true, false);
                f.addContainerFilter(filter);
 
                table.cancelEditor();
            }
        });
        return filter;
    }

}
