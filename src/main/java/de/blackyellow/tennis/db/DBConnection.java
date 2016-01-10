package de.blackyellow.tennis.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vaadin.server.VaadinService;

import de.blackyellow.tennis.util.ErrorConstants;

public class DBConnection {

private static Logger logger = Logger.getLogger(DBConnection.class);
    
	public static Connection getDBConnection()
	{
		String basepath = VaadinService.getCurrent().getBaseDirectory().getPath();//getAbsolutePath();
		System.out.println(basepath);
		Properties props = new Properties();
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/WEB-INF/dbsettings.properties"));
			props.load(bis);
			bis.close();
		} catch (FileNotFoundException e1) {
			logger.error(ErrorConstants.FEHLER_LESEN_PROPERTIES, e1);
		} catch (IOException e) {
			logger.error(ErrorConstants.FEHLER_LESEN_PROPERTIES, e);
		}
		
		String host = props.getProperty("host").toString();
        String username = props.getProperty("username").toString();
        String password = props.getProperty("password").toString();
        String driver = props.getProperty("driver").toString();
		
		
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
