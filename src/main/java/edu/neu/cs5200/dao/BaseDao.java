package edu.neu.cs5200.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-summer2018-zhang.chptv1bgwter.us-east-2.rds.amazonaws.com/cs5200_summer2018_zhang";
	final String USER = "zhang";
	final String PASS = "ZEROwstc03";
	Connection conn = null;
	PreparedStatement pstmt = null;

	public void connect() {
		if (conn != null) {
			return;
		}
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se2) { // ignore, can't help it
		}
	}
}
