package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Phone;

public interface PhoneImpl {
	void createPrimaryPhone(int userID, String phoneNumber);
	int updatePrimaryPhone(int userID, String phoneNumber);

}
