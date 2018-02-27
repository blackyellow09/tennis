package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.liefereBespannungen;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereKundeZuSchlaeger;
import static de.blackyellow.tennis.util.ServletUtil.getParameter;
import static de.blackyellow.tennis.util.ServletUtil.prepareResponse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class BespannungServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getParameter(req, "id");
		if(!id.isEmpty())
		{
			int schlaegerId = Integer.parseInt(id);
			ArrayList<Bespannung> bespannungen = liefereBespannungen(schlaegerId);
			Kunde kunde = liefereKundeZuSchlaeger(schlaegerId);
			Schlaeger schlaeger = DatabaseHandler.liefereSchlaeger(schlaegerId);
			Kundenbespannungen kundenbespannungen = new Kundenbespannungen(bespannungen, kunde, schlaeger);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String json = gson.toJson(kundenbespannungen);
			
			System.out.println(json);
			
			prepareResponse(resp);
			resp.getWriter().write(json);
		}
	}
}
