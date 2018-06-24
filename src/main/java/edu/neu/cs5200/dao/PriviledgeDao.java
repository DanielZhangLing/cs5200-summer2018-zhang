package edu.neu.cs5200.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PriviledgeDao extends BaseDao{

	private static PriviledgeDao instance = null;

	private PriviledgeDao() {
	}

	public static PriviledgeDao getInstance() {
		if (instance == null)
			instance = new PriviledgeDao();
		return instance;
	}
	
	final String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO hw3_zhang_ling_summer_2018.WebsitePriviledge (priviledge, developer, website) VALUES (? ,? ,? );";
	final String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO hw3_zhang_ling_summer_2018.PagePriviledge (priviledge, developer, page) VALUES (? ,? ,? );";
	final String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM hw3_zhang_ling_summer_2018.WebsitePriviledge WHERE priviledge = ? AND developer = ? AND website = ?;";
	final String DELETE_PAGE_PRIVILEDGE = "DELETE FROM hw3_zhang_ling_summer_2018.PagePriviledge WHERE priviledge = ? AND developer = ? AND page = ?;";

	public void assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
			pstmt.setInt(1, priviledgeId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, websiteId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se2) { // ignore, can't help it
			}
		}
	}
	
	public void assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
			pstmt.setInt(1, priviledgeId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, pageId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se2) { // ignore, can't help it
			}
		}
	}
	
	public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
			pstmt.setInt(1, priviledgeId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, websiteId);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se2) { // ignore, can't help it
			}
		}
		return result;
	}
	
	public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_PAGE_PRIVILEDGE);
			pstmt.setInt(1, priviledgeId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, pageId);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se2) { // ignore, can't help it
			}
		}
		return result;
	}
}
