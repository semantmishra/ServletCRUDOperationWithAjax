package com.crud.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static Connection conn=null;
	
	public static Connection getConnetion() {
		if(conn!=null) {
			return conn;
		}else {
			try {			
				Class.forName(DBInfo.DRIVER_NAME);
				conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER_NAME,DBInfo.PASSWORD);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
	}
}
