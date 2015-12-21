package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
    public static final String DRIVER = "org.mariadb.jdbc.Driver";
    public static final String DBURL = "jdbc:mariadb://localhost:3306/drink99";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        return conn;
    }
}
