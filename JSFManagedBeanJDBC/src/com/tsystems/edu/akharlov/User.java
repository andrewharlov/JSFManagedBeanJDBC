package com.tsystems.edu.akharlov;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped
public class User {
	private String name;
	private String fullName;
	private String email;
	private String country;
	
	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String searchUserFullName() throws Exception {
		
		// e.g. username = akharlov
		String query = "select * from SVV_USER where USERNAME = '" + this.name + "'"; 
		
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/SVV");
		Connection connection  = ds.getConnection();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
		resultSet.next();
		String fullName = resultSet.getString("FULL_NAME");
		String email = resultSet.getString("EMAIL");
		String country = resultSet.getString("COUNTRY");
		this.fullName = fullName;
		this.email = email;
		this.country = country;
		
		statement.close();
		connection.close();
		
		if(this.fullName != null) {
			return "success";
		} else {
			return "failure";
		}
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}
}
