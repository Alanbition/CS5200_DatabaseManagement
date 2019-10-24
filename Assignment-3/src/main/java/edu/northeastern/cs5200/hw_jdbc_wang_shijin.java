package edu.northeastern.cs5200;
import java.text.ParseException;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import java.sql.Date;
import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;


public class hw_jdbc_wang_shijin {
	public static void main(String[] args) throws ParseException {		
		createDevelopers();
		createUsers();
		createWebsite();
		createPages();
		createWidgets();
		updates();
		deletes();
	}
	
	public static void createDevelopers()  throws ParseException{
		Developer alice = 
		new Developer(12, "Alice", "Wonder", "4321rewq", "alice", "alice", "alice@wonder.com", null);
		DeveloperDao.getInstance().createDeveloper(alice);
		System.out.println("Alice Created");

		Developer bob = 
		new Developer(23, "Bob", "Marley", "5432trew", "bob", "bob", "bob@marley.com", null);
		DeveloperDao.getInstance().createDeveloper(bob);
		System.out.println("Bob Created");

		Developer charlie = 
		new Developer(34, "Charles", "Garcia", "6543ytre", "charlie", "charlie", "chuch@garcia.com", null);
		DeveloperDao.getInstance().createDeveloper(charlie);
		System.out.println("Charles Created");
	}

	public static void createUsers(){
		User dan = 
		new User(45, "Dan", "Martin", "charlie", "charlie", "chuch@garcia.com", null);
		UserDao.getInstance().createUser(dan);
		System.out.println("Dan Created");

		User ed = 
		new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null);
		UserDao.getInstance().createUser(ed);
		System.out.println("Ed Created");
	}

	public static void createWebsite(){
		Date date = new Date(System.currentTimeMillis());
		Website fb = 
		new Website(123, "Facebook", "an online social media and social networking service", date, date, 1234234, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,fb);
		System.out.println("Facebook Created");

	
		RoleDao.getInstance().assignWebsiteRole(12, 123, "owner");
		RoleDao.getInstance().assignWebsiteRole(23, 123, "editor");
		RoleDao.getInstance().assignWebsiteRole(34, 123, "admin");
		System.out.println("Facebook Role Created");

		Website tw = 
		new Website(234, "Twitter", "an online news and social networking service", date, date, 4321543, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,tw);
		System.out.println("Twitter Created");

		RoleDao.getInstance().assignWebsiteRole(23, 234, "owner");
		RoleDao.getInstance().assignWebsiteRole(34, 234, "editor");
		RoleDao.getInstance().assignWebsiteRole(12, 234, "admin");
		System.out.println("Twitter Role Created");

		Website wk = 
		new Website(345, "Wikipedia", "a free online encyclopedia", date, date, 3456654, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,wk);
		System.out.println("Wikipedia Created");

		RoleDao.getInstance().assignWebsiteRole(34, 345, "owner");
		RoleDao.getInstance().assignWebsiteRole(12, 345, "editor");
		RoleDao.getInstance().assignWebsiteRole(23, 345, "admin");
		System.out.println("Wikipedia Role Created");

		Website cnn = 
		new Website(456, "CNN", "an American basic cable and satellite television news channel", date, date, 6543345, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,cnn);
		System.out.println("CNN Created");

		RoleDao.getInstance().assignWebsiteRole(12, 456, "owner");
		RoleDao.getInstance().assignWebsiteRole(23, 456, "editor");
		RoleDao.getInstance().assignWebsiteRole(34, 456, "admin");
		System.out.println("CNN role Created");

		Website cnet = 
		new Website(567, "CNET", "an American media website that publishes reviews, news, "
				+ "articles, blogs, podcasts and videos on technology and consumer electronics", date, date, 5433455, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,cnet);
		System.out.println("CNET Created");

		RoleDao.getInstance().assignWebsiteRole(23, 567, "owner");
		RoleDao.getInstance().assignWebsiteRole(34, 567, "editor");
		RoleDao.getInstance().assignWebsiteRole(12, 567, "admin");
		System.out.println("CNET role Created");

		Website gz = 
		new Website(678, "Gizmodo", "a design, technology, science and "
				+ "science fiction website that also writes articles on politics", date, date, 4322345, -1);
		WebsiteDao.getInstance().createWebsiteForDeveloper(-1,gz);
		System.out.println("Gizmodo Created");

		RoleDao.getInstance().assignWebsiteRole(34, 678, "owner");
		RoleDao.getInstance().assignWebsiteRole(12, 678, "editor");
		RoleDao.getInstance().assignWebsiteRole(23, 678, "admin");
		System.out.println("Gizomodo Role Created");

	}
	public static void createPages() {
		Date semdate = java.sql.Date.valueOf("2019-09-03");
		Date duedate = java.sql.Date.valueOf("2019-10-23");

		Page home = 
		new Page(123, "Home", "Landing page", semdate, duedate, 123434);
		PageDao.getInstance().createPageForWebsite(567, home);
		System.out.println("Home Page Created");	
		
		RoleDao.getInstance().assignPageRole(12, 123, "editor");
		RoleDao.getInstance().assignPageRole(23, 123, "reviewer");
		RoleDao.getInstance().assignPageRole(34, 123, "writer");

		Page about = 
		new Page(234, "About", "Website description", semdate, duedate, 234545);
		PageDao.getInstance().createPageForWebsite(678, about);
		System.out.println("About Page Created");
		
		RoleDao.getInstance().assignPageRole(23, 234, "editor");
		RoleDao.getInstance().assignPageRole(34, 234, "reviewer");
		RoleDao.getInstance().assignPageRole(12, 234, "writer");
		
		Page contact = 
		new Page(345, "Contact", "Addresses, phones, and contact info", semdate, duedate, 345656);
		PageDao.getInstance().createPageForWebsite(345, contact);
		System.out.println("Contact Page Created");

		RoleDao.getInstance().assignPageRole(34, 345, "editor");
		RoleDao.getInstance().assignPageRole(12, 345, "reviewer");
		RoleDao.getInstance().assignPageRole(23, 345, "writer");
		
		Page preferences = 
		new Page(456, "Preferences", "Where users can configure their preferences",semdate, duedate, 456776);
		PageDao.getInstance().createPageForWebsite(456, preferences);
		System.out.println("Preferences Page Created");
		
		RoleDao.getInstance().assignPageRole(12, 456, "editor");
		RoleDao.getInstance().assignPageRole(23, 456, "reviewer");
		RoleDao.getInstance().assignPageRole(34, 456, "writer");
		
		Page profile = 
		new Page(567, "Profile", "Users can configure their personal information", semdate, duedate, 567878);
		PageDao.getInstance().createPageForWebsite(567, profile);
		System.out.println("Profile Page Created");
		
		RoleDao.getInstance().assignPageRole(23, 567, "editor");
		RoleDao.getInstance().assignPageRole(34, 567, "reviewer");
		RoleDao.getInstance().assignPageRole(12, 567, "writer");
	}
	public static void createWidgets() {
		Widget head123 = 
		new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome",0, 0);
		WidgetDao.getInstance().createWidgetForPage(123, head123);
		System.out.println("head123 Created");
		
		Widget post234 = 
		new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>",0, null);
		WidgetDao.getInstance().createWidgetForPage(234, post234);
		System.out.println("post234 Created");
		
		Widget head345 = 
		new HeadingWidget(3, "head345", 0, 0, null, null, "Hi",1, 0);
		WidgetDao.getInstance().createWidgetForPage(345, head345);
		System.out.println("head345 Created");

		Widget intro456 = 
		new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>",2, null);
		WidgetDao.getInstance().createWidgetForPage(345, intro456);
		System.out.println("intro456 Created");

		Widget image345 = 
		new ImageWidget(5, "image345", 50, 100, null, null, null,3, "/img/567.png");
		WidgetDao.getInstance().createWidgetForPage(345, image345);
		System.out.println("image345 Created");
	
		Widget video456 = 
		new YouTubeWidget(6, "video456", 400, 300, null, null, null,0, "https://youtu.be/h67VX51QXiQ",false, false);
		WidgetDao.getInstance().createWidgetForPage(456, video456);
		System.out.println("video456 Created");

	}
	public static void updates() {
		//1
	    PhoneDao phoneDao = PhoneDao.getInstance();
	    int userId = DeveloperDao.getInstance().findDeveloperByUsername("charlie").getId();
	    String phoneNumber = "333-444-5555";
	    if(phoneDao.updatePrimaryPhone(userId, phoneNumber) == 0)
	        phoneDao.createPrimaryPhone(userId, phoneNumber);
		System.out.println("phone updated");

	    
	    //2
	    Collection<Widget> widgetList = WidgetDao.getInstance().findAllWidgets();
	    Widget head345 = null;
		for (Widget w : widgetList) {
			int order = w.getOrder();
			if (w.getName().equals("head345")) {
				w.setOrder(3);;
			}else {
				if (order > 3) {
					w.setOrder(order + 1);
				}else if(order<=3 && order >0){
					w.setOrder(order - 1);
				}
			}
			int res = WidgetDao.getInstance().updateWidget(w.getId(), w);
		}
		System.out.println("widget updated");
		
		//3
		Collection<Website> websiteList = WebsiteDao.getInstance().findAllWebsites();
		int webId = 0;
		for (Website w : websiteList) {
			if (w.getName().equals("CNET")) {
				webId = w.getId();
			}
		}
	    Collection<Page> pageList = PageDao.getInstance().findPagesForWebsite(webId);
		for (Page p : pageList) {
			String newTitle = "CNET - "+ p.getTitle();
			p.setTitle(newTitle);
			int res = PageDao.getInstance().updatePage(p.getId(), p);			
		}	    
		System.out.println("page updated");
		
		//4
		Collection<Website> websiteList2 = WebsiteDao.getInstance().findAllWebsites();
		int webId2 = 0;
		for (Website w : websiteList2) {
			if (w.getName().equals("CNET")) {
				webId2 = w.getId();
			}
		}
		System.out.println("Update roles-1/5 Finished");
		
	    Collection<Page> pageList2 = PageDao.getInstance().findPagesForWebsite(webId2);	
	    Page CNET_home = null;
		for (Page p : pageList2) {
			if (p.getTitle().equals("CNET - Home")) {
				CNET_home = p;
			}
		}
		System.out.println("Update roles-2/5 Finished ");

	    int charlieId = DeveloperDao.getInstance().findDeveloperByUsername("charlie").getId();
	    int bobId = DeveloperDao.getInstance().findDeveloperByUsername("bob").getId();
	    String charlieRole = RoleDao.getInstance().findPageRole(charlieId, CNET_home.getId());
		System.out.println(charlieRole);
		System.out.println("Update roles-3/5 Finished");

	    String bobRole = RoleDao.getInstance().findPageRole(bobId, CNET_home.getId());
		System.out.println(bobRole);
		System.out.println("Update roles-4/5 Finished");

	    //Update Charlie Role
	    RoleDao.getInstance().updatePageRole(bobRole, CNET_home.getId(), charlieId);
	    //Update Bob Role
	    RoleDao.getInstance().updatePageRole(charlieRole, CNET_home.getId(), bobId);
		System.out.println("Update roles-5/5 Finished");
		
		
	}
	public static void deletes(){
		//1
	    int aliceId = DeveloperDao.getInstance().findDeveloperByUsername("alice").getId();
		DeveloperDao.getInstance().deletePrimaryAddress(aliceId);
		System.out.println("Phone deleted");

		//2
	    Collection<Page> pageList0 = PageDao.getInstance().findAllPages();
	    Page contact = null;
		for (Page p : pageList0) {
			if (p.getTitle().equals("Contact")) {
				contact = p;
				break;
			}
		}
	    Collection<Widget> widgetList = WidgetDao.getInstance().findWidgetsForPage(contact.getId());
	    Widget highestOrderWidget = null;
	    int maxOrder = 0;
		for (Widget w : widgetList) {
			if (w.getOrder() > maxOrder) {
				maxOrder = w.getOrder();
				highestOrderWidget = w;
			}
		}
		WidgetDao.getInstance().deleteWidget(highestOrderWidget.getId());
		System.out.println("widget deleted");	
		//3
		Collection<Website> websiteList = WebsiteDao.getInstance().findAllWebsites();
		int webId = 0;
		for (Website w : websiteList) {
			if (w.getName().equals("Wikipedia")) {
				webId = w.getId();
			}
		}
	    Collection<Page> pageList = PageDao.getInstance().findPagesForWebsite(webId);
	    Page last = null;
	    Date latestDate = java.sql.Date.valueOf("1990-09-03");
		for (Page p : pageList) {
			if (p.getUpdated().after(latestDate)) {
				latestDate = p.getUpdated();
				last = p;
			}
		}
		int res = PageDao.getInstance().deletePage(last.getId());			
		System.out.println("Page deleted");	
		//4
		Collection<Website> websiteList2 = WebsiteDao.getInstance().findAllWebsites();
		int webId2 = 0;
		for (Website w : websiteList2) {
			if (w.getName().equals("CNET")) {
				webId2 = w.getId();
			}
		}
		WebsiteDao.getInstance().deleteWebsite(webId2);
		System.out.println("Website deleted");	

	}
}
