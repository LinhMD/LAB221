package dao;

import dtos.User;

public class UserDAO {
	public static User checkUser(String userID, String password){
		if (userID == null) return null;
		String sql = "select u.userID, u.userFullName, u.userPassword, u.userStatus\n" +
				     "from tblUsers u\n" +
				     "where u.userID = ?";

		return null;
	}
}
