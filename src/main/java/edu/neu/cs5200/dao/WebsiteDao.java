package edu.neu.cs5200.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.neu.cs5200.models.Website;
import edu.neu.cs5200.models.Enum.RoleEnum;
import edu.neu.cs5200.models.Page;

public class WebsiteDao extends BaseDao {

	private static WebsiteDao instance = null;

	private WebsiteDao() {
	}

	public static WebsiteDao getInstance() {
		if (instance == null)
			instance = new WebsiteDao();
		return instance;
	}

	final String CREATE_WEBSITE_FOR_DEVELOPER = "INSERT INTO hw3_zhang_ling_summer_2018.Website (name, description, visits, developer) VALUES(?, ?, ?, ?);";
	final String CREATE_WEBSITE_ROLE_FOR_DEVELOPER = "INSERT INTO hw3_zhang_ling_summer_2018.WebsiteRole (role, developer, website) VALUES(?, ?, ?);";
	final String FIND_ALL_WEBSITES = "SELECT * FROM hw3_zhang_ling_summer_2018.Website;";
	final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM hw3_zhang_ling_summer_2018.Website WHERE developer = ?;";
	final String FIND_WEBSITE_BY_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Website WHERE id = ?;";
	final String FIND_WEBSITE_BY_NAME = "SELECT * FROM hw3_zhang_ling_summer_2018.Website WHERE name = ?;";
	final String UPDATE_WEBSITE = "UPDATE hw3_zhang_ling_summer_2018.Website SET name = ?, description = ?, visits = ?, WHERE id = ?;";
	final String DELETE_WEBSITE = "DELETE FROM hw3_zhang_ling_summer_2018.Website WHERE id = ?;";

	public int createWebsiteForDeveloper(int developerId, Website website) {
		int websiteId = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(CREATE_WEBSITE_FOR_DEVELOPER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, website.getName());
			pstmt.setString(2, website.getDescription());
			pstmt.setInt(3, website.getVisits());
			pstmt.setInt(4, developerId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				websiteId = rs.getInt(1);
				pstmt = conn.prepareStatement(CREATE_WEBSITE_ROLE_FOR_DEVELOPER);
				pstmt.setInt(1, RoleEnum.OWNER.value());
				pstmt.setInt(2, developerId);
				pstmt.setInt(3, websiteId);
				pstmt.executeUpdate();
			}

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
		return websiteId;
	}

	public Collection<Website> findAllWebsites() {

		List<Website> websites = new ArrayList<Website>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_ALL_WEBSITES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int websiteId = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int visits = rs.getInt("visits");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int developerId = rs.getInt("developer");
				Website website = new Website(websiteId, name, description, created, updated, visits, developerId);
				PageDao pageDao = PageDao.getInstance();
				Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
				website.setPages(pages);
				websites.add(website);
			}
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
		return websites;
	}

	public Collection<Website> findWebsitesForDeveloper(int developerId) {

		List<Website> websites = new ArrayList<Website>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
			pstmt.setInt(1, developerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int websiteId = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int visits = rs.getInt("visits");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				Website website = new Website(websiteId, name, description, created, updated, visits, developerId);
				PageDao pageDao = PageDao.getInstance();
				Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
				website.setPages(pages);
				websites.add(website);
			}
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
		return websites;
	}

	public Website findWebsiteById(int websiteId) {
		Website website = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WEBSITE_BY_ID);
			pstmt.setInt(1, websiteId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				int visits = rs.getInt("visits");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int developerId = rs.getInt("developer");
				website = new Website(websiteId, name, description, created, updated, visits, developerId);
				PageDao pageDao = PageDao.getInstance();
				Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
				website.setPages(pages);
			}
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
		return website;
	}

	public Website findWebsiteByName(String name) {
		Website website = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WEBSITE_BY_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int websiteId = rs.getInt("id");
				String description = rs.getString("description");
				int visits = rs.getInt("visits");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int developerId = rs.getInt("developer");
				website = new Website(websiteId, name, description, created, updated, visits, developerId);
				PageDao pageDao = PageDao.getInstance();
				Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
				website.setPages(pages);
			}
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
		return website;
	}

	public int updateWebsite(int websiteId, Website website) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(UPDATE_WEBSITE);
			pstmt.setString(1, website.getName());
			pstmt.setString(2, website.getDescription());
			pstmt.setInt(3, website.getVisits());
			pstmt.setInt(4, websiteId);
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

	public int deleteWebsite(int websiteId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_WEBSITE);
			pstmt.setInt(1, websiteId);
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
