package de.blackyellow.tennis.json;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.blackyellow.tennis.schlaeger.Marken;

public class MarkenServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087839491542482974L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Marken[] values = Marken.values();
		ArrayList<String> marken = new ArrayList<String>();
		for (Marken marke : values) {
			marken.add(marke.toString());
		}
		Gson gson = new Gson();
		String mainObject = gson.toJson(marken);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(mainObject);
	}

}
