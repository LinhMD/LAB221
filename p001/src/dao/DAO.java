package dao;

import dtos.User;
import utills.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	public static boolean executeNonQuery(String sqlStatement,Object... values) throws Exception{
		boolean result;
		int index;
		PreparedStatement ps = null;
		Connection cnn = null;
		try{
			cnn  = MyConnection.makeConnection();
			if(cnn == null) return false;
			ps = cnn.prepareStatement(sqlStatement);
			for (index = 0; index < values.length; index++) ps.setObject(index + 1, values[index]);
			result = (ps.executeUpdate() > 0);
		} catch(Exception ex){
			throw new Exception(ex.getMessage());
		} finally{
			if(ps != null) ps.close();
			if(cnn != null) cnn.close();
		}
		return  result;
	}

	public static User checkUser(String userID, String password){
		if (userID.isBlank() || password.isBlank() ) return null;
		String sql = "select u.userID, u.userFullName, u.userPassword, u.userStatus\n" +
				"from tblUsers u\n" +
				"where u.userID = ?";
		Connection connection = MyConnection.makeConnection();
		User user = null;
		if (connection != null){
			try{
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userID);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next() && resultSet.getString("userPassword").equals(password))
					user = new User(resultSet.getString("userID"), resultSet.getString("userFullName"), password, resultSet.getBoolean("userStatus"));
				statement.close();
			} catch (SQLException | IllegalArgumentException e) {
				e.printStackTrace();
			}finally {
				try{
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}
}
