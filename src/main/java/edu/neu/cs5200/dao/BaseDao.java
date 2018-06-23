package edu.neu.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BaseDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-summer2018-zhang.chptv1bgwter.us-east-2.rds.amazonaws.com/cs5200_summer2018_zhang";
	final String USER = "zhang";
	final String PASS = "ZEROwstc03";
	Connection conn = null;
	PreparedStatement pstmt = null;

}
