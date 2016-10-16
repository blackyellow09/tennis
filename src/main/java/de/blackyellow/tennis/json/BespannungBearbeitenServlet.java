package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.liefereKundeZuSchlaeger;
import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;
import static de.blackyellow.tennis.util.ServletUtil.getParameter;

import java.io.IOException;

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

public class BespannungBearbeitenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getParameter(req, "id");
		String bespannungParam = getParameter(req, "bespannung");
		String schlaegerId = getParameter(req, "schlaegerId");
		if(id != null && !id.isEmpty())
		{
			if(bespannungParam != null && !bespannungParam.isEmpty())
			{
				Bespannung fromJson = createObjectFromJson(bespannungParam, Bespannung.class);
				DatabaseHandler.updateBespannung(fromJson, fromJson.getSchlaegerId());
			}
			else
			{
				int bespId = Integer.parseInt(id);
				Bespannung bespannung = DatabaseHandler.liefereBespannung(bespId);
				Kunde kunde = liefereKundeZuSchlaeger(bespannung.getSchlaegerId());
				Schlaeger schlaeger = DatabaseHandler.liefereSchlaeger(bespannung.getSchlaegerId());
				KundenSchlaegerbespannung kundenSchlaegerBespannung = new KundenSchlaegerbespannung(bespannung, kunde, schlaeger);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gson.toJson(kundenSchlaegerBespannung);
				
				System.out.println(json);
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}
		}
		else if(bespannungParam != null && !bespannungParam.isEmpty())
		{
			Bespannung fromJson = createObjectFromJson(bespannungParam, Bespannung.class);
			int sId = Integer.parseInt(schlaegerId);
			DatabaseHandler.speichereNeueBespannung(fromJson, sId);
		}
		else if(schlaegerId != null && !schlaegerId.isEmpty())
		{
			int sId = Integer.parseInt(schlaegerId);
			Kunde kunde = liefereKundeZuSchlaeger(sId);
			Schlaeger schlaeger = DatabaseHandler.liefereSchlaeger(sId);
			KundenSchlaegerbespannung kundenSchlaegerBespannung = new KundenSchlaegerbespannung(null, kunde, schlaeger);
			Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
			String json = gson.toJson(kundenSchlaegerBespannung);
			
			System.out.println(json);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		}
	}
}
