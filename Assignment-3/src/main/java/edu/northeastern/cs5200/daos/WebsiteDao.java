package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Website;
import java.util.Collection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WebsiteDao {
		private static WebsiteDao instance = null;
		private WebsiteDao() {}
		public static WebsiteDao getInstance() {
			if(instance == null)
				instance = new WebsiteDao();
			return instance;
		}
	private static String CREATE_WEBSITE =
		"INSERT INTO website (id, name, description, created, updated, visits) VALUES (?, ?, ?, ?, ?, ?)";

	public void createWebsiteForDeveloper(int developerId, Website website){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(CREATE_WEBSITE);
			statement.setInt(1, website.getId());
			statement.setString(2, website.getName());
			statement.setString(3, website.getDescription());
			statement.setDate(4, website.getCreated());
			statement.setDate(5, website.getUpdated());
			statement.setInt(6, website.getVisits());
			//statement.setInt(7, website.getDeveloperId());
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
	private static String FIND_ALL_WEBSITES =
			"SELECT * FROM website";

	public Collection<Website> findAllWebsites(){
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		Collection<Website> websites = new ArrayList<Website>();

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WEBSITES);

			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId = -1;
				Website web = new Website(id, name, description, created, updated, visits, developerId);
				websites.add(web);
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
		return websites;

	}

	private static String FIND_WEBSITES_FOR_DEVELOPER =
			"SELECT * FROM website, website_role WHERE website_role.website = website.id and website_role.developer = ?";

	public Collection<Website> findWebsitesForDeveloper(int developerId){
		Connection connection = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		Collection<Website> websites = new ArrayList<Website>();

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
			statement.setInt(1, developerId);
			results = statement.executeQuery();

			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId_ = -1;//results.getInt("developerId");
				Website web = new Website(id, name, description, created, updated, visits, developerId_);
				websites.add(web);
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
		return websites;
	}

	private static String FIND_WEBSITE_BY_ID =
			"SELECT * FROM website WHERE website.id = ?";

	public Website findWebsiteById(int websiteId){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		Website web = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			statement.setInt(1, websiteId);
			results = statement.executeQuery();
			results.next();//???
			int id = results.getInt("id");
			String name = results.getString("name");
			String description = results.getString("description");
			Date created = results.getDate("created");
			Date updated = results.getDate("updated");
			int visits = results.getInt("visits");
			int developerId = -1;//results.getInt("developerId");
			web = new Website(id, name, description, created, updated, visits, developerId);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return web;

	}

	private static String UPDATE_WEBSITE =
			"UPDATE website SET website.id = ?, website.name = ?, website.description = ?, website.created = ?, website.updated = ?, " 
			+"website.visits = ? WHERE website.id = ?";	

	public int updateWebsite(int websiteId, Website website){
		Connection connection = null;
		PreparedStatement statement = null;
		int resultp = 0;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(UPDATE_WEBSITE);
			statement.setInt(1, website.getId());
			statement.setString(2, website.getName());
			statement.setString(3, website.getDescription());
			statement.setDate(4, website.getCreated());
			statement.setDate(5, website.getUpdated());
			statement.setInt(6, website.getVisits());
			//statement.setInt(7, website.getDeveloperId());
			statement.setInt(7, websiteId);
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

	private static String DELETE_WEBISTE =
			"DELETE FROM website WHERE website.id = ?";

	public int deleteWebsite(int websiteId){
		Connection connection = null;
		PreparedStatement statement = null;
		int resultp = 0;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(DELETE_WEBISTE);
			statement.setInt(1, websiteId);
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
