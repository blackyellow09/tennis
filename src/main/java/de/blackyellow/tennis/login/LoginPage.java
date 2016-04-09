package de.blackyellow.tennis.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.blackyellow.tennis.menu.UserauswahlView;

public class LoginPage extends VerticalLayout implements View {

	public static final String NAME = "Login";
	private Button btnLogin = new Button("Login");
    private TextField user = new TextField ( "Username");
    private PasswordField password = new PasswordField ( "Password");


    private void initUI ()
    {
        addComponent ( new Label ("Please login in order to use the application") );
        addComponent ( new Label () );
        addComponent ( user );
        addComponent ( password );
        addComponent ( createButton() );
    }

	private Button createButton() {
		btnLogin.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//
		        // Validate the fields using the navigator. By using validors for the
		        // fields we reduce the amount of queries we have to use to the database
		        // for wrongly entered passwords
		        //
		        if (!user.isValid() || !password.isValid()) {
		            return;
		        }

		        String username = user.getValue();
		        String pw = password.getValue();

		        //
		        // Validate username and password with database here. For examples sake
		        // I use a dummy username and password.
		        //
		        boolean isValid = username.equals("christian")
		                && pw.equals("passw0rd");

		        if (isValid) {

		            // Store the current user in the service session
		            getSession().setAttribute("user", username);

		            // Navigate to main view
		            getUI().getNavigator().navigateTo(UserauswahlView.ROOT_VIEW);//

		        } else {

		            // Wrong password clear the password field and refocuses it
		            password.setValue(null);
		            password.focus();

		        }
				
			}
		});
		return btnLogin;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		initUI();
	}
}
