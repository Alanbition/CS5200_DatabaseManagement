package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "alanbition";//System.getenv("DB_USERNAME");
    private static final String PASSWORD = "wangshijin37";//System.getenv("DB_PASSWORD");
    private static final String SCHEMA = "cs5200";//System.getenv("DB_SCHEMA");
    private static final String DB_HOSTNAME = "cs5200-fall2019-wang.c5agblqjtqma.us-east-2.rds.amazonaws.com";//System.getenv("DB_HOSTNAME");

    private static final String URL = String.format("jdbc:mysql://%s/%s", DB_HOSTNAME, SCHEMA);


    private DatabaseConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}

/*
public class DatabaseConnection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2019-wang.c5agblqjtqma.us-east-2.rds.amazonaws.com/cs5200";
	private static final String USER = "alanbition";
	private static final String PASSWORD = "wangshijin37";
	private static 	java.sql.Connection dbConnection = null;

	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName(DRIVER);

	if (dbConnection == null) {
		dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
		return dbConnection;
	} else { return dbConnection; } }
	
	public static void closeConnection() {
   	 try {
   		 if (dbConnection != null) {
		dbConnection.close();
		dbConnection = null; }
   	 } catch (SQLException e) {
   		 // TODO Auto-generated catch block
   		 e.printStackTrace();
   	 }
	}
}
*/
