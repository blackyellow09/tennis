package de.blackyellow.tennis.db;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;

import de.blackyellow.tennis.util.ErrorConstants;

public class DBConnection {

private static Logger logger = Logger.getLogger(DBConnection.class);
    
	public static Connection getDBConnection()
	{
		String basepath = "/localhost";
		Properties props = new Properties();
		try {
			ClassResource externalRes = new ClassResource("dbsettings.properties");
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			BufferedInputStream bis = new BufferedInputStream(classLoader.getResourceAsStream("dbsettings.properties"));
			props.load(bis);
			bis.close();
		} catch (FileNotFoundException e1) {
			logger.error(ErrorConstants.FEHLER_LESEN_PROPERTIES, e1);
		} catch (IOException e) {
			logger.error(ErrorConstants.FEHLER_LESEN_PROPERTIES, e);
		}
		
		String host;
		String username;
		String password;
		if(basepath.equals("/localhost"))
		{
			String getenv = System.getenv("MYSQL_SERVICE_HOST");
			host = "jdbc:mysql://"+getenv+":3306/tennis";
	        username = props.getProperty("username").toString();
	        password = props.getProperty("password").toString();
		}
		else
		{
			host = "jdbc:mysql://localhost:3306/tennis";
	        username = "root";
	        password = "";
		}
        String driver = "com.mysql.jdbc.Driver";
		
		
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(host, username, password);

		} catch (ClassNotFoundException e) {
			logger.error(ErrorConstants.FEHLER_DB_CONNECTION, e);
		} catch (SQLException e) {
			logger.error(ErrorConstants.FEHLER_DB_CONNECTION, e);
		}
		return connection;
	}
}
