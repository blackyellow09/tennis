package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.util.ServletUtil.getParameter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class SchlaegerZuordnungServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kundeId = getParameter(req,"id");
			int kundenNr = Integer.parseInt(kundeId);
			Kunde kunde = DatabaseHandler.liefereKunde(kundenNr);
			Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
			ArrayList<Schlaeger> schlaeger = DatabaseHandler.liefereSchlaegernamen();
			KundeUndSchlaegernamen kundeUndSchlaegernamen = new KundeUndSchlaegernamen(kunde, schlaeger);
			String json = gson.toJson(kundeUndSchlaegernamen);
			System.out.println(json);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
	}
}
