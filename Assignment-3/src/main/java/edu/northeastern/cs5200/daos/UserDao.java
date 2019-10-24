package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.northeastern.cs5200.models.User;

public class UserDao {
	private static UserDao instance = null;
	private UserDao() {}
	public static UserDao getInstance() {
		if(instance == null)
			instance = new UserDao();
		return instance;
	}

	private static String CREATE_PERSON =
			"INSERT INTO person (id, first_name, last_name, username, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static String CREATE_USER =
			"INSERT INTO user (id, user_agreement) VALUES (?, ?)";
		
	public void createUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(CREATE_PERSON);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getFirst_name());
			statement.setString(3, user.getLast_name());
			statement.setString(4, user.getUsername());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getEmail());
			statement.setDate(7, user.getDob());
			statement.executeUpdate();
			
			statement = connection.prepareStatement(CREATE_USER);
			statement.setInt(1, user.getId());
			statement.setBoolean(2, user.getUser_agreement());
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

}
