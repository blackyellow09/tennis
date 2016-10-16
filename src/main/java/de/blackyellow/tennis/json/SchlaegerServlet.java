package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.db.DatabaseHandler.aktualisiereSchlaegermodell;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSchlaegermodell;
import static de.blackyellow.tennis.db.DatabaseHandler.liefereSchlaegernamen;
import static de.blackyellow.tennis.db.DatabaseHandler.speichereNeuesSchlaegermodell;
import static de.blackyellow.tennis.util.ServletUtil.createObjectFromJson;
import static de.blackyellow.tennis.util.ServletUtil.getParameter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.blackyellow.tennis.schlaeger.Schlaeger;

public class SchlaegerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      ArrayList<Schlaeger> schlaeger = liefereSchlaegernamen();
	      Gson gson = new Gson();
	      String mainObject = gson.toJson(schlaeger);

	      System.out.println(mainObject);
	      
	      resp.setContentType("application/json;charset=utf-8");
	      resp.setCharacterEncoding("UTF-8");
	      resp.getWriter().write(mainObject);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = getParameter(req, "id");
		String modellParam = getParameter(req, "modell");
		if(!id.isEmpty())
		{
			if(modellParam != null && !modellParam.isEmpty())
			{
				Schlaeger fromJson = createObjectFromJson(modellParam, Schlaeger.class);
				aktualisiereSchlaegermodell(fromJson);
			}
			else
			{
				int modellId = Integer.parseInt(id);
				Schlaeger schlaeger = liefereSchlaegermodell(modellId);
				Gson gson = new Gson();
				String json = gson.toJson(schlaeger);
				System.out.println(json);
				resp.setContentType("application/json;charset=utf-8");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}
		}
		else if(modellParam != null && !modellParam.isEmpty())
		{
			Schlaeger fromJson = createObjectFromJson(modellParam, Schlaeger.class);
			speichereNeuesSchlaegermodell(fromJson);
		}
	}
}
