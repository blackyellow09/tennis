package de.blackyellow.tennis.util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.login.LoginPage;

public class LogoutButton extends Button {

	/** */
	private static final long serialVersionUID = -2067131972707449678L;

	public LogoutButton() {
		this(null);
	}
	
	public LogoutButton(String bezeichnung) {
		if(bezeichnung == null)
		{
			this.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
			this.setIcon(FontAwesome.SIGN_OUT);
			this.setDescription("Logout");
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
				// "Logout" the user
	            getSession().setAttribute("user", null);

	            // Refresh this view, should redirect to login view
	            getUI().getNavigator().navigateTo(LoginPage.NAME);
			}
		});
	}
}