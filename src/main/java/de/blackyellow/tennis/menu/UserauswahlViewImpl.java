package de.blackyellow.tennis.menu;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
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

}
