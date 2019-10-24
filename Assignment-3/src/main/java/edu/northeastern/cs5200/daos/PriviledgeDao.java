package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Priviledge;
import java.util.Collection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PriviledgeDao {
		private static PriviledgeDao instance = null;
		private PriviledgeDao() {}
		public static PriviledgeDao getInstance() {
			if(instance == null)
				instance = new PriviledgeDao();
			return instance;
		}

		private static String ASSIGN_WEBSITE_PRIVILEDGE =
			"INSERT INTO website_priviledge(priviledge, website, developer) VALUES (?, ?, ?)";
		public void assignWebsitePriviledge(int developerId, int websiteId, String priviledge){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
				statement.setString(1, priviledge);
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


		private static String ASSIGN_PAGE_PRIVILEDGE =
			"INSERT INTO page_priviledge(priviledge, page, developer) VALUES (?, ?, ?)";		
		public void assignPagePriviledge(int developerId, int pageId, String priviledge){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
				statement.setString(1, priviledge);
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

		private static String DELETE_WEBSITE_PRIVILEDGE =
			"DELETE FROM website_priviledge WHERE website_priviledge.priviledge = ? and website_priviledge.website = ? and website_priviledge.developer = ?";
		public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
				statement.setString(1, priviledge);
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

		private static String DELETE_PAGE_PRIVILEDGE =
			"DELETE FROM page_priviledge WHERE page_priviledge.priviledge = ? and page_priviledge.page = ? and website_priviledge.developer = ?";
		public void deletePagePriviledge(int developerId, int pageId, String priviledge){
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
				statement.setString(1, priviledge);
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
