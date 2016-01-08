package de.blackyellow.tennis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

import de.blackyellow.tennis.person.Kunde;

public class DatabaseHandler {

	public static BeanItemContainer<Kunde> readAllKunden()
	{
		ResultSet resultSet = null;
		BeanItemContainer<Kunde> colKunde = new BeanItemContainer<Kunde>(Kunde.class);
		try {
//			JDBCConnectionPool pool = new SimpleJDBCConnectionPool(
//			        "com.mysql.jdbc.Driver",
//			        "jdbc:mysql://127.7.125.130:3306/tennis", "adminanAm5tu", "iNqanGf6WbId", 2, 5);
//			
//			TableQuery tableQuery = new TableQuery("Kunden", pool);
//			tableQuery.setVersionColumn("OPTLOCK");
//			
//			SQLContainer container = new SQLContainer(tableQuery);
//			
//			container.g
			Connection connection = getConnection();
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colKunde;
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.7.125.130:3306/tennis", "adminanAm5tu", "iNqanGf6WbId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
