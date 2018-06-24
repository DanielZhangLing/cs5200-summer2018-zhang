package edu.neu.cs5200.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.neu.cs5200.models.HeadingWidget;
import edu.neu.cs5200.models.HtmlWidget;
import edu.neu.cs5200.models.ImageWidget;
import edu.neu.cs5200.models.Widget;
import edu.neu.cs5200.models.YouTubeWidget;;

public class WidgetDao extends BaseDao {
	private static WidgetDao instance = null;

	private WidgetDao() {
	}

	public static WidgetDao getInstance() {
		if (instance == null)
			instance = new WidgetDao();
		return instance;
	}

	final String CREATE_WIDGET_FOR_PAGE = "INSERT INTO hw3_zhang_ling_summer_2018.Widget (name, width, height, cssClass, cssStyle, text, Widget.order, page, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	final String CREATE_HEADING = "INSERT INTO hw3_zhang_ling_summer_2018.Heading (widget, size) VALUES (?, ?);";
	final String CREATE_HTML = "INSERT INTO hw3_zhang_ling_summer_2018.Html (widget, html) VALUES (?, ?);";
	final String CREATE_IMAGE = "INSERT INTO hw3_zhang_ling_summer_2018.Image (widget, src) VALUES (?, ?);";
	final String CREATE_YOUTUBE = "INSERT INTO hw3_zhang_ling_summer_2018.Youtube (widget, url, sharable, expandable) VALUES (?, ?, ?, ?);";
	final String FIND_ALL_WIDGETS = "SELECT * FROM hw3_zhang_ling_summer_2018.Widget;";
	final String FIND_HEADING_BY_WIDGET_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Heading WHERE widget = ?;";
	final String FIND_HTML_BY_WIDGET_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Html WHERE widget = ?;";
	final String FIND_IMAGE_BY_WIDGET_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Image WHERE widget = ?;";
	final String FIND_YOUTUBE_BY_WIDGET_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Youtube WHERE widget = ?;";
	final String FIND_WIDGET_BY_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Widget WHERE id = ?;";
	final String FIND_WIDGET_BY_NAME = "SELECT * FROM hw3_zhang_ling_summer_2018.Widget WHERE name = ?;";
	final String FIND_WIDGET_FOR_PAGE = "SELECT * FROM hw3_zhang_ling_summer_2018.Widget WHERE page = ?;";
	final String UPDATE_WIDGET = "UPDATE hw3_zhang_ling_summer_2018.Widget SET name = ?, width = ?, height = ?, cssClass = ?, cssStyle = ?, text = ?, Widget.order = ?, page = ? WHERE id = ?;";
	final String UPDATE_HEADING = "UPDATE hw3_zhang_ling_summer_2018.Heading SET size = ? WHERE widget = ?";;
	final String UPDATE_HTML = "UPDATE hw3_zhang_ling_summer_2018.Html SET html = ? WHERE widget = ?";;
	final String UPDATE_IMAGE = "UPDATE hw3_zhang_ling_summer_2018.Image SET src = ? WHERE widget = ?";;
	final String UPDATE_YOUTUBE = "UPDATE hw3_zhang_ling_summer_2018.Youtube SET url = ?, sharable = ?, expandable = ? WHERE widget = ?";;
	final String DELETE_WIDGET = "DELETE FROM hw3_zhang_ling_summer_2018.Widget WHERE id = ?;";
	final String DELETE_HEADING = "DELETE FROM hw3_zhang_ling_summer_2018.Heading WHERE widget = ?;";
	final String DELETE_HTML = "DELETE FROM hw3_zhang_ling_summer_2018.Html WHERE widget = ?;";
	final String DELETE_IMAGE = "DELETE FROM hw3_zhang_ling_summer_2018.Image WHERE widget = ?;";
	final String DELETE_YOUTUBE = "DELETE FROM hw3_zhang_ling_summer_2018.Youtube WHERE widget = ?;";

	public int createWidgetForPage(int pageId, Widget widget) {
		int widgetId = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(CREATE_WIDGET_FOR_PAGE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, widget.getName());
			pstmt.setInt(2, widget.getWidth());
			pstmt.setInt(3, widget.getHeight());
			pstmt.setString(4, widget.getCssClass());
			pstmt.setString(5, widget.getCssStyle());
			pstmt.setString(6, widget.getText());
			pstmt.setInt(7, widget.getOrder());
			pstmt.setInt(8, pageId);
			pstmt.setString(9, widget.getType());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				widgetId = rs.getInt(1);
				createTypeWidget(widgetId, widget);
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
		return widgetId;
	}

	public int createTypeWidget(int widgetId, Widget widget) {
		int typeWidgetId = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			switch (widget.getType().toUpperCase()) {
			case "HEADING":
				pstmt = conn.prepareStatement(CREATE_HEADING, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(2, ((HeadingWidget) widget).getSize());
				break;
			case "HTML":
				pstmt = conn.prepareStatement(CREATE_HTML, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(2, ((HtmlWidget) widget).getHtml());
				break;
			case "IMAGE":
				pstmt = conn.prepareStatement(CREATE_IMAGE, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(2, ((ImageWidget) widget).getSrc());
				break;
			case "YOUTUBE":
				pstmt = conn.prepareStatement(CREATE_YOUTUBE, Statement.RETURN_GENERATED_KEYS);
				YouTubeWidget ytbWidget = (YouTubeWidget) widget;
				pstmt.setString(2, ytbWidget.getUrl());
				pstmt.setBoolean(3, ytbWidget.isSharable());
				pstmt.setBoolean(4, ytbWidget.isExpandable());
				break;
			default:
				break;
			}
			pstmt.setInt(1, widgetId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				typeWidgetId = rs.getInt(1);

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
		return typeWidgetId;
	}

	public Collection<Widget> findAllWidgets() {

		List<Widget> widgets = new ArrayList<Widget>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_ALL_WIDGETS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int width = rs.getInt("width");
				int height = rs.getInt("height");
				String cssClass = rs.getString("cssClass");
				String cssStyle = rs.getString("cssStyle");
				String text = rs.getString("text");
				int order = rs.getInt("order");
				int pageId = rs.getInt("page");
				String type = rs.getString("type");
				Widget widget = findTypeWidgetByWidget(id,
						new Widget(id, name, width, height, cssClass, cssStyle, text, order, type, pageId));
				widgets.add(widget);
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
		return widgets;
	}

	public Widget findTypeWidgetByWidget(int widgetId, Widget widget) {
		String type = widget.getType();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			switch (type.toUpperCase()) {
			case "HEADING":
				pstmt = conn.prepareStatement(FIND_HEADING_BY_WIDGET_ID);
				break;
			case "HTML":
				pstmt = conn.prepareStatement(FIND_HTML_BY_WIDGET_ID);
				break;
			case "IMAGE":
				pstmt = conn.prepareStatement(FIND_IMAGE_BY_WIDGET_ID);
				break;
			case "YOUTUBE":
				pstmt = conn.prepareStatement(FIND_YOUTUBE_BY_WIDGET_ID);
				break;
			default:
				break;
			}
			pstmt.setInt(1, widgetId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				switch (type.toUpperCase()) {
				case "HEADING":
					int headingWidgetId = rs.getInt("id");
					int size = rs.getInt("size");
					return new HeadingWidget(widget, headingWidgetId, size);
				case "HTML":
					int htmlWidgetId = rs.getInt("id");
					String html = rs.getString("html");
					return new HtmlWidget(widget, htmlWidgetId, html);
				case "IMAGE":
					int imageWidgetId = rs.getInt("id");
					String src = rs.getString("src");
					return new ImageWidget(widget, imageWidgetId, src);
				case "YOUTUBE":
					int youtubeWidgetId = rs.getInt("id");
					String url = rs.getString("url");
					Boolean sharable = rs.getBoolean("sharable");
					Boolean expandable = rs.getBoolean("expandable");
					return new YouTubeWidget(widget, youtubeWidgetId, url, sharable, expandable);
				default:
					return widget;
				}
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
		return widget;
	}

	public Widget findWidgetById(int widgetId) {

		Widget widget = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WIDGET_BY_ID);
			pstmt.setInt(1, widgetId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				int width = rs.getInt("width");
				int height = rs.getInt("height");
				String cssClass = rs.getString("cssClass");
				String cssStyle = rs.getString("cssStyle");
				String text = rs.getString("text");
				int order = rs.getInt("order");
				int pageId = rs.getInt("page");
				String type = rs.getString("type");
				widget = findTypeWidgetByWidget(widgetId,
						new Widget(widgetId, name, width, height, cssClass, cssStyle, text, order, type, pageId));
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
		return widget;
	}

	public Widget findWidgetByName(String name) {

		Widget widget = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WIDGET_BY_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int widgetId = rs.getInt("id");
				int width = rs.getInt("width");
				int height = rs.getInt("height");
				String cssClass = rs.getString("cssClass");
				String cssStyle = rs.getString("cssStyle");
				String text = rs.getString("text");
				int order = rs.getInt("order");
				int pageId = rs.getInt("page");
				String type = rs.getString("type");
				widget = findTypeWidgetByWidget(widgetId,
						new Widget(widgetId, name, width, height, cssClass, cssStyle, text, order, type, pageId));
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
		return widget;
	}

	public Collection<Widget> findWidgetsForPage(int pageId) {

		List<Widget> widgets = new ArrayList<Widget>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_WIDGET_FOR_PAGE);
			pstmt.setInt(1, pageId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int widgetId = rs.getInt("id");
				String name = rs.getString("name");
				int width = rs.getInt("width");
				int height = rs.getInt("height");
				String cssClass = rs.getString("cssClass");
				String cssStyle = rs.getString("cssStyle");
				String text = rs.getString("text");
				int order = rs.getInt("order");
				String type = rs.getString("type");
				Widget widget = findTypeWidgetByWidget(widgetId,
						new Widget(widgetId, name, width, height, cssClass, cssStyle, text, order, type, pageId));
				widgets.add(widget);
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
		return widgets;
	}

	public int updateWidget(int widgetId, Widget widget, boolean updateTypeWidget) {
		int result = 0;
		try {
			if (updateTypeWidget)
				updateTypeWidget(widgetId, widget);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(UPDATE_WIDGET);
			pstmt.setString(1, widget.getName());
			pstmt.setInt(2, widget.getWidth());
			pstmt.setInt(3, widget.getHeight());
			pstmt.setString(4, widget.getCssClass());
			pstmt.setString(5, widget.getCssStyle());
			pstmt.setString(6, widget.getText());
			pstmt.setInt(7, widget.getOrder());
			pstmt.setInt(8, widget.getPageId());
			pstmt.setInt(9, widgetId);
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

	public int updateTypeWidget(int widgetId, Widget widget) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			switch (widget.getType().toUpperCase()) {
			case "HEADING":
				pstmt = conn.prepareStatement(UPDATE_HEADING);
				pstmt.setInt(1, ((HeadingWidget) widget).getSize());
				pstmt.setInt(2, widgetId);
				break;
			case "HTML":
				pstmt = conn.prepareStatement(UPDATE_HTML);
				pstmt.setString(1, ((HtmlWidget) widget).getHtml());
				pstmt.setInt(2, widgetId);
				break;
			case "IMAGE":
				pstmt = conn.prepareStatement(UPDATE_IMAGE);
				pstmt.setString(1, ((ImageWidget) widget).getSrc());
				pstmt.setInt(2, widgetId);
				break;
			case "YOUTUBE":
				pstmt = conn.prepareStatement(UPDATE_YOUTUBE);
				YouTubeWidget ytbWidget = (YouTubeWidget) widget;
				pstmt.setString(1, ytbWidget.getUrl());
				pstmt.setBoolean(2, ytbWidget.isSharable());
				pstmt.setBoolean(3, ytbWidget.isExpandable());
				pstmt.setInt(4, widgetId);
				break;
			default:
				break;
			}
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

	public int deleteWidget(int widgetId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_WIDGET);
			pstmt.setInt(1, widgetId);
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

	// public int deleteTypeWidget(int widgetId) {
	// int result = 0;
	// Widget widget = findWidgetById(widgetId);
	// try {
	// Class.forName(JDBC_DRIVER);
	// conn = DriverManager.getConnection(DB_URL, USER, PASS);
	// switch (widget.getType().toUpperCase()) {
	// case "HEADING":
	// pstmt = conn.prepareStatement(DELETE_HEADING);
	// break;
	// case "HTML":
	// pstmt = conn.prepareStatement(DELETE_HTML);
	// break;
	// case "IMAGE":
	// pstmt = conn.prepareStatement(DELETE_IMAGE);
	// break;
	// case "YOUTUBE":
	// pstmt = conn.prepareStatement(DELETE_YOUTUBE);
	// break;
	// default:
	// break;
	// }
	// pstmt.setInt(1, widgetId);
	// result = pstmt.executeUpdate();
	//
	// } catch (SQLException se) {
	// se.printStackTrace(); // handle errors for JDBC
	// } catch (Exception e) {
	// e.printStackTrace(); // handle Class.forName
	// } finally {
	// try {
	// if (conn != null)
	// conn.close();
	// } catch (SQLException se2) { // ignore, can't help it
	// }
	// }
	// return result;
	// }

	// public int deleteWidgetsForPage(int pageId) {
	// int result = 0;
	// try {
	// Collection<Widget> widgets = findWidgetsForPage(pageId);
	// for (Widget widget : widgets) {
	// result += deleteWidget(widget.getId());
	// }
	// } catch (Exception e) {
	// e.printStackTrace(); // handle Class.forName
	// }
	// return result;
	// }
}
