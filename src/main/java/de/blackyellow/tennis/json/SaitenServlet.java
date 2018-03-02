package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.aktualisiereSaite;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSaite;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSaiten;
import static de.blackyellow.tennis.db.DatabaseHandler.speichereNeueSaite;
import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.blackyellow.tennis.saite.Saite;

@SuppressWarnings("serial")
@Path("/saiten")
public class SaitenServlet extends HttpServlet {

	@Path("alle")
	@GET
	@Produces("application/json")
	public String getAlleSaiten()
	{
		ArrayList<Saite> saiten = liefereSaiten();
	      Gson gson = new Gson();
	      return gson.toJson(saiten);
	}
	
	@Path("id/{id}")
	@GET
	@Produces("application/json")
	public String getSaite(@PathParam("id") String id)
	{
		if(!id.isEmpty())
		{
			int saiteId = Integer.parseInt(id);
			Saite saite = liefereSaite(saiteId);
			Gson gson = new Gson();
			return gson.toJson(saite);
		}
		return null;
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public void updateSaite(@QueryParam("id") String id, @QueryParam("saite") String saiteParam)
	{
		if(!id.isEmpty())
		{
			Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
			aktualisiereSaite(fromJson);
		}
		else
		{
			Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
			speichereNeueSaite(fromJson);
		}
	}
}
