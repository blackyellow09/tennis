package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.readAllKunden;
import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;
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

import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.util.SchlaegerUebersichtComparator;

public class KundenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      ArrayList<Kunde> kunden = readAllKunden();
	      Gson gson = new Gson();
	      String mainObject = gson.toJson(kunden);

	      System.out.println(mainObject);
	      
	      prepareResponse(resp);
	      resp.getWriter().write(mainObject);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getParameter(req, "id");
		String kundeParam = getParameter(req,"kunde");
		if(isBestandskunde(id))
		{
			int kundenNr = Integer.parseInt(id);
			if(isKundendatenVorhanden(kundeParam))
			{
				Kunde fromJson = createObjectFromJson(kundeParam, Kunde.class);
				DatabaseHandler.updateKunde(fromJson);
				return;
			}
			Kunde kunde = DatabaseHandler.liefereKunde(kundenNr);
			Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
			String edit = getParameter(req, "edit");
			String json;
			if(isEditMode(edit))
			{
				json = gson.toJson(kunde);
			}
			else
			{
				ArrayList<BespannungKurzItem> schlaeger = DatabaseHandler.liefereSchlaegerZuKunde(kundenNr);
				schlaeger.sort(new SchlaegerUebersichtComparator());
				int anzahlBespannungen = DatabaseHandler.liefereAnzahlBespannungenZuKunde(kundenNr);
				SchlaegerZuKunde schlaegerZuKunde = new SchlaegerZuKunde(kunde, schlaeger, anzahlBespannungen);
				json = gson.toJson(schlaegerZuKunde);
			}
			System.out.println(json);
			
			prepareResponse(resp);
			resp.getWriter().write(json);
		}
		else if(isKundendatenVorhanden(kundeParam))
		{
			Kunde fromJson = createObjectFromJson(kundeParam, Kunde.class);
			DatabaseHandler.speichereNeuenKunden(fromJson);
		}
	}

	private boolean isEditMode(String edit) {
		return edit != null;
	}

	private boolean isBestandskunde(String kundenNr) {
		return kundenNr != null && !kundenNr.isEmpty();
	}
	
	private boolean isKundendatenVorhanden(String kundeParam) {
		return kundeParam != null && !kundeParam.isEmpty();
	}
}
