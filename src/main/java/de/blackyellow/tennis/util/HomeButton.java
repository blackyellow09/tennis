package de.blackyellow.tennis.util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.menu.UserauswahlView;

public class HomeButton extends Button {

	/** */
	private static final long serialVersionUID = -2067131972707449678L;

	public HomeButton() {
		this(null);
	}
	
	public HomeButton(String bezeichnung) {
		if(bezeichnung == null)
		{
			this.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
			this.setIcon(FontAwesome.HOME);
			this.setDescription("Startseite");
		}
		else
		{
			this.setCaption(bezeichnung);
		}
		this.addClickListener(new Button.ClickListener() {
			/** */
			private static final long serialVersionUID = -4185133244180717961L;
	
			@Override
			public void buttonClick(ClickEvent clickEvent) {
				getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);
			}
		});
	}
}