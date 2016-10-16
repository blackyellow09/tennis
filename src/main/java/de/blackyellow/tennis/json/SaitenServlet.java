package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.aktualisiereSaite;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSaite;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSaiten;
import static de.blackyellow.tennis.db.DatabaseHandler.speichereNeueSaite;
import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;
import static de.blackyellow.tennis.util.ServletUtil.getParameter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.blackyellow.tennis.saite.Saite;

public class SaitenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      ArrayList<Saite> saiten = liefereSaiten();
	      Gson gson = new Gson();
	      String mainObject = gson.toJson(saiten);

	      System.out.println(mainObject);
	      
	      resp.setContentType("application/json");
	      resp.setCharacterEncoding("UTF-8");
	      resp.getWriter().write(mainObject);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getParameter(req, "id");
		String saiteParam = getParameter(req,"saite");
		if(!id.isEmpty())
		{
			if(saiteParam != null && !saiteParam.isEmpty())
			{
				Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
				aktualisiereSaite(fromJson);
			}
			else
			{
				int saiteId = Integer.parseInt(id);
				Saite saite = liefereSaite(saiteId);
				Gson gson = new Gson();
				String json = gson.toJson(saite);
				System.out.println(json);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}
		}
		else if(saiteParam != null && !saiteParam.isEmpty())
		{
			Saite fromJson = createObjectFromJson(saiteParam, Saite.class);
			speichereNeueSaite(fromJson);
		}
	}
}
