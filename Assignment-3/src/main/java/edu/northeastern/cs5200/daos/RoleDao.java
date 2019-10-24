package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Role;
import java.util.Collection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
		private static RoleDao instance = null;
		private RoleDao() {}
		public static RoleDao getInstance() {
			if(instance == null)
				instance = new RoleDao();
			return instance;
		}

		private static String ASSIGN_WEBSITE_ROLE =
			"INSERT INTO website_role(role, website, developer) VALUES (?, ?, ?)";

		public void assignWebsiteRole(int developerId, int websiteId, String role){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);
				statement.setString(1, role);
				statement.setInt(2, websiteId);
				statement.setInt(3, developerId);
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

		private static String ASSIGN_PAGE_ROLE =
			"INSERT INTO page_role(role, page, developer) VALUES (?, ?, ?)";		
		public void assignPageRole(int developerId, int pageId, String role){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(ASSIGN_PAGE_ROLE);
				statement.setString(1, role);
				statement.setInt(2, pageId);
				statement.setInt(3, developerId);
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

		
		private static String FIND_PAGE_ROLE =
				"SELECT * FROM page_role WHERE page_role.`page` = ? and page_role.developer = ?";		
			public String findPageRole(int developerId, int pageId){
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet result = null;
				String role = null;
				try {
					connection = DatabaseConnection.getInstance().getConnection();
					statement = connection.prepareStatement(FIND_PAGE_ROLE);
					statement.setInt(1, pageId);
					statement.setInt(2, developerId);
					result = statement.executeQuery();
					if (result.next()) {
						role = result.getString("role");
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
				return role;
			}		

		private static String UPDATE_PAGE_ROLE =
				"UPDATE page_role set page_role.`role` = ? WHERE page_role.`page` = ? and page_role.developer = ?";		
			public void updatePageRole(String newrole, int pageId, int developerId){
				Connection connection = null;
				PreparedStatement statement = null;
				try {
					connection = DatabaseConnection.getInstance().getConnection();
					statement = connection.prepareStatement(UPDATE_PAGE_ROLE);
					statement.setString(1, newrole);
					statement.setInt(2, pageId);
					statement.setInt(3, developerId);
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
		
		private static String DELETE_WEBSITE_ROLE =
			"DELETE FROM website_role WHERE website_role.id = ? and website_role.website = ? and website_role.developer = ?";
		public void deleteWebsiteRole(int developerId, int websiteId, String role){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
			statement.setString(1, role);
			statement.setInt(2, websiteId);
			statement.setInt(3, developerId);

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


		private static String DELETE_PAGE_ROLE =
			"DELETE FROM page_role WHERE page_role.id = ? and page_role.page = ? and page_role.developer = ?";

		public void deletePageRole(int developerId, int pageId, String role){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(DELETE_PAGE_ROLE);
			statement.setString(1, role);
			statement.setInt(2, pageId);
			statement.setInt(3, developerId);
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
