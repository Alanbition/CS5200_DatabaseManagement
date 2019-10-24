New Helper functions added:


In DeveloperDao:
int deletePrimaryAddress(int developerId);


In RoleDao:
        String findPageRole(int developerId, int pageId);
        void updatePageRole(String newrole, int pageId, int developerId);
 


New Dao added:


UserDao:
        void createUser(User user);
        
PhoneDao:
        void createPrimaryPhone(int userID, String phoneNumber);
        int updatePrimaryPhone(int userID, String phoneNumber);






Assumptions:


Parameter: “Int RoleID” changed to “String Role” in RoleDao helper functions


The developerId in createWebsiteForDeveloper() and website constructors are just a placeholder which has a fixed value of -1 since this relation is redundant


Triggers are implemented in mySQL instead of using Daos implemented in privliedgeDao.


The width, length and size fields in WidgetDaos are all initialized as 0 instead of “null” to avoid null pointer error.