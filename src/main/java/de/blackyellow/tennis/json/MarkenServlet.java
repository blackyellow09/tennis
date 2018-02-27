package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.util.ServletUtil.prepareResponse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.blackyellow.tennis.db.DatabaseHandler;
import de.blackyellow.tennis.schlaeger.Marke;

public class MarkenServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087839491542482974L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Marke> marken = DatabaseHandler.liefereMarken();
		Gson gson = new Gson();
		String mainObject = gson.toJson(marken);

		prepareResponse(resp);
		resp.getWriter().write(mainObject);
	}

}
