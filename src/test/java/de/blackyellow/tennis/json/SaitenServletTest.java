package de.blackyellow.tennis.json;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.google.gson.Gson;

import de.blackyellow.tennis.saite.Saite;

public class SaitenServletTest extends JerseyTest{

	SaitenServlet underTest = spy(SaitenServlet.class);

	@Test
	public void wennAlleSaitenGesucht_dannLiefereAlleSaiten() {
		ArrayList<Saite> colSaiten = new ArrayList<>();
		colSaiten.add(createSaite());
		doReturn(colSaiten).when(underTest).liefereSaiten();
		
		String alleSaiten = underTest.getAlleSaiten();
		
		assertThat(alleSaiten, containsString("Solinco"));
	}
	
	@Override
    protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(SaitenServlet.class);
    }
	
	@Test
	public void testName() throws Exception {
		String response = target("saiten/alle").request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		assertThat(response, containsString("Saskia Neid"));
//		assertThat(response.getContentType(), is(MediaType.APPLICATION_JSON));
//		assertThat(response.getHeader("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
	}
	
//	@Test
//	public void testMitClient() throws Exception {
//		HttpUriRequest request = new HttpGet("https://localhost/tennis/saiten/alle");
//		HttpResponse response = HttpClientBuilder.create().build().execute(request);
//		assertThat(ContentType.getOrDefault(response.getEntity()).getMimeType(), is(MediaType.APPLICATION_JSON));
//		assertThat(response.getFirstHeader("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
//	}

	protected Saite createSaite() {
		return new Saite(1, "Solinco", "Hyper", "Poly", BigDecimal.TEN);
	}
	
	@Test
	public void wennSaiteZuUebergebenerIdVorhanden_dannLiefereSaite() throws Exception {
		Saite s = createSaite();
		doReturn(s).when(underTest).liefereSaite(1);
		
		String saite = underTest.getSaite("1");
		
		assertThat(saite, containsString("Solinco"));
	}
	
	@Test
	public void wennSaitenIdVorhanden_dannAktualisiereSaite() throws Exception {
		Mockito.doNothing().when(underTest).aktualisiereSaite(Mockito.any(Saite.class));
		Saite saite = createSaite();
		
		underTest.updateSaite("1", new Gson().toJson(saite));
		
		Mockito.verify(underTest).aktualisiereSaite(Mockito.any(Saite.class));
	}
	
	@Test
	public void wennSaitenIdNichtVorhanden_dannSpeichereSaite() throws Exception {
		Mockito.doNothing().when(underTest).speichereSaite(Mockito.any(Saite.class));
		Saite saite = createSaite();
		
		underTest.updateSaite("", new Gson().toJson(saite));
		
		Mockito.verify(underTest).speichereSaite(Mockito.any(Saite.class));
	}

}
