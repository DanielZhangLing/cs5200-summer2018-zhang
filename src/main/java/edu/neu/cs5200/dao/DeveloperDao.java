package edu.neu.cs5200.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.neu.cs5200.models.Developer;
import edu.neu.cs5200.models.Person;
import edu.neu.cs5200.models.Website;

public class DeveloperDao extends BaseDao {

	private static DeveloperDao instance = null;

	private DeveloperDao() {
	}

	public static DeveloperDao getInstance() {
		if (instance == null)
			instance = new DeveloperDao();
		return instance;
	}

	final String CREATE_DEVELOPER = "INSERT INTO hw3_zhang_ling_summer_2018.Developer (developerKey, person) VALUES (?, ?);";
	final String FIND_ALL_DEVEVLOPERS = "SELECT * FROM hw3_zhang_ling_summer_2018.Developer;";
	final String FIND_DEVELOPER_BY_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Developer WHERE id = ?;";
	final String FIND_DEVELOPER_BY_PERSON_ID = "SELECT * FROM hw3_zhang_ling_summer_2018.Developer WHERE person = ?;";
	final String UPDATE_DEVELOPER = "UPDATE hw3_zhang_ling_summer_2018.Developer SET developerKey = ? WHERE id = ?;";
	final String DELETE_DEVELOPER = "DELETE FROM hw3_zhang_ling_summer_2018.Developer WHERE id = ?;";

	public int createDeveloper(Developer developer) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PersonDao personDao = PersonDao.getInstance();
			int personId = personDao.createPerson(developer);
			pstmt = conn.prepareStatement(CREATE_DEVELOPER);
			pstmt.setString(1, developer.getDeveloperKey());
			pstmt.setInt(2, personId);
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

	public Collection<Developer> findAllDevelopers() {

		List<Developer> developers = new ArrayList<Developer>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_ALL_DEVEVLOPERS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("person");
				PersonDao personDao = PersonDao.getInstance();
				Person person = personDao.findPersonById(id);
				int developerId = rs.getInt("id");
				String developerKey = rs.getString("developerKey");
				Developer developer = new Developer(person, developerId, developerKey);
				WebsiteDao websiteDao = WebsiteDao.getInstance();
				Collection<Website> websites = websiteDao.findWebsitesForDeveloper(developerId);
				developer.setWebsites(websites);
				developers.add(developer);
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
		return developers;
	}

	public Developer findDeveloperById(int id) {
		Developer developer = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_DEVELOPER_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String developerKey = rs.getString("developerKey");
				int personId = rs.getInt("person");
				PersonDao personDao = PersonDao.getInstance();
				Person person = personDao.findPersonById(personId);
				developer = new Developer(person, id, developerKey);
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
		return developer;
	}

	public Developer findDeveloperByPersonId(int personId) {
		Developer developer = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_DEVELOPER_BY_PERSON_ID);
			pstmt.setInt(1, personId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String developerKey = rs.getString("developerKey");
				int id = rs.getInt("id");
				PersonDao personDao = PersonDao.getInstance();
				Person person = personDao.findPersonById(personId);
				developer = new Developer(person, id, developerKey);
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
		return developer;
	}

	public Developer findDeveloperByUsername(String username) {
		Developer developer = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PersonDao personDao = PersonDao.getInstance();
			Person person = personDao.findPersonByUsername(username);
			if (person != null)
				developer = findDeveloperByPersonId(person.getId());
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
		return developer;
	}

	public Developer findDeveloperByCredentials(String username, String password) {
		Developer developer = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PersonDao personDao = PersonDao.getInstance();
			Person person = personDao.findPersonByCredentials(username, password);
			if (person != null)
				developer = findDeveloperByPersonId(person.getId());
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
		return developer;
	}

	public int updateDeveloper(int developerId, Developer developer) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PersonDao personDao = PersonDao.getInstance();
			personDao.updatePerson(developer.getId(), developer);
			pstmt = conn.prepareStatement(UPDATE_DEVELOPER);
			pstmt.setString(1, developer.getDeveloperKey());
			pstmt.setInt(7, developerId);
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

	public int deleteDeveloper(int developerId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Developer developer = findDeveloperById(developerId);
			PersonDao personDao = PersonDao.getInstance();
			personDao.deletePerson(developer.getId());
			pstmt = conn.prepareStatement(DELETE_DEVELOPER);
			pstmt.setInt(1, developerId);
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