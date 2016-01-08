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

import de.blackyellow.tennis.menu.UserauswahlView;

public class NeuePersonView extends VerticalLayout implements View {

	public static final String NEUE_PERSON = "neuePerson";
	
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
                getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);
            }
        });
//        button.setStyleName(ValoTheme.BUTTON);
        button.setIcon(FontAwesome.HOME);
        addComponent(button);
        setComponentAlignment(button, Alignment.BOTTOM_LEFT);

	}

}
