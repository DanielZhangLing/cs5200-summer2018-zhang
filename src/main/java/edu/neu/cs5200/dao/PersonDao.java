package edu.neu.cs5200.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.neu.cs5200.models.Person;

public class PersonDao extends BaseDao {
	private static PersonDao instance = null;

	private PersonDao() {
	}

	public static PersonDao getInstance() {
		if (instance == null)
			instance = new PersonDao();
		return instance;
	}

	final String CREATE_PERSON = "INSERT INTO hw2_zhang_ling_summer_2018.Person (firstName, lastName, username, password, email, dob) VALUES(?, ?, ?, ?, ?, ?);";
	final String FIND_PERSON_BY_ID = "SELECT * FROM hw2_zhang_ling_summer_2018.Person WHERE id = ?;";
	final String FIND_PERSON_BY_USERNAME = "SELECT * FROM hw2_zhang_ling_summer_2018.Person WHERE username = ?;";
	final String FIND_PERSON_BY_CREDENTIALS = "SELECT * FROM hw2_zhang_ling_summer_2018.Person WHERE username = ? AND password = ?;";
	final String UPDATE_PERSON = "UPDATE hw2_zhang_ling_summer_2018.Person SET firstName = ?, lastName = ?, username = ?, password = ?, email = ?, dob = ? WHERE id = ?;";
	final String DELETE_PERSON = "DELETE FROM hw2_zhang_ling_summer_2018.Person WHERE id = ?;";

	public int createPerson(Person person) {
		int personId = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(CREATE_PERSON);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setString(3, person.getUsername());
			pstmt.setString(4, person.getPassword());
			pstmt.setString(5, person.getEmail());
			pstmt.setDate(6, person.getDob());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				personId = rs.getInt("id");
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
		return personId;
	}

	public Person findPersonById(int id) {
		Person person = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PERSON_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String email = rs.getString("password");
				Date dob = rs.getDate("dob");
				person = new Person(id, firstName, lastName, username, password, email, dob);
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
		return person;
	}

	public Person findPersonByUsername(String username) {
		Person person = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PERSON_BY_USERNAME);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String password = rs.getString("password");
				String email = rs.getString("password");
				Date dob = rs.getDate("dob");
				person = new Person(id, firstName, lastName, username, password, email, dob);
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
		return person;
	}

	public Person findPersonByCredentials(String username, String password) {
		Person person = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(FIND_PERSON_BY_CREDENTIALS);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("password");
				Date dob = rs.getDate("dob");
				person = new Person(id, firstName, lastName, username, password, email, dob);
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
		return person;
	}

	public int updatePerson(int personId, Person person) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(UPDATE_PERSON);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setString(3, person.getUsername());
			pstmt.setString(4, person.getPassword());
			pstmt.setString(5, person.getEmail());
			pstmt.setDate(6, person.getDob());
			pstmt.setInt(7, personId);
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

	public int deletePerson(int personId) {
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			pstmt = conn.prepareStatement(DELETE_PERSON);
			pstmt.setInt(1, personId);
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
