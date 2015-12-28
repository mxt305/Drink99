package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DBURL = "jdbc:mariadb://localhost:3306/drink99";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return conn;
    }
}
