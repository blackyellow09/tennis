package de.blackyellow.tennis.db;

import static de.blackyellow.tennis.db.DatabaseHandler.speichereNeueSaite;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Singleton;

import org.apache.log4j.Logger;

import de.blackyellow.tennis.saite.Saite;
import de.blackyellow.tennis.util.ErrorConstants;

@Singleton
public class SaitenServices {

	private static Logger logger = Logger.getLogger(SaitenServices.class);
	
	
	public ArrayList<Saite> liefereSaiten()
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
			logger.error(ErrorConstants.FEHLER_LIEFERE_SAITEN, e);
		}
		return listSaite;
	}


	public Saite liefereSaite(int saiteId) {
		return DatabaseHandler.liefereSaite(saiteId);
	}
	
	public void speichereSaite(Saite fromJson) {
		speichereNeueSaite(fromJson);
	}

	public void aktualisiereSaite(Saite fromJson) {
		DatabaseHandler.aktualisiereSaite(fromJson);
	}
}
