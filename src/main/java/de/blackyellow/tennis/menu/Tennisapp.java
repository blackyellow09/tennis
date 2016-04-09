package de.blackyellow.tennis.menu;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import de.blackyellow.tennis.bespannung.BespannungSchlaegerModel;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerPresenter;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerView;
import de.blackyellow.tennis.bespannung.BespannungSchlaegerViewImpl;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtModel;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtPresenter;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtView;
import de.blackyellow.tennis.bespannung.BespannungsuebersichtViewImpl;
import de.blackyellow.tennis.person.NeuePersonPresenter;
import de.blackyellow.tennis.person.NeuePersonView;
import de.blackyellow.tennis.person.NeuePersonViewImpl;
import de.blackyellow.tennis.saite.SaiteDetailsPresenter;
import de.blackyellow.tennis.saite.SaiteDetailsView;
import de.blackyellow.tennis.saite.SaiteDetailsViewImpl;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsPresenter;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsView;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsViewImpl;

/**
 * Servlet implementation class Userauswahl
 */
//@WebServlet(description = "Hauptmenü der Anwendung, Auswahl des Kunden", urlPatterns = { "/Userauswahl" })
@Theme("valo")
public class Tennisapp extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5514058771932638536L;

//	ThreadLocal<Tennisapp> currentApp = new ThreadLocal<Tennisapp>();
	
	Navigator navigator;
	
	@Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Tennis-App");
        navigator = new Navigator(this, this);
        
//        LoginPage login = new LoginPage();
//        navigator.addView(LoginPage.NAME, login);
        
        UserauswahlModel model = new UserauswahlModel();
        UserauswahlViewImpl view = new UserauswahlViewImpl();
        new UserauswahlPresenter(model, view);
        navigator.addView(UserauswahlView.ROOT_VIEW, view);
        
        NeuePersonViewImpl neuePersonView = new NeuePersonViewImpl();
        new NeuePersonPresenter(neuePersonView);
        navigator.addView(NeuePersonView.NEUE_PERSON, neuePersonView);
        
        BespannungsuebersichtViewImpl bespannungsView = new BespannungsuebersichtViewImpl();
        BespannungsuebersichtModel bespannungsModel = new BespannungsuebersichtModel();
		new BespannungsuebersichtPresenter(bespannungsView, bespannungsModel);
        navigator.addView(BespannungsuebersichtView.BESPANNUNGSUEBERSICHT, bespannungsView);
        
        BespannungSchlaegerViewImpl bespannungSchlaegerView = new BespannungSchlaegerViewImpl();
        BespannungSchlaegerModel bespannungSchlaegerModel = new BespannungSchlaegerModel();
		new BespannungSchlaegerPresenter(bespannungSchlaegerView, bespannungSchlaegerModel);
        navigator.addView(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER, bespannungSchlaegerView);
        
        SchlaegerDetailsViewImpl schlaegerDetailsView = new SchlaegerDetailsViewImpl();
        new SchlaegerDetailsPresenter(schlaegerDetailsView);
        navigator.addView(SchlaegerDetailsView.SCHLAEGER_DETAILS, schlaegerDetailsView);
        
        SaiteDetailsViewImpl saiteDetailsView = new SaiteDetailsViewImpl();
        new SaiteDetailsPresenter(saiteDetailsView);
        navigator.addView(SaiteDetailsView.SAITE_DETAILS, saiteDetailsView);
        
//        navigator.addViewChangeListener(new ViewChangeListener() {
//
//            @Override
//            public boolean beforeViewChange(ViewChangeEvent event) {
//
//                // Check if a user has logged in
//                boolean isLoggedIn = getSession().getAttribute("user") != null;
//                boolean isLoginView = event.getNewView() instanceof LoginPage;
//
//                if (!isLoggedIn && !isLoginView) {
//                    // Redirect to login view always if a user has not yet
//                    // logged in
//                    getNavigator().navigateTo(LoginPage.NAME);
//                    return false;
//
//                } else if (isLoggedIn && isLoginView) {
//                    // If someone tries to access to login view while logged in,
//                    // then cancel
//                    return false;
//                }
//
//                return true;
//            }
//
//			@Override
//			public void afterViewChange(ViewChangeEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//        });
    }


}
