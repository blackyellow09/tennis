package de.blackyellow.tennis.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.saite.Saite;
import de.blackyellow.tennis.schlaeger.Schlaeger;
import de.blackyellow.tennis.util.ErrorConstants;

public class DatabaseHandler {

	private static Logger logger = Logger.getLogger(DatabaseHandler.class);
	private static Notification notification;
	
	public static BeanItemContainer<Kunde> readAllKunden()
	{
		ResultSet resultSet = null;
		BeanItemContainer<Kunde> colKunde = new BeanItemContainer<Kunde>(Kunde.class);
		try {
			Connection connection = DBConnection.getDBConnection();
			if(connection != null)
			{
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM Kunden;");
				
					resultSet = preparedStatement.executeQuery();
					
					while(resultSet.next())
					{
						int kundennummer = resultSet.getInt(1);
						String vorname = resultSet.getString(2);
						String nachname = resultSet.getString(3);
						colKunde.addItem(new Kunde(kundennummer, vorname, nachname));
					}
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_KUNDEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_KUNDEN.toString());
			notification.show(Page.getCurrent());
		}
		return colKunde;
	}

	public static Kunde liefereKunde(int kundennummer) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return null;
		}
		Kunde kunde = null;
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM Kunden WHERE id = ?;");
			preparedStatement.setInt(1, kundennummer);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String vorname = resultSet.getString(2);
				String nachname = resultSet.getString(3);
				kunde = new Kunde(kundennummer, vorname, nachname);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_KUNDE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_KUNDE.toString());
			notification.show(Page.getCurrent());
		}
		return kunde;
	}
	
	public static ArrayList<Schlaeger> liefereSchlaegernamen()
	{
		Connection connection = DBConnection.getDBConnection();
		ArrayList<Schlaeger> listSchlaeger = new ArrayList<Schlaeger>();
		if(connection == null)
		{
			return listSchlaeger;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT ID, Marke, Bezeichnung FROM schlaegermodelle;");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int modellNr = resultSet.getInt(1);
				String marke = resultSet.getString(2);
				String bezeichnung = resultSet.getString(3);
				Schlaeger schlaeger = new Schlaeger(modellNr, marke, bezeichnung);
				listSchlaeger.add(schlaeger);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN.toString());
			notification.show(Page.getCurrent());
		}
		return listSchlaeger;
	}
	
	public static Schlaeger liefereSchlaegermodell(int id)
	{
		Connection connection = DBConnection.getDBConnection();
		Schlaeger schlaeger = null;
		if(connection == null)
		{
			return null;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM schlaegermodelle WHERE ID = ?;");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String marke = resultSet.getString(2);
				String bezeichnung = resultSet.getString(3);
				int mains = resultSet.getInt(4);
				int crosses = resultSet.getInt(5);
				int kopfgroesse = resultSet.getInt(6);
				double gewicht = resultSet.getDouble(7);
				double seitenlaenge = resultSet.getDouble(8);
				double seitenlaengeOpt = resultSet.getDouble(9);
				schlaeger = new Schlaeger(id, marke, bezeichnung, mains, crosses, 
						kopfgroesse, gewicht, seitenlaenge, seitenlaengeOpt);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN.toString());
			notification.show(Page.getCurrent());
		}
		return schlaeger;
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
				Saite saite = new Saite(id, marke, bezeichnung, typ);
				listSaite.add(saite);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SAITEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SAITEN.toString());
			notification.show(Page.getCurrent());
		}
		return listSaite;
	}
	
	public static ArrayList<Bespannung> liefereBespannung(int schlaegerId)
	{
		Connection connection = DBConnection.getDBConnection();
		ArrayList<Bespannung> listBespannung = new ArrayList<Bespannung>();
		if(connection == null)
		{
			return listBespannung;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM bespannung WHERE schlaeger = ? ;");
			preparedStatement.setInt(1, schlaegerId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("bespannung.id");
				Date datum = resultSet.getDate(3);
				int dt = resultSet.getInt(4);
				int kgLaengs = resultSet.getInt(5);
				int kgQuer = resultSet.getInt(6);
				Bespannung bespannung = new Bespannung(id, datum, dt, kgLaengs, kgQuer);
				bespannung.setPreis(resultSet.getBigDecimal(7));
				listBespannung.add(bespannung);
			}
			for (Bespannung bespannung : listBespannung) {
				bespannung.setSaite(liefereSaite(connection, bespannung.getId()));
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_BESPANNUNG, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_BESPANNUNG.toString());
			notification.show(Page.getCurrent());
		}
		return listBespannung;
	}

	private static Saite liefereSaite(Connection connection, int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT bespannung.id, saiten.* FROM bespannung, saiten WHERE bespannung.id = ? "
					+ "AND bespannung.saite = saiten.id;");
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				return new Saite(resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SAITE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SAITE.toString());
			notification.show(Page.getCurrent());
		}	
		return null;
	}

	public static ArrayList<BespannungKurzItem> liefereSchlaegerZuKunde(int kundennummer) {
		Connection connection = DBConnection.getDBConnection();
		ArrayList<Schlaeger> listSchlaeger = new ArrayList<Schlaeger>();
		ArrayList<BespannungKurzItem> listKurzItems = new ArrayList<BespannungKurzItem>();
		if(connection == null)
		{
			return null;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT schlaeger.id, schlaegermodelle.*, schlaeger.nr FROM `schlaeger` , schlaegermodelle WHERE `Kunde` = ? AND `Modell` = schlaegermodelle.id;");
			preparedStatement.setInt(1, kundennummer);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int modellId = resultSet.getInt(2);
				String marke = resultSet.getString(3);
				String bezeichnung = resultSet.getString(4);
				int mains = resultSet.getInt(5);
				int crosses = resultSet.getInt(6);
				int kopfgroesse = resultSet.getInt(7);
				double gewicht = resultSet.getDouble(8);
				double seitenlaenge = resultSet.getDouble(9);
				double seitenlaengeOpt = resultSet.getDouble(10);
				int nr = resultSet.getInt(11);
				listSchlaeger.add(new Schlaeger(id, modellId, kundennummer, nr, marke, bezeichnung, mains, crosses, 
						kopfgroesse, gewicht, seitenlaenge, seitenlaengeOpt));
			}
			
			for (Schlaeger schlaeger : listSchlaeger) {
				int schlaegerId = schlaeger.getSchlaegerId();
				preparedStatement = connection.prepareStatement("SELECT * FROM bespannung WHERE  schlaeger = ? AND datum = (SELECT MAX(Datum) FROM bespannung WHERE  schlaeger = ?);");
				preparedStatement.setInt(1, schlaegerId);
				preparedStatement.setInt(2, schlaegerId);
				ResultSet resultSet2 = preparedStatement.executeQuery();
				Bespannung bespannung = null;
				while (resultSet2.next()) {
					int id = resultSet2.getInt("bespannung.id");
					Date datum = resultSet2.getDate(3);
					int dt = resultSet2.getInt(4);
					int kgLaengs = resultSet2.getInt(5);
					int kgQuer = resultSet2.getInt(6);
					bespannung = new Bespannung(id, datum, dt, kgLaengs, kgQuer);
					bespannung.setPreis(resultSet2.getBigDecimal(7));
				}
				listKurzItems.add(new BespannungKurzItem(schlaeger, bespannung));
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGER_ZU_KUNDE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGER_ZU_KUNDE.toString());
			notification.show(Page.getCurrent());
		}
		return listKurzItems;
		
	}
	
	public static Schlaeger liefereSchlaeger(int schlaegerNr) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return null;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT schlaeger.id, schlaeger.nr, schlaegermodelle.* FROM `schlaeger` , schlaegermodelle WHERE schlaeger.id = ? AND `Modell` = schlaegermodelle.id;");
			preparedStatement.setInt(1, schlaegerNr);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("schlaegermodelle.id");
				String marke = resultSet.getString("schlaegermodelle.marke");
				String bezeichnung = resultSet.getString("schlaegermodelle.bezeichnung");
				Schlaeger schlaeger = new Schlaeger(id, marke, bezeichnung);
				schlaeger.setSchlaegerNr(resultSet.getInt("schlaeger.nr"));
				schlaeger.setSchlaegerId(resultSet.getInt("schlaeger.id"));
				return schlaeger;
			}
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGER, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGER.toString());
			notification.show(Page.getCurrent());
		}
		return null;
		
	}

	public static boolean speichereNeuenSchlaeger(int kundennummer, int modellNr, Bespannung bespannung) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			int schlaegerNr = ermittleSchlaegerNr(connection, kundennummer);
			preparedStatement = connection.prepareStatement("INSERT INTO schlaeger (NR, Kunde, Modell) VALUES(?, ?, ?);");
			preparedStatement.setInt(1, schlaegerNr);
			preparedStatement.setInt(2, kundennummer);
			preparedStatement.setInt(3, modellNr);
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet > 0)
			{
				int schlaegerId = liefereSchlaegerId(connection, schlaegerNr, kundennummer);
				if(schlaegerId > 0)
				{
					speichereBespannung(bespannung, schlaegerId, connection);
				}
				else
				{
					logger.error(ErrorConstants.FEHLER_SPEICHERE_BESPANNUNG_ZU_NEUEN_SCHLAEGER);
					notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_BESPANNUNG_ZU_NEUEN_SCHLAEGER.toString());
					notification.show(Page.getCurrent());
				}
			}
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUEN_SCHLAEGER, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_NEUEN_SCHLAEGER.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	private static int liefereSchlaegerId(Connection connection, int schlaegerNr, int kundennummer) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT ID FROM `schlaeger` WHERE kunde = ? AND nr = ?;");
			preparedStatement.setInt(1, kundennummer);
			preparedStatement.setInt(2, schlaegerNr);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_BESPANNUNG_ZU_NEUEN_SCHLAEGER, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_BESPANNUNG_ZU_NEUEN_SCHLAEGER.toString());
			notification.show(Page.getCurrent());
		}
		
		return 0;
	}

	private static int ermittleSchlaegerNr(Connection connection, int kundennummer) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(NR) FROM `schlaeger` WHERE kunde = ?;");
		preparedStatement.setInt(1, kundennummer);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int hoechsteNr = resultSet.getInt(1);
			return hoechsteNr+1;
		}
		return 1;
	}

	public static boolean speichereNeueBespannung(Bespannung neueBespannung, int kundennummer, int schlaegerNr) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		return speichereBespannung(neueBespannung, schlaegerNr, connection);
	}

	private static boolean speichereBespannung(Bespannung neueBespannung, int schlaegerNr, Connection connection) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO bespannung (Schlaeger, Datum, DT, kgLaengs, kgQuer, Preis, Saite) VALUES(?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setInt(1, schlaegerNr);
			preparedStatement.setDate(2, neueBespannung.getDatum());
			preparedStatement.setInt(3, neueBespannung.getDt());
			preparedStatement.setInt(4, neueBespannung.getLaengs());
			preparedStatement.setInt(5, neueBespannung.getQuer());
			preparedStatement.setBigDecimal(6, neueBespannung.getPreis());
			if(neueBespannung.getSaite() != null)
			{
				preparedStatement.setInt(7, neueBespannung.getSaite().getId());
			}
			else
			{
				preparedStatement.setInt(7, 0);
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUE_BESPANNUNG, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_NEUE_BESPANNUNG.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static boolean updateBespannung(Bespannung aktuellsteBespannung, int kundennummer, int schlaegerNr) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE bespannung SET Schlaeger = ?, Datum = ?, DT = ?, kgLaengs = ?, kgQuer = ?, Preis = ?, Saite = ? WHERE ID = ?;");
			preparedStatement.setInt(1, schlaegerNr);
			preparedStatement.setDate(2, aktuellsteBespannung.getDatum());
			preparedStatement.setInt(3, aktuellsteBespannung.getDt());
			preparedStatement.setInt(4, aktuellsteBespannung.getLaengs());
			preparedStatement.setInt(5, aktuellsteBespannung.getQuer());
			preparedStatement.setBigDecimal(6, aktuellsteBespannung.getPreis());
			if(aktuellsteBespannung.getSaite() != null)
			{
				preparedStatement.setInt(7, aktuellsteBespannung.getSaite().getId());
			}
			else
			{
				preparedStatement.setInt(7, 0);
			}
			preparedStatement.setInt(8, aktuellsteBespannung.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_UPDATE_BESPANNUNG, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_UPDATE_BESPANNUNG.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static boolean speichereNeuenKunden(Kunde kunde) {
		// INSERT INTO `tennis`.`kunden` (`ID`, `Vorname`, `Nachname`) VALUES (NULL, 'Maria', 'Müller');
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `tennis`.`kunden` (`Vorname`, `Nachname`) VALUES (?, ?);");
			preparedStatement.setString(1, kunde.getVorname());
			preparedStatement.setString(2, kunde.getNachname());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUEN_KUNDEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_NEUEN_KUNDEN.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static boolean speichereNeuesSchlaegermodell(Schlaeger schlaeger) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `tennis`.`schlaegermodelle` (`Marke`, `Bezeichnung`, `Mains`, `Crosses`, `Kopfgroesse`, `Gewicht`, `empfSeitenlaenge`, `optSeitenlaenge`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, schlaeger.getMarke());
			preparedStatement.setString(2, schlaeger.getBezeichnung());
			preparedStatement.setInt(3, schlaeger.getMains());
			preparedStatement.setInt(4, schlaeger.getCrosses());
			preparedStatement.setInt(5, schlaeger.getKopfgroesse());
			preparedStatement.setDouble(6, schlaeger.getGewicht());
			preparedStatement.setDouble(7, schlaeger.getSeitelaenge());
			preparedStatement.setDouble(8, schlaeger.getSeitenlaengeOpt());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUES_SCHLAEGERMODELL, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_NEUES_SCHLAEGERMODELL.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static boolean aktualisiereSchlaegermodell(Schlaeger schlaeger) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE `tennis`.`schlaegermodelle` SET Marke = ?, Bezeichnung = ?, Mains = ?, Crosses = ?, Kopfgroesse = ?, Gewicht = ?, empfSeitenlaenge = ?, optSeitenlaenge = ? WHERE ID = ?;");
			preparedStatement.setString(1, schlaeger.getMarke());
			preparedStatement.setString(2, schlaeger.getBezeichnung());
			preparedStatement.setInt(3, schlaeger.getMains());
			preparedStatement.setInt(4, schlaeger.getCrosses());
			preparedStatement.setInt(5, schlaeger.getKopfgroesse());
			preparedStatement.setDouble(6, schlaeger.getGewicht());
			preparedStatement.setDouble(7, schlaeger.getSeitelaenge());
			preparedStatement.setDouble(8, schlaeger.getSeitenlaengeOpt());
			preparedStatement.setInt(9, schlaeger.getModellNr());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_UPDATE_SCHLAEGERMODELL, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_UPDATE_SCHLAEGERMODELL.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static Saite liefereSaite(int id) {
		Connection connection = DBConnection.getDBConnection();
		Saite saite = null;
		if(connection == null)
		{
			return null;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM saiten WHERE ID = ?;");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String marke = resultSet.getString(2);
				String bezeichnung = resultSet.getString(3);
				String typ = resultSet.getString(4);
				saite = new Saite(id, marke, bezeichnung, typ);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SAITE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SAITE.toString());
			notification.show(Page.getCurrent());
		}
		return saite;
	}

	public static boolean speichereNeueSaite(Saite saite) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `tennis`.`saiten` (`Marke`, `Bezeichnung`, Typ) VALUES (?, ?, ?);");
			preparedStatement.setString(1, saite.getMarke());
			preparedStatement.setString(2, saite.getBezeichnung());
			preparedStatement.setString(3, saite.getTyp());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_SPEICHERE_NEUE_SAITE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_SPEICHERE_NEUE_SAITE.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}

	public static boolean aktualisiereSaite(Saite saite) {
		Connection connection = DBConnection.getDBConnection();
		if(connection == null)
		{
			return false;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE `tennis`.`saiten` SET Marke = ?, Bezeichnung = ?, Typ = ? WHERE ID = ?;");
			preparedStatement.setString(1, saite.getMarke());
			preparedStatement.setString(2, saite.getBezeichnung());
			preparedStatement.setString(3, saite.getTyp());
			preparedStatement.setInt(4, saite.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_UPDATE_SAITE, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_UPDATE_SAITE.toString());
			notification.show(Page.getCurrent());
			return false;
		}
		return true;
	}
}
