package edu.northeastern.cs5200.models;

import java.sql.Date;

public class User extends Person{
	private boolean user_agreement;
	
	public User(int id, String first_name, String last_name) {
		super(id, first_name, last_name);
		this.user_agreement = true;
	}

	public User(int id, String first_name, String last_name, String password, String username, String email, Date dob) {
		super(id, first_name, last_name, username, password, email, dob);
		this.user_agreement = true;
	}

	public boolean getUser_agreement() {
		return user_agreement;
	}
	
	public void setUser_agreement(boolean user_agreement) {
		this.user_agreement = user_agreement;
	}
	
}
