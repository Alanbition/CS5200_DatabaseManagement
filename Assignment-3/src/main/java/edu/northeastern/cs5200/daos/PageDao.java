package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.Page;
import java.util.Collection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageDao {
		private static PageDao instance = null;
		private PageDao() {}
		public static PageDao getInstance() {
			if(instance == null)
				instance = new PageDao();
			return instance;
		}

	private static String CREATE_PAGE =
		"INSERT INTO page(id, title, description, created, updated, views, website) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
	public void createPageForWebsite(int websiteId, Page page){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(CREATE_PAGE);
			statement.setInt(1, page.getId());
			statement.setString(2, page.getTitle());
			statement.setString(3, page.getDescription());
			statement.setDate(4, page.getCreated());
			statement.setDate(5, page.getUpdated());
			statement.setInt(6, page.getViews());
			statement.setInt(7, websiteId);
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

	private static String FIND_ALL_PAGES =
			"SELECT * FROM page";
	public Collection<Page> findAllPages(){
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_PAGES);

			while(results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				pages.add(page);
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
		return pages;
	}

	private static String FIND_PAGE_BY_ID =
			"SELECT * FROM page WHERE page.id = ?";

	public Page findPageById(int pageId){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		Page page = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(FIND_PAGE_BY_ID);
			statement.setInt(1, pageId);
			results = statement.executeQuery();
			results.next();//???
			int id = results.getInt("id");
			String title = results.getString("title");
			String description = results.getString("description");
			Date created = results.getDate("created");
			Date updated = results.getDate("updated");
			int views = results.getInt("views");
			page = new Page(id, title, description, created, updated, views);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	private static String FIND_PAGES_FOR_WEBSITE =
			"SELECT * FROM page WHERE page.website = ?";

	public Collection<Page> findPagesForWebsite(int websiteId){
		Connection connection = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		Collection<Page> pages = new ArrayList<Page>();

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(FIND_PAGES_FOR_WEBSITE);
			statement.setInt(1, websiteId);
			results = statement.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				pages.add(page);
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
		return pages;
	}

	private static String UPDATE_PAGE =
			"UPDATE page SET page.id = ?, page.title = ?, page.description = ?, page.created = ?, page.updated = ?, " 
			+"page.views = ? WHERE page.id = ?";	
	public int updatePage(int pageId, Page page){
		Connection connection = null;
		PreparedStatement statement = null;
		int resultp = 0;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(UPDATE_PAGE);
			statement.setInt(1, page.getId());
			statement.setString(2, page.getTitle());
			statement.setString(3, page.getDescription());
			statement.setDate(4, page.getCreated());
			statement.setDate(5, page.getUpdated());
			statement.setInt(6, page.getViews());
			statement.setInt(7, pageId);
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
		return resultp;
	}

	private static String DELETE_PAGE =
		"DELETE FROM page WHERE page.id = ?";

	public int deletePage(int pageId){
		Connection connection = null;
		PreparedStatement statement = null;
		int resultp = 0;

		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(DELETE_PAGE);
			statement.setInt(1, pageId);
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
