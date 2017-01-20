package de.blackyellow.tennis.json;

import static de.blackyellow.tennis.util.ServletUtil.getParameter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.blackyellow.tennis.db.DatabaseHandler;

public class NeuerSchlaegerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kundeId = getParameter(req,"kundenId");
		int kundenNr = Integer.parseInt(kundeId);
		String modellId = getParameter(req,"schlaegerId");
		int modellNr = Integer.parseInt(modellId);
		boolean success = DatabaseHandler.speichereNeuenSchlaeger(kundenNr, modellNr, null);
	}
}
