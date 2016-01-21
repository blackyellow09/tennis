package de.blackyellow.tennis.bespannung;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseHandler.class)
public class BespannungSchlaegerPresenterTest {

	
	@Test
	public void testLadeDatenSchlaegerNotEnabled() {
		BespannungSchlaegerView view = Mockito.mock(BespannungSchlaegerView.class);
		BespannungSchlaegerModel model = new BespannungSchlaegerModel();
		BespannungSchlaegerPresenter presenter = new BespannungSchlaegerPresenter(view, model );
		
		PowerMockito.mockStatic(DatabaseHandler.class);
		PowerMockito.when(DatabaseHandler.liefereKunde(Mockito.anyInt())).thenReturn(new Kunde(1, "vorname", "nachname"));
		PowerMockito.when(DatabaseHandler.liefereSchlaegermodell(Mockito.anyInt())).thenReturn(new Schlaeger(2, "marke", "name"));
		
		
		presenter.ermittleDaten("0/1/2");
		
		Assert.assertEquals(1, presenter.getKunde().getKundennummer());
		Assert.assertEquals(2, presenter.getSchlaeger().getSchlaegerNr());
		Assert.assertFalse(presenter.isSchlaegerEnabled());
	}
	
	@Test
	public void testLadeDatenSchlaegerEnabled() {
		BespannungSchlaegerView view = Mockito.mock(BespannungSchlaegerView.class);
		BespannungSchlaegerModel model = new BespannungSchlaegerModel();
		BespannungSchlaegerPresenter presenter = new BespannungSchlaegerPresenter(view, model );
		
		PowerMockito.mockStatic(DatabaseHandler.class);
		PowerMockito.when(DatabaseHandler.liefereKunde(Mockito.anyInt())).thenReturn(new Kunde(1, "vorname", "nachname"));
		PowerMockito.when(DatabaseHandler.liefereSchlaegermodell(Mockito.anyInt())).thenReturn(new Schlaeger(2, "marke", "name"));
		
		
		presenter.ermittleDaten("1/1/2");
		
		Assert.assertEquals(1, presenter.getKunde().getKundennummer());
		Assert.assertEquals(2, presenter.getSchlaeger().getSchlaegerNr());
		Assert.assertTrue(presenter.isSchlaegerEnabled());
	}

}
