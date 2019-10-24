package edu.northeastern.cs5200.models;
import java.sql.Date;
import java.util.Collection;

public class Developer extends Person{
	private String developer_key;

	public String getDeveloper_key() {
		return developer_key;
	}
	
	public void setDeveloper_key(String developer_key) {
		this.developer_key = developer_key;
	}
	
	public Developer(int id, String first_name, String last_name, String developer_key) {
		super(id, first_name, last_name);
		this.developer_key = developer_key;
	}

	public Developer(int id, String first_name, String last_name, String developer_key, String password, String username, String email, Date dob, Collection<Phone> phone, Collection<Address> address) {
		super(id, first_name, last_name, username, password, email, dob, phone, address);
		this.developer_key = developer_key;
	}
	
	public Developer(int id, String first_name, String last_name, String developer_key, String password, String username, String email, Date dob) {
		super(id, first_name, last_name, username, password, email, dob);
		this.developer_key = developer_key;
	}
}
