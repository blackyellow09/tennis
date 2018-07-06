package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import de.blackyellow.tennis.db.SaitenServices;
import de.blackyellow.tennis.saite.Saite;

@Singleton
@Path("/saiten")
public class SaitenServlet {

	@Inject
	protected SaitenServices services;
	
	@Path("alle")
	@GET
	@Produces("application/json")
	public Response getAlleSaiten()
	{
		ArrayList<Saite> saiten = liefereSaiten();
	    Gson gson = new Gson();
	    return Response.status(Status.OK).entity(gson.toJson(saiten)).header("Access-Control-Allow-Origin", "http://tennis.blackyellow.de").build();
	}

	protected ArrayList<Saite> liefereSaiten() {
		return services.liefereSaiten();
	}
	
	@Path("id/{id}")
	@GET
	@Produces("application/json")
	public Response getSaite(@PathParam("id") String id)
	{
		if(!id.isEmpty())
		{
			int saiteId = Integer.parseInt(id);
			Saite saite = liefereSaite(saiteId);
			Gson gson = new Gson();
			return Response.status(Status.OK).entity(gson.toJson(saite)).header("Access-Control-Allow-Origin", "http://tennis.blackyellow.de").build();
		}
		//TODO Status OK?
		return Response.status(Status.OK).build();
	}

	protected Saite liefereSaite(int saiteId) {
		return services.liefereSaite(saiteId);
	}
	
	@OPTIONS
	@Path("/*")
	public Response options()
	{
		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "http://tennis.blackyellow.de")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response updateSaite(@QueryParam("id") String id, @QueryParam("saite") String saiteParam)
	{
		if(!id.isEmpty())
		{
			Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
			if(!aktualisiereSaite(fromJson))
			{
				throw new InternalServerErrorException();
			}
		}
		else
		{
			Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
			if(!speichereSaite(fromJson))
			{
				throw new InternalServerErrorException();
			}
		}
		return Response.status(Status.OK).header("Access-Control-Allow-Origin", "http://tennis.blackyellow.de").build();
	}

	protected boolean speichereSaite(Saite fromJson) {
		return services.speichereSaite(fromJson);
	}

	protected boolean aktualisiereSaite(Saite fromJson) {
		return services.aktualisiereSaite(fromJson);
	}
}
