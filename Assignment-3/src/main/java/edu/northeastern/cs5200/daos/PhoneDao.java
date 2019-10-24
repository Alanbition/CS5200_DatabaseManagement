package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Phone;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneDao {
	private static PhoneDao instance = null;
	private PhoneDao() {}
	public static PhoneDao getInstance() {
		if(instance == null)
			instance = new PhoneDao();
		return instance;
	}
	
	private static String CREATE_PRIMARY_PHONE =
			"INSERT INTO phone (phone, `primary`, person) VALUES (?, ?, ?)";
	
	public void createPrimaryPhone(int userID, String phoneNumber) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			
			statement = connection.prepareStatement(CREATE_PRIMARY_PHONE);
			statement.setString(1, phoneNumber);
			statement.setInt(2, 1);
			statement.setInt(3, userID);
			statement.executeUpdate();			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private static String UPDATE_PRIMARY_PHONE =
			"UPDATE phone SET phone.phone = ? WHERE phone.`primary` = 1 and phone.person = ?";	
	public int updatePrimaryPhone(int userID, String phoneNumber) {
		Connection connection = null;
		PreparedStatement statement = null;
		int resultd = 0;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(UPDATE_PRIMARY_PHONE);
			statement.setString(1, phoneNumber);
			statement.setInt(2, userID);
			resultd = statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultd;		
	}
}
