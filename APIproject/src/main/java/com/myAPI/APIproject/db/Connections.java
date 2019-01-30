package com.myAPI.APIproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class Connections {
	private Connection conn;
	@Value("${host}")
	private String host;
	@Value("${db}")
	private String db;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	@PostConstruct
	public Connection init() {
		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://" + host + "/" + db + "?" + "user=" + user + "&password=" + password);
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
		return conn;
	}
	
	
}
