package de.blackyellow.tennis.db;

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
	
	public boolean speichereSaite(Saite saite) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `tennis`.`saiten` (`Marke`, `Bezeichnung`, Typ, Preis) VALUES (?, ?, ?, ?);");
			preparedStatement.setString(1, saite.getMarke());
			preparedStatement.setString(2, saite.getBezeichnung());
			preparedStatement.setString(3, saite.getTyp());
			preparedStatement.setBigDecimal(4, saite.getPreis());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUE_SAITE, e);
			return false;
		}
		return true;
	}

	public boolean aktualisiereSaite(Saite saite) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE `tennis`.`saiten` SET Marke = ?, Bezeichnung = ?, Typ = ?, Preis = ? WHERE ID = ?;");
			preparedStatement.setString(1, saite.getMarke());
			preparedStatement.setString(2, saite.getBezeichnung());
			preparedStatement.setString(3, saite.getTyp());
			preparedStatement.setBigDecimal(4, saite.getPreis());
			preparedStatement.setInt(5, saite.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_UPDATE_SAITE, e);
			return false;
		}
		return true;
	}
}
