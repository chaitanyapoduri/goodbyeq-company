package com.beatus.goodbyeq.company.dac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("companyDBUtils")
public class CompanyDBUtils {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.aaa");
	
	public static String getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());
	}
	
	@Value(value = "${db.host:localhost}")
	private String defaultHost;
	
	@Value(value = "${db.driver:localhost}")
	private String defaultDriver;
	
	@Value(value = "${db.username:localhost}")
	private String defaultUsername;
	
	@Value(value = "${db.password:localhost}")
	private String defaultPassword;
	
	@Value(value = "${db.port:localhost}")
	private String defaultPort;

	private static final Logger logger = LoggerFactory.getLogger(CompanyDBUtils.class);
	
	public Connection getRemoteConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		logger.info("getRemoteConnection()::defaultHost:- " + defaultHost);
		Class.forName(defaultDriver);
	    String jdbcUrl = "jdbc:mysql://" + defaultHost + ":" + defaultPort + "?user=" + defaultUsername + "&password=" + defaultPassword;
	    logger.info("Getting remote connection with connection string from environment variables.");
	    connection = DriverManager.getConnection(jdbcUrl);
	    logger.info("Remote connection successful.");
	   	     
	   return connection;
	}
}