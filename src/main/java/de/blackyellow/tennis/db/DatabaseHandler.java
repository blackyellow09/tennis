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

import de.blackyellow.tennis.Saite;
import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.person.Kunde;
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
			preparedStatement = connection.prepareStatement("SELECT * FROM bespannung, saiten WHERE schlaeger = ? "
					+ "AND bespannung.saite = saiten.id;");
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
				Saite saite = new Saite(resultSet.getInt(8), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
				bespannung.setSaite(saite);
				listBespannung.add(bespannung);
			}
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_BESPANNUNG, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_BESPANNUNG.toString());
			notification.show(Page.getCurrent());
		}
		return listBespannung;
	}

	public static ArrayList<BespannungKurzItem> liefereSchlaegerZuKunde(int kundennummer) {
		// Schlaeger schlaeger = DatabaseHandler.liefereSchlaeger(1);
//		Bespannung bespannung = new Bespannung(new Date(), 36, 25, 26);
//		container.addBean(new BespannungKurzItem(schlaeger, bespannung));
		/*
		 * SELECT *
FROM `schlaeger` , schlaegermodelle
WHERE `Kunde` =1
AND `Modell` = schlaegermodelle.id
		 */
		Connection connection = DBConnection.getDBConnection();
		ArrayList<Schlaeger> listSchlaeger = new ArrayList<Schlaeger>();
		ArrayList<BespannungKurzItem> listKurzItems = new ArrayList<BespannungKurzItem>();
		if(connection == null)
		{
			return null;
		}
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT schlaeger.id, schlaegermodelle.* FROM `schlaeger` , schlaegermodelle WHERE `Kunde` = ? AND `Modell` = schlaegermodelle.id;");
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
				listSchlaeger.add(new Schlaeger(id, modellId, kundennummer, marke, bezeichnung, mains, crosses, 
						kopfgroesse, gewicht, seitenlaenge, seitenlaengeOpt));
			}
			
			for (Schlaeger schlaeger : listSchlaeger) {
				int schlaegerNr = schlaeger.getSchlaegerNr();
				preparedStatement = connection.prepareStatement("SELECT * FROM bespannung WHERE  schlaeger = ? AND datum = (SELECT MAX(Datum) FROM bespannung);");
				preparedStatement.setInt(1, schlaegerNr);
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
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN.toString());
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
			preparedStatement = connection.prepareStatement("SELECT schlaeger.id, schlaegermodelle.* FROM `schlaeger` , schlaegermodelle WHERE schlaeger.id = ? AND `Modell` = schlaegermodelle.id;");
			preparedStatement.setInt(1, schlaegerNr);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("schlaegermodelle.id");
				String marke = resultSet.getString("schlaegermodelle.marke");
				String bezeichnung = resultSet.getString("schlaegermodelle.bezeichnung");
				Schlaeger schlaeger = new Schlaeger(id, marke, bezeichnung);
				schlaeger.setSchlaegerNr(resultSet.getInt("schlaeger.id"));
				return schlaeger;
			}
			
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN, e);
			notification = new Notification("Fehler!", ErrorConstants.FEHLER_LIEFERE_SCHLAEGERNAMEN.toString());
			notification.show(Page.getCurrent());
		}
		return null;
		
	}
}
