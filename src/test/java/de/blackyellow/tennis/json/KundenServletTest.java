package de.blackyellow.tennis.json;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class KundenServletTest {

	private MockHttpServletResponse resp;
	private MockHttpServletRequest req;

	@Before
	public void before() {
		resp = new MockHttpServletResponse();
		req = new MockHttpServletRequest();
	}
	
	@Test
	public void wennAlleKundenGesucht_dannLiefereAlleKunden() throws ServletException, IOException {
		KundenServlet underTest = new KundenServlet()
				{
			@Override
			protected ArrayList<Kunde> liefereAlleKunden() {
				ArrayList<Kunde> kunden = new ArrayList<>();
				kunden.add(new Kunde(0, "Saskia", "Neid"));
				return kunden;
			}
				};
//		HttpUriRequest request = new HttpGet(uri);
//		HttpClientBuilder.create().build().execute(request);
		underTest.doGet(req, resp);
		
		assertThat(resp.getContentAsString(), containsString("Saskia Neid"));
		assertThat(resp.getContentType(), is(MediaType.APPLICATION_JSON));
		assertThat(resp.getHeader("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
	}
	
	@Test
	public void wennKundendatenOhneIdGeliefert_dannSpeichereNeuenKunden() throws Exception {
		KundenServlet underTest = Mockito.spy(KundenServlet.class);
		
		req.addParameter("kunde", "{\"kundennummer\":0,\"vorname\":\"Saskia\",\"nachname\":\"Neid\",\"name\":\"Saskia Neid\"}");
		
		doNothing().when(underTest).speichereKunde(Mockito.any());
		
		underTest.doPost(req, resp);
		
		verify(underTest).speichereKunde(Mockito.argThat(isKunde("Saskia Neid")));
	}
	
	@Test
	public void wennNurKundenIdUebergeben_dannLiefereKundeUndBespannungKurzItemDazuUndAnzahlBespannungen() throws Exception {
		KundenServlet underTest = Mockito.spy(KundenServlet.class);
		
		req.addParameter("id", "1");
		
		Kunde kunde = new Kunde(1, "Saskia", "Neid");
		doReturn(kunde).when(underTest).liefereKunde(1);
		Schlaeger schlaeger = new Schlaeger();
		Bespannung bespannung = new Bespannung(0, new java.sql.Date(1000), 42, 24, 24);
		BespannungKurzItem bki = new BespannungKurzItem(schlaeger, bespannung);
		ArrayList<BespannungKurzItem> schlaegerZuKunde = new ArrayList<>();
		schlaegerZuKunde.add(bki);
		doReturn(schlaegerZuKunde).when(underTest).liefereSchlaegerZuKunde(1);
		doReturn(3).when(underTest).liefereAnzahlBespannungenZuKunde(1);
		
		underTest.doPost(req, resp);
		
		assertThat(resp.getContentAsString(), containsString("Saskia Neid"));
		assertThat(resp.getContentType(), is(MediaType.APPLICATION_JSON));
		assertThat(resp.getHeader("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
	}

	@Test
	public void wennKundenIdUndEditModeUebergeben_dannLiefereKunde() throws Exception {
		KundenServlet underTest = Mockito.spy(KundenServlet.class);
		
		req.addParameter("id", "1");
		req.addParameter("kunde", "{\"kundennummer\":0,\"vorname\":\"Saskia\",\"nachname\":\"Neid\",\"name\":\"Saskia Neid\"}");
		
		doNothing().when(underTest).updateKunde(Mockito.any());
		
		underTest.doPost(req, resp);

		verify(underTest).updateKunde(Mockito.argThat(isKunde("Saskia Neid")));
	}
	
	@Test
	public void wennKundenIdUndKundendatenUebergeben_dannAktualisiereKunde() throws Exception {
KundenServlet underTest = Mockito.spy(KundenServlet.class);
		
		req.addParameter("id", "1");
		req.addParameter("edit", "true");
		
		Kunde kunde = new Kunde(1, "Saskia", "Neid");
		doReturn(kunde).when(underTest).liefereKunde(1);
		
		underTest.doPost(req, resp);
		
		assertThat(resp.getContentAsString(), containsString("Saskia Neid"));
		assertThat(resp.getContentType(), is(MediaType.APPLICATION_JSON));
		assertThat(resp.getHeader("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
	}
	
	private Matcher<Kunde> isKunde(String string) {
		return new FeatureMatcher<Kunde, String>(equalTo(string), "name", "ka") {

			@Override
			protected String featureValueOf(Kunde arg0) {
				return arg0.getName();
			}
		};
	}

}
