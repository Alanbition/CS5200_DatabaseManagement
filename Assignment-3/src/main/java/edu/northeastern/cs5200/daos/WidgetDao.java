package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

import java.util.Collection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WidgetDao {
		private static WidgetDao instance = null;
		private WidgetDao() {}
		public static WidgetDao getInstance() {
			if(instance == null)
				instance = new WidgetDao();
			return instance;
		}

		private static String CREATE_HEADING_WIDGET =
			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page`, `type`, size)" 
				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static String CREATE_HTML_WIDGET =
			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page`, `type`, html)" 
				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static String CREATE_IMAGE_WIDGET =
			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page`, `type`, src)" 
				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static String CREATE_YOUTUBE_WIDGET =
			"INSERT INTO widget (id, `name`, width, height, css_class, css_style, `text`, `order`, `page`, `type`, url, shareble, expandable)" 
				+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		public void createWidgetForPage(int pageId, Widget widget){
		Connection connection = null;
		PreparedStatement statement = null;
		//PreparedStatement substatement = null;

		try {
			connection = DatabaseConnection.getInstance().getConnection();

			if (widget instanceof HeadingWidget) {
				statement = connection.prepareStatement(CREATE_HEADING_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setInt(9, pageId);
				statement.setString(10, "heading");
				statement.setInt(11, widget.getSize());
				
				/*
				HeadingWidget headingWidget = (HeadingWidget) widget;
				substatement = connection.prepareStatement(CREATE_HEADING_WIDGET);
			
				substatement.setInt(1, widget.getId());
				substatement.setInt(2, headingWidget.getSize());
				statement.setString(10, "heading");*/
			}else if (widget instanceof HtmlWidget) {
				statement = connection.prepareStatement(CREATE_HTML_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setInt(9, pageId);
				statement.setString(10, "html");
				statement.setString(11, widget.getHtml());
				/*
				HtmlWidget htmlWidget = (HtmlWidget) widget;
				substatement = connection.prepareStatement(CREATE_HTML_WIDGET);
				substatement.setInt(1, widget.getId());
				substatement.setString(2, htmlWidget.getHtml());
				statement.setString(10, "html");*/

			}else if (widget instanceof ImageWidget) {
				statement = connection.prepareStatement(CREATE_IMAGE_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setInt(9, pageId);
				statement.setString(10, "image");
				statement.setString(11, widget.getSrc());
				/*
				ImageWidget imageWidget = (ImageWidget) widget;
				substatement = connection.prepareStatement(CREATE_IMAGE_WIDGET);
				substatement.setInt(1, widget.getId());
				substatement.setString(2, imageWidget.getSrc());
				statement.setString(10, "image");*/

			}else if (widget instanceof YouTubeWidget) {
				statement = connection.prepareStatement(CREATE_YOUTUBE_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setInt(9, pageId);
				statement.setString(10, "youtube");
				statement.setString(11, widget.getUrl());
				statement.setBoolean(12, widget.getShareble());
				statement.setBoolean(13, widget.getExpandable());
				/*
				YouTubeWidget youtubeWidget = (YouTubeWidget) widget;
				substatement = connection.prepareStatement(CREATE_YOUTUBE_WIDGET);
				substatement.setInt(1, widget.getId());
				substatement.setString(2, youtubeWidget.getUrl());
				substatement.setBoolean(3, youtubeWidget.getShareble());
				substatement.setBoolean(4, youtubeWidget.getExpandable());
				statement.setString(10, "youtube");*/
			}

			statement.executeUpdate();
			//substatement.executeUpdate();


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

		private static String FIND_ALL_WIDGET =
				"SELECT * FROM widget";


		public Collection<Widget> findAllWidgets(){
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WIDGET);

			while(results.next()) {
				Integer id = results.getInt("id");
				String name = results.getString("name");
				Integer width = results.getInt("width");
				Integer height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				Integer order = results.getInt("order");
				String type = results.getString("type");
				Widget widget = null;

				try {
					//connection = DatabaseConnection.getInstance().getConnection();
					
					if (type.equals("heading")){
							Integer size = results.getInt("size");
							widget = new HeadingWidget(id, name, width, height, css_class, css_style, text, order, size);

					}else if(type.equals("html")){
							String html = results.getString("html");
							widget = new HtmlWidget(id, name, width, height, css_class, css_style, text, order, html);
							
					}else if(type.equals("youtube")){
						
							String url = results.getString("url");
							Boolean shareble = results.getBoolean("shareble");
							Boolean expandable = results.getBoolean("expandable");
							widget = new YouTubeWidget(id, name, width, height, css_class, css_style, text, order, url, shareble, expandable);
					
					}else if(type.equals("image")){

							String src = results.getString("src");
							widget = new ImageWidget(id, name, width, height, css_class, css_style, text, order, src);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				widgets.add(widget);
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
		return widgets;
	}


		private static String FIND_WIDGET_BY_ID =
			"SELECT * FROM widget WHERE widget.id = ?";

		public Widget findWidgetById(int widgetId){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		Widget widget = null;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(FIND_WIDGET_BY_ID);
				statement.setInt(1, widgetId);
				results = statement.executeQuery();

				if(results.next()) {
					Integer id = results.getInt("id");
					String name = results.getString("name");
					Integer width = results.getInt("width");
					Integer height = results.getInt("height");
					String css_class = results.getString("css_class");
					String css_style = results.getString("css_style");
					String text = results.getString("text");
					Integer order = results.getInt("order");
					String type = results.getString("type");
					widget = null;

					try {						
						if (type.equals("heading")){
							Integer size = results.getInt("size");
							widget = new HeadingWidget(id, name, width, height, css_class, css_style, text, order, size);

						}else if(type.equals("html")){
								String html = results.getString("html");
								widget = new HtmlWidget(id, name, width, height, css_class, css_style, text, order, html);
						
						}else if(type.equals("youtube")){
								String url = results.getString("url");
								Boolean shareble = results.getBoolean("shareble");
								Boolean expandable = results.getBoolean("expandable");
								widget = new YouTubeWidget(id, name, width, height, css_class, css_style, text, order, url, shareble, expandable);

						}else if(type.equals("image")){
								String src = results.getString("src");
								widget = new ImageWidget(id, name, width, height, css_class, css_style, text, order, src);
							}
					}catch(SQLException e) {
						e.printStackTrace();
					}
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
			return widget;
	}


	private static String FIND_WIDGET_FOR_PAGE =
			"SELECT * FROM widget WHERE widget.page = ?";

		public Collection<Widget> findWidgetsForPage(int pageId){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(FIND_WIDGET_FOR_PAGE);
				statement.setInt(1, pageId);
				results = statement.executeQuery();

				while(results.next()) {
					Integer id = results.getInt("id");
					String name = results.getString("name");
					Integer width = results.getInt("width");
					Integer height = results.getInt("height");
					String css_class = results.getString("css_class");
					String css_style = results.getString("css_style");
					String text = results.getString("text");
					Integer order = results.getInt("order");
					String type = results.getString("type");
					Widget widget = null;

					try {
						//connection = DatabaseConnection.getInstance().getConnection();
						
						if (type.equals("heading")){
								Integer size = results.getInt("size");
								widget = new HeadingWidget(id, name, width, height, css_class, css_style, text, order, size);

						}else if(type.equals("html")){
								String html = results.getString("html");
								widget = new HtmlWidget(id, name, width, height, css_class, css_style, text, order, html);
						}else if(type.equals("youtube")){
								String url = results.getString("url");
								Boolean shareble = results.getBoolean("shareble");
								Boolean expandable = results.getBoolean("expandable");
								widget = new YouTubeWidget(id, name, width, height, css_class, css_style, text, order, url, shareble, expandable);
						}else if(type.equals("image")){
								String src = results.getString("src");
								widget = new ImageWidget(id, name, width, height, css_class, css_style, text, order, src);
						}
					}catch(SQLException e) {
							e.printStackTrace();
						}
					widgets.add(widget);
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
		return widgets;
	}




		private static String UPDATE_HEADING_WIDGET =
			"UPDATE widget SET widget.id = ?, widget.`name` = ?, widget.width = ?, widget.height = ?, widget.css_class = ?, widget.css_style = ?, widget.`text` = ?, widget.`order` = ?, widget.`type` = ?, widget.size = ? WHERE widget.id = ?";
		private static String UPDATE_HTML_WIDGET =
			"UPDATE widget SET widget.id = ?, widget.`name` = ?, widget.width = ?, widget.height = ?, widget.css_class = ?, widget.css_style = ?, widget.`text` = ?, widget.`order` = ?, widget.`type` = ?, widget.html = ? WHERE widget.id = ?";

		private static String UPDATE_IMAGE_WIDGET =
			"UPDATE widget SET widget.id = ?, widget.`name` = ?, widget.width = ?, widget.height = ?, widget.css_class = ?, widget.css_style = ?, widget.`text` = ?, widget.`order` = ?, widget.`type` = ?, widget.src = ? WHERE widget.id = ?";

		private static String UPDATE_YOUTUBE_WIDGET =
			"UPDATE widget SET widget.id = ?, widget.`name` = ?, widget.width = ?, widget.height = ?, widget.css_class = ?, widget.css_style = ?, widget.`text` = ?, widget.`order` = ?, widget.`type` = ? , widget.url = ?, widget.shareble = ?, widget.expandable = ? WHERE widget.id = ?";

		public int updateWidget(int widgetId, Widget widget){
		Connection connection = null;
		PreparedStatement statement = null;
		//PreparedStatement substatement = null;
		int pass = 0;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			/*
			statement = connection.prepareStatement(UPDATE_WIDGET);
			statement.setInt(1, widgetId);
			statement.setString(2, widget.getName());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCss_class());
			statement.setString(6, widget.getCss_style());
			statement.setString(7, widget.getText());
			statement.setInt(8, widget.getOrder());*/
			//statement.setInt(9, pageId);

			if (widget instanceof HeadingWidget) {
				statement = connection.prepareStatement(UPDATE_HEADING_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setString(9, "heading");
				statement.setInt(10, widget.getSize());
				statement.setInt(11, widgetId);
				/*
				HeadingWidget headingWidget = (HeadingWidget) widget;
				substatement = connection.prepareStatement(UPDATE_HEADING_WIDGET);
				substatement.setInt(1, widgetId);
				substatement.setInt(2, headingWidget.getSize());
				substatement.setInt(3, widgetId);
				statement.setString(9, "heading");*/
			}else if (widget instanceof HtmlWidget) {
				statement = connection.prepareStatement(UPDATE_HTML_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setString(9, "html");
				statement.setString(10, widget.getHtml());
				statement.setInt(11, widgetId);
				/*
				HtmlWidget htmlWidget = (HtmlWidget) widget;
				substatement = connection.prepareStatement(UPDATE_HTML_WIDGET);
				substatement.setInt(1, widgetId);
				substatement.setString(2, htmlWidget.getHtml());
				substatement.setInt(3, widgetId);
				statement.setString(9, "html");*/

			}else if (widget instanceof ImageWidget) {
				statement = connection.prepareStatement(UPDATE_IMAGE_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setString(9, "image");
				statement.setString(10, widget.getSrc());
				statement.setInt(11, widgetId);
				/*
				ImageWidget imageWidget = (ImageWidget) widget;
				substatement = connection.prepareStatement(UPDATE_IMAGE_WIDGET);
				substatement.setInt(1, widgetId);
				substatement.setString(2, imageWidget.getSrc());
				substatement.setInt(3, widgetId);
				statement.setString(9, "image");*/

			}else if (widget instanceof YouTubeWidget) {
				statement = connection.prepareStatement(UPDATE_YOUTUBE_WIDGET);
				statement.setInt(1, widget.getId());
				statement.setString(2, widget.getName());
				statement.setInt(3, widget.getWidth());
				statement.setInt(4, widget.getHeight());
				statement.setString(5, widget.getCss_class());
				statement.setString(6, widget.getCss_style());
				statement.setString(7, widget.getText());
				statement.setInt(8, widget.getOrder());
				statement.setString(9, "youtube");
				statement.setString(10, widget.getUrl());
				statement.setBoolean(11, widget.getShareble());
				statement.setBoolean(12, widget.getExpandable());
				statement.setInt(13, widgetId);

				/*
				YouTubeWidget youtubeWidget = (YouTubeWidget) widget;
				substatement = connection.prepareStatement(UPDATE_YOUTUBE_WIDGET);
				substatement.setInt(1, widgetId);
				substatement.setString(2, youtubeWidget.getUrl());
				substatement.setBoolean(3, youtubeWidget.getShareble());
				substatement.setBoolean(4, youtubeWidget.getExpandable());
				substatement.setInt(5, widgetId);
				statement.setString(9, "youtube");*/
			}
			//statement.setInt(10, widgetId);
			pass = statement.executeUpdate();
			//pass = substatement.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pass;
		}


		private static String DELETE_WIDGET =
			"DELETE FROM widget WHERE widget.id = ?";

		public int deleteWidget(int widgetId){
			Connection connection = null;
			PreparedStatement statement = null;
			PreparedStatement substatement = null;
			int pass = 0;
			try {
				connection = DatabaseConnection.getInstance().getConnection();
				statement = connection.prepareStatement(DELETE_WIDGET);
				statement.setInt(1, widgetId);
				pass = statement.executeUpdate();


			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return pass;
		}
}
