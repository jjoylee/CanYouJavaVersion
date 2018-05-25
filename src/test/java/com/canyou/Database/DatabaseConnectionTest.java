package com.canyou.Database;
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {
	 private static final String DRIVER = "com.mysql.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://127.0.0.1:3306/canyou";
	    private static final String USER = "zerock";
	    private static final String PW = "zerock";  
	    
	    @Test
	    public void testConnection() throws ClassNotFoundException {
	        Class.forName(DRIVER);
	        try(Connection conn = DriverManager.getConnection(URL, USER, PW)) {
	            System.out.println(conn);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
//	    	assertTrue(false);
	    	
	    }
}
