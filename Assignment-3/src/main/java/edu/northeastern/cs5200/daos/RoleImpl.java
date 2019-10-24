package edu.northeastern.cs5200.daos;
import java.util.Collection;

import edu.northeastern.cs5200.models.Role;

public interface RoleImpl {
	void assignWebsiteRole(int developerId, int websiteId, int roleId);
	void assignPageRole(int developerId, int pageId, int roleId);
	void deleteWebsiteRole(int developerId, int websiteId, int roleId);
	void deletePageRole(int developerId, int pageId, int roleId);
	String findPageRole(int developerId, int pageId);
	void updatePageRole(String newrole, int pageId, int developerId);
}
