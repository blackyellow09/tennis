package de.blackyellow.tennis.person;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.menu.Views;

public class NeuePersonView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
		setSizeFull();
		addComponent(new Label("Hello Vaadin!"));
		
		Notification.show("Welcome to the Animal Farm");
		
		Button button = new Button("Zurück",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(Views.ROOT_VIEW);
            }
        });
//        button.setStyleName(ValoTheme.BUTTON);
        button.setIcon(FontAwesome.HOME);
        addComponent(button);
        setComponentAlignment(button, Alignment.BOTTOM_LEFT);

	}

}
