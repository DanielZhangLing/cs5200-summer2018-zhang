package edu.neu.cs5200.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao extends BaseDao {

	private static RoleDao instance = null;

	private RoleDao() {
	}

	public static RoleDao getInstance() {
		if (instance == null)
			instance = new RoleDao();
		return instance;
	}

	final String ASSIGN_WEBSITE_ROLE = "INSERT INTO hw3_zhang_ling_summer_2018.WebsiteRole (role, developer, website) VALUES (? ,? ,? );";
	final String ASSIGN_PAGE_ROLE = "INSERT INTO hw3_zhang_ling_summer_2018.PageRole (role, developer, page) VALUES (? ,? ,? );";
	final String DELETE_WEBSITE_ROLE = "DELETE FROM hw3_zhang_ling_summer_2018.WebsiteRole WHERE role = ? AND developer = ? AND website = ?;";
	final String DELETE_PAGE_ROLE = "DELETE FROM hw3_zhang_ling_summer_2018.PageRole WHERE role = ? AND developer = ? AND page = ?;";
	final String FIND_ROLE_BY_PAGE_DEVELOPER = "SELECT role FROM hw3_zhang_ling_summer_2018.PageRole WHERE page = ? AND developer = ?;";
	final String UPDATE_PAGE_ROLE = "UPDATE hw3_zhang_ling_summer_2018.PageRole SET role = ? WHERE developer = ? AND page = ?;";

	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
		try {
			connect();
			pstmt = conn.prepareStatement(ASSIGN_WEBSITE_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, websiteId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} 
	}

	public void assignPageRole(int developerId, int pageId, int roleId) {
		try {
			connect();
			pstmt = conn.prepareStatement(ASSIGN_PAGE_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, pageId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		}
	}

	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = 0;
		try {
			connect();
			pstmt = conn.prepareStatement(DELETE_WEBSITE_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, websiteId);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} 
		return result;
	}

	public int deletePageRole(int developerId, int pageId, int roleId) {
		int result = 0;
		try {
			connect();
			pstmt = conn.prepareStatement(DELETE_PAGE_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, pageId);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} 
		return result;
	}

	public int findRoleByPageDeveloper(int pageId, int developerId) {
		int role = -1;
		try {
			connect();
			pstmt = conn.prepareStatement(FIND_ROLE_BY_PAGE_DEVELOPER);
			pstmt.setInt(1, pageId);
			pstmt.setInt(2, developerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getInt("role");

		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} 
		return role;
	}

	public void updatePageRole(int developerId, int pageId, int roleId) {
		try {
			connect();
			pstmt = conn.prepareStatement(UPDATE_PAGE_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, developerId);
			pstmt.setInt(3, pageId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace(); // handle errors for JDBC
		} catch (Exception e) {
			e.printStackTrace(); // handle Class.forName
		} 
	}
}
