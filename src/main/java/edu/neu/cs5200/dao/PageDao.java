package edu.neu.cs5200.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.neu.cs5200.models.Page;
import edu.neu.cs5200.models.Widget;

public class PageDao extends BaseDao {
	private static PageDao instance = null;

	private PageDao() {
	}

	public static PageDao getInstance() {
		if (instance == null)
			instance = new PageDao();
		return instance;
	}

	final String CREATE_PAGE_FOR_WEBSITE = "INSERT INTO hw3_zhang_ling_summer_2018.Page (title, description, views, website) VALUES (?, ?, ?, ?);";
	final String FIND_ALL_PAGES = "SELECT * FROM hw3_zhang_ling_summer_2018.Page;";
	final String FIND_PAGE_BY_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Page WHERE id = ?;";
	final String FIND_PAGE_BY_TITLE = "SELECT * FROM hw3_zhang_ling_summer_2018.Page WHERE title = ?;";
	final String FIND_PAGE_FOR_WEBSITE = "SELECT * FROM hw3_zhang_ling_summer_2018.Page WHERE website = ?;";
	final String UPDATE_PAGE = "UPDATE hw3_zhang_ling_summer_2018.Page SET title =?, description =?, views =? WHERE id =?;";
	final String DELETE_PAGE = "DELETE FROM hw3_zhang_ling_summer_2018.Page WHERE id = ?;";

	public int createPageForWebsite(int websiteId, Page page) {
		int pageId = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(CREATE_PAGE_FOR_WEBSITE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, page.getTitle());
			pstmt.setString(2, page.getDescription());
			pstmt.setInt(3, page.getViews());
			pstmt.setInt(4, websiteId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				pageId = rs.getInt(1);
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
		return pageId;
	}

	public Collection<Page> findAllPages() {

		List<Page> pages = new ArrayList<Page>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_ALL_PAGES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pageId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int views = rs.getInt("views");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int websiteId = rs.getInt("website");
				Page page = new Page(pageId, title, description, created, updated, views, websiteId);
				WidgetDao widgetDao = WidgetDao.getInstance();
				Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
				page.setWidgets(widgets);
				pages.add(page);
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
		return pages;
	}

	public Page findPageById(int pageId) {

		Page page = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PAGE_BY_ID);
			pstmt.setInt(1, pageId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("description");
				int views = rs.getInt("views");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int websiteId = rs.getInt("website");
				page = new Page(pageId, title, description, created, updated, views, websiteId);
				WidgetDao widgetDao = WidgetDao.getInstance();
				Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
				page.setWidgets(widgets);
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
		return page;
	}

	public Page findPageByTitle(String title) {

		Page page = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PAGE_BY_TITLE);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int pageId = rs.getInt("id");
				String description = rs.getString("description");
				int views = rs.getInt("views");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				int websiteId = rs.getInt("website");
				page = new Page(pageId, title, description, created, updated, views, websiteId);
				WidgetDao widgetDao = WidgetDao.getInstance();
				Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
				page.setWidgets(widgets);
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
		return page;
	}

	
	public Collection<Page> findPagesForWebsite(int websiteId) {

		List<Page> pages = new ArrayList<Page>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PAGE_FOR_WEBSITE);
			pstmt.setInt(1, websiteId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pageId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int views = rs.getInt("views");
				Date created = rs.getDate("created");
				Date updated = rs.getDate("updated");
				Page page = new Page(pageId, title, description, created, updated, views, websiteId);
				WidgetDao widgetDao = WidgetDao.getInstance();
				Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
				page.setWidgets(widgets);
				pages.add(page);
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
		return pages;
	}

	public int updatePage(int pageId, Page page) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(UPDATE_PAGE);
			pstmt.setString(1, page.getTitle());
			pstmt.setString(2, page.getDescription());
			pstmt.setInt(3, page.getViews());
			pstmt.setInt(4, pageId);
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

	public int deletePage(int pageId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_PAGE);
			pstmt.setInt(1, pageId);
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
	
//	public int deletePagesForWebsite(int websiteId) {
//		int result = 0;
//		try {
//			Collection<Page> pages = findPagesForWebsite(websiteId);
//			for(Page page : pages) {
//				result += deletePage(page.getId());
//			}
//		} catch (Exception e) {
//			e.printStackTrace(); // handle Class.forName
//		}
//		return result;
//	}

}
