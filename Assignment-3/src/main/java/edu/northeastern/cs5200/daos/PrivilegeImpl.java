package edu.northeastern.cs5200.daos;
import java.util.Collection;

import edu.northeastern.cs5200.models.Priviledge;

public interface PrivilegeImpl {
		void assignWebsitePriviledge(int developerId, int websiteId, String priviledge);
		void assignPagePriviledge(int developerId, int pageId, String priviledge);
		void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge);
		void deletePagePriviledge(int developerId, int pageId, String priviledge);
}
