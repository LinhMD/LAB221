package dao;

import dtos.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public static User checkUser(String userID, String password){
		if (userID == null) return null;
		String sql = "select u.userID, u.userFullName, u.userPassword, u.userStatus\n" +
				     "from tblUsers u\n" +
				     "where u.userID = ?";
		ResultSet resultSet = DAO.executeQuery(sql, userID);
		try {
			if(resultSet != null && resultSet.next()){
				String userPassword = resultSet.getString("userPassword");
				if(userPassword.equals(password))
					return new User(userID, resultSet.getString("userFullName"), userPassword, resultSet.getBoolean("userStatus"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
