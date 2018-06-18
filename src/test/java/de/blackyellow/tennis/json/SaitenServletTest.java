package de.blackyellow.tennis.json;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.jdkhttp.JdkHttpServerTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.hamcrest.CoreMatchers;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.research.ws.wadl.Resource;

import de.blackyellow.tennis.db.SaitenServices;
import de.blackyellow.tennis.saite.Saite;

public class SaitenServletTest extends JerseyTest{//FunctionalTest{

	SaitenServlet underTest = spy(SaitenServlet.class);
	
	@Mock
	SaitenServlet servletMock;
	
	@Mock
	SaitenServices saitenServices;
	
	@Test
	public void wennAlleSaitenGesucht_dannLiefereAlleSaiten() {
		ArrayList<Saite> colSaiten = new ArrayList<>();
		colSaiten.add(createSaite());
		doReturn(colSaiten).when(underTest).liefereSaiten();
		
		Response response = underTest.getAlleSaiten();
		String entity = response.getEntity().toString();
		List<Saite> alleSaiten = new Gson().fromJson(entity, new TypeToken<List<Saite>>(){}.getType());
		
		assertThat(alleSaiten, contains(saite("Solinco Hyper")));
	}
	
	@Test
	public void testName() throws Exception {
		ArrayList<Saite> colSaiten = new ArrayList<>();
		colSaiten.add(createSaite());
		when(saitenServices.liefereSaiten()).thenReturn(colSaiten);
	
		WebTarget targetUpdated = target("/saiten/alle");
	    Response response = targetUpdated.request().accept(MediaType.APPLICATION_JSON).get();
	    
		List<Saite> readEntity = response.readEntity(new GenericType<List<Saite>>(){});
		assertThat(readEntity, contains(saite("Solinco Hyper")));
		assertThat(response.getMediaType().toString(), is(MediaType.APPLICATION_JSON));
		assertThat(response.getHeaderString("Access-Control-Allow-Origin"), is("http://tennis.blackyellow.de"));
	}

	private Matcher<Saite> saite(String string) {
		return new FeatureMatcher<Saite, String>(containsString(string), "Saite", "Saiten") {

			@Override
			protected String featureValueOf(Saite actual) {
				return actual.getName().toString();
			}
		};
	}

	@Override
		protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
			return new GrizzlyWebTestContainerFactory();
		}
	
	@Override
		protected DeploymentContext configureDeployment() {
		MockitoAnnotations.initMocks(this);
		enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
			ResourceConfig resourceConfig = new ResourceConfig(SaitenServlet.class);
			resourceConfig.register(JacksonFeature.class);
			resourceConfig.register(JacksonJsonProvider.class);
			resourceConfig.register(new AbstractBinder() {

				@Override
				protected void configure() {
					bind(saitenServices).to(SaitenServices.class).in(Singleton.class);
				}
			});
			return ServletDeploymentContext.forServlet(new ServletContainer(resourceConfig))
					.build();
		}
	
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
