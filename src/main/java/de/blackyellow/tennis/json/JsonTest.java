package de.blackyellow.tennis.json;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import de.blackyellow.tennis.db.DBConnection;
import de.blackyellow.tennis.saite.Saite;

public class JsonTest {

	public static void main(String[] args){
	      JSONObject obj = new JSONObject();
	      ArrayList<Saite> saiten = liefereSaiten();
	      Saite saite = saiten.get(0);
	      
	      obj.put("name", saite.getBezeichnung());
	      obj.put("marke", saite.getMarke());
	      obj.put("preis", saite.getPreis());

	      System.out.print(obj);
	   }
	
	public static ArrayList<Saite> liefereSaiten()
	{
		Connection connection = DBConnection.getDBConnection();
		ArrayList<Saite> listSaite = new ArrayList<Saite>();
		if(connection == null)
		{
			return listSaite;
		}
	PreparedStatement preparedStatement;
	try {
		preparedStatement = connection.prepareStatement("SELECT * FROM saiten;");

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String marke = resultSet.getString(2);
			String bezeichnung = resultSet.getString(3);
			String typ = resultSet.getString(4);
			BigDecimal preis = resultSet.getBigDecimal(5);
			Saite saite = new Saite(id, marke, bezeichnung, typ, preis);
			listSaite.add(saite);
		}
	} catch (SQLException e) {
//		logger.error(ErrorConstants.FEHLER_LIEFERE_SAITEN, e);
//		notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SAITEN.toString());
//		notification.show(Page.getCurrent());
	}
	return listSaite;
}
}
