package de.blackyellow.tennis.menu;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UserauswahlView extends GridLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		setColumns(2);
        Button button = new Button("Neue Person",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo("neuePerson");
            }
        });
        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        button.setIcon(FontAwesome.USER_PLUS);
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}
	
	public void addUserButtons()
	{
		for (int i = 0; i < 3; i++) {
			Button button = new Button("User "+i,
	                new Button.ClickListener() {
	            @Override
	            public void buttonClick(ClickEvent event) {
	                getUI().getNavigator().navigateTo("neuePerson");
	            }
	        });
	        button.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
	        button.setIcon(FontAwesome.USER);
	        addComponent(button);
	        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		}
	}

}
