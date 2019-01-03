package com.myAPI.APIproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.myAPI.APIproject.user.ToDoAppUser;

@Component
public class UserDao {
	private Connection conn;
	@Value("${host}")
	private String host;
	@Value("${db}")
	private String db;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	final private PasswordEncoder passwordEncoder;

	@Autowired
	public UserDao(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void init() {
		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://" + host + "/" + db + "?" + "user=" + user + "&password=" + password);
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}

	/** method for admin to add a supervisor */
	public void adminInsertUser(String nameIn, String passwordIn, String employeeNoIn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into Users (userId, name, password, position) values" + "('" + employeeNoIn
					+ "','" + nameIn + "','" + passwordEncoder.encode(passwordIn) + "','supervisor')");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
	}

	/** method for admin to display users by position - employee or a supervisor */
	public List<ToDoAppUser> getUsersByPosition(String positionIn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from users where position=\"" + positionIn + "\"");
			List<ToDoAppUser> newList = new ArrayList<>();
			while (rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("Name");
				String password = "Not available";
				String position = rs.getString("Position");
				ToDoAppUser newUser = new ToDoAppUser(userId, name, password, Collections.singletonList(position));
				newList.add(newUser);
			}
			return newList;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return null;
	}

	/** method for admin to delete a supervisor or user */
	public void adminDeleteUser(String employeeIdIn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from users where userid=\"" + employeeIdIn + "\";");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
	}

	/** method for supervisor to add employee */
	public void supervisorInsertUser(String nameIn, String passwordIn, String employeeNoIn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into Users (userId, name, password, position) values" + "('" + employeeNoIn
					+ "','" + nameIn + "','" + passwordEncoder.encode(passwordIn) + "','employee')");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
	}

	/** method for supervisor to view employees */
	public List<ToDoAppUser> getEmployees() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from users where position=\"employee\"");
			List<ToDoAppUser> newList = new ArrayList<>();
			while (rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("Name");
				String password = "Not available";
				String position = rs.getString("Position");
				ToDoAppUser newUser = new ToDoAppUser(userId, name, password, Collections.singletonList(position));
				newList.add(newUser);
			}
			return newList;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return null;
	}

	/** method for supervisor to delete employee */
	public void supervisorDeleteEmployee(String employeeIdIn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from users where userid=\"" + employeeIdIn + "\" AND position=\"employee\";");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
	}
}
