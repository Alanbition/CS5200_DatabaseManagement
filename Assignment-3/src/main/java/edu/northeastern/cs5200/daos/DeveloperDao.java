package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Developer;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DeveloperDao {
		private static DeveloperDao instance = null;
		private DeveloperDao() {}
		public static DeveloperDao getInstance() {
			if(instance == null)
				instance = new DeveloperDao();
			return instance;
		}



		
		private static String CREATE_PERSON =
			"INSERT INTO person (id, first_name, last_name, username, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";

		private static String CREATE_DEVELOPER =
			"INSERT INTO developer (id, developer_key) VALUES (?, ?)";

		public void createDeveloper(Developer developer) {
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(CREATE_PERSON);
				statement.setInt(1, developer.getId());
				statement.setString(2, developer.getFirst_name());
				statement.setString(3, developer.getLast_name());
				statement.setString(4, developer.getUsername());
				statement.setString(5, developer.getPassword());
				statement.setString(6, developer.getEmail());
				statement.setDate(7, developer.getDob());
				statement.executeUpdate();
				
				statement = connection.prepareStatement(CREATE_DEVELOPER);
				statement.setInt(1, developer.getId());
				statement.setString(2, developer.getDeveloper_key());
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


			
		private static String FIND_ALL_DEVELOPER =
				"SELECT * FROM developer, person WHERE person.id = developer.id";

		public Collection<Developer> findAllDevelopers() {
			Connection connection = null;
			Statement statement = null;
			ResultSet results = null;
			Collection<Developer> developers = new ArrayList<Developer>();

			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.createStatement();
				results = statement.executeQuery(FIND_ALL_DEVELOPER);

				while(results.next()) {
					int id = results.getInt("id");
					String first_name = results.getString("first_name");
					String last_name = results.getString("last_name");
					String username = results.getString("username");
					String password = results.getString("password");
					String email = results.getString("email");
					Date dob = results.getDate("dob");
					String developer_key = results.getString("developer_key");
					Developer dev = new Developer(id, first_name, last_name, developer_key, username, password, email, dob, null, null);
					developers.add(dev);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return developers;
			
		}

		private static String FIND_DEVELOPER_BY_ID =
				"SELECT * FROM developer, person WHERE person.id = developer.id and developer.id = ?";

		public Developer findDeveloperById(int developerId) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet results = null;
			Developer dev = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
				statement.setInt(1, developerId);
				results = statement.executeQuery();
				results.next();//???
				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");
				dev = new Developer(id, first_name, last_name, developer_key, username, password, email, dob, null, null);
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return dev;
		}

		private static String FIND_DEVELOPER_BY_NAME =
				"SELECT * FROM developer, person WHERE person.id = developer.id and person.username = ?";

		public Developer findDeveloperByUsername(String username) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet results = null;
			Developer dev = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(FIND_DEVELOPER_BY_NAME);
				statement.setString(1, username);
				results = statement.executeQuery();
				results.next();//???
				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String username_ = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");
				dev = new Developer(id, first_name, last_name, developer_key, username_, password, email, dob, null, null);
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return dev;		
		}

		
		
		private static String FIND_DEVELOPER_BY_CREDENTIALS =
				"SELECT * FROM developer, person WHERE person.id = developer.id and person.username = ? and person.password = ?";

		public Developer findDeveloperByCredentials(String username, String password) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet results = null;
			Developer dev = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
				statement.setString(1, username);
				statement.setString(2, password);
				results = statement.executeQuery();
				results.next();//???
				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String username_ = results.getString("username");
				String password_ = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");
				dev = new Developer(id, first_name, last_name, developer_key, username_, password_, email, dob, null, null);
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return dev;			
		}

		private static String UPDATE_PERSON =
			"UPDATE person SET person.id = ?, person.first_name = ?, person.last_name = ?, person.username = ?"
			+ "person.password = ?, person.email = ?, person.dob = ? WHERE person.id = ?";


		private static String UPDATE_DEVELOPER =
				"UPDATE developer SET developer.developer_key = ? WHERE developer.id = ?";	


		public int updateDeveloper(int developerId, Developer developer) {
			Connection connection = null;
			PreparedStatement statement = null;
			int resultp = 0;

			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(UPDATE_PERSON);
				statement.setInt(1, developer.getId());
				statement.setString(2, developer.getFirst_name());
				statement.setString(3, developer.getLast_name());
				statement.setString(4, developer.getUsername());
				statement.setString(5, developer.getPassword());
				statement.setString(6, developer.getEmail());
				statement.setDate(7, developer.getDob());
				statement.setInt(8, developerId);
				resultp = statement.executeUpdate();
				
				statement = connection.prepareStatement(UPDATE_DEVELOPER);
				statement.setString(1, developer.getDeveloper_key());
				statement.setInt(2, developerId);
				resultp = statement.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return resultp;
		}


		private static String DELETE_PERSON =
				"DELETE FROM person WHERE person.id = ?";
		private static String DELETE_DEVELOPER =
				"UPDATE FROM developer WHERE developer.id = ?";	
		public int deleteDeveloper(int developerId) {
			Connection connection = null;
			PreparedStatement statement = null;
			int resultp = 0;

			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(DELETE_PERSON);
				statement.setInt(1, developerId);
				resultp = statement.executeUpdate();
				
				statement = connection.prepareStatement(DELETE_DEVELOPER);
				statement.setInt(1, developerId);
				resultp = statement.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return resultp;
		}
		
		private static String DELETE_PRIMARY_ADDRESS =
				"DELETE FROM address WHERE address.primary = 1 and address.person = ?";

		public int deletePrimaryAddress(int developerId) {
			Connection connection = null;
			PreparedStatement statement = null;
			int resultp = 0;

			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(DELETE_PRIMARY_ADDRESS);
				statement.setInt(1, developerId);
				resultp = statement.executeUpdate();
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return resultp;
		}
}
