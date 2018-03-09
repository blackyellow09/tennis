package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.aktualisiereSchlaegerModell;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereKundeIdZuSchlaegerId;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/schlaegerBearbeiten")
public class SchlaegerBearbeiten {

	@PUT
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public Response speichereAenderungen(@QueryParam("id") String schlaegerId, @QueryParam("modell") String modellNr)
	{
		if(!schlaegerId.isEmpty())
		{
			boolean success = aktualisiereSchlaegerModell(schlaegerId, modellNr);
			if(success)
			{
				int kundeId = liefereKundeIdZuSchlaegerId(schlaegerId);
				if(kundeId > 0)
				{
					return Response.ok(kundeId, MediaType.TEXT_PLAIN).build();
				}
			}
		}
//		test
		return Response.status(Status.NOT_FOUND).build();
	}
}
