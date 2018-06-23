package edu.neu.cs5200.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RoleDao extends BaseDao{

	private static RoleDao instance = null;

	private RoleDao() {
	}

	public static RoleDao getInstance() {
		if (instance == null)
			instance = new RoleDao();
		return instance;
	}
	
	final String ASSIGN_WEBSITE_ROLE = "INSERT INTO hw2_zhang_ling_summer_2018.WebsiteRole (role, developer, website) VALUES (? ,? ,? );";
	final String ASSIGN_PAGE_ROLE = "INSERT INTO hw2_zhang_ling_summer_2018.PageRole (role, developer, page) VALUES (? ,? ,? );";
	final String DELETE_WEBSITE_ROLE = "DELETE FROM hw2_zhang_ling_summer_2018.WebsiteRole WHERE role = ? AND developer = ? AND website = ?;";
	final String DELETE_PAGE_ROLE = "DELETE FROM hw2_zhang_ling_summer_2018.PageRole WHERE role = ? AND developer = ? AND page = ?;";

	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(ASSIGN_WEBSITE_ROLE);
			pstmt.setInt(1, roleId);
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
	
	public void assignPageRole(int developerId, int pageId, int roleId) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(ASSIGN_PAGE_ROLE);
			pstmt.setInt(1, roleId);
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
	
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_WEBSITE_ROLE);
			pstmt.setInt(1, roleId);
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
	
	public int deletePageRole(int developerId, int pageId, int roleId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_PAGE_ROLE);
			pstmt.setInt(1, roleId);
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
