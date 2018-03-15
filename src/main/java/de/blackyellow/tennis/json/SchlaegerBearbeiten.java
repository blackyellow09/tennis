package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.aktualisiereSchlaegerModell;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereIsSchlaegerAktiv;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereKundeIdZuSchlaegerId;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSchlaegernamen;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import de.blackyellow.tennis.schlaeger.Schlaeger;

@Path("/schlaegerBearbeiten")
public class SchlaegerBearbeiten {

	public class SchlaegernamenUndAktiv {

		private ArrayList<Schlaeger> schlaeger;
		private boolean aktiv;

		public SchlaegernamenUndAktiv(ArrayList<Schlaeger> schlaeger, boolean aktiv) {
			this.schlaeger = schlaeger;
			this.aktiv = aktiv;
		}

	}

	@PUT
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public Response speichereAenderungen(@QueryParam("id") String schlaegerId, @QueryParam("modell") String modellNr,
			@QueryParam("aktiv") boolean aktiv)
	{
		if(!schlaegerId.isEmpty())
		{
			boolean success = aktualisiereSchlaegerModell(schlaegerId, modellNr, aktiv);
			if(success)
			{
				int kundeId = liefereKundeIdZuSchlaegerId(schlaegerId);
				if(kundeId > 0)
				{
					return Response.ok(kundeId, MediaType.TEXT_PLAIN).build();
				}
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchlaegerAktiv(@QueryParam("id") String schlaegerId) 
	{
		boolean aktiv = liefereIsSchlaegerAktiv(schlaegerId);
		Gson gson = new Gson();
		ArrayList<Schlaeger> schlaeger = liefereSchlaegernamen();
		SchlaegernamenUndAktiv schlaegernamenUndAktiv = new SchlaegernamenUndAktiv(schlaeger, aktiv);
		String schlaegernamen = gson.toJson(schlaegernamenUndAktiv);

		return Response.ok(schlaegernamen, MediaType.APPLICATION_JSON).build();
	}
}
