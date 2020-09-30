package dao;

import dtos.Item;
import dtos.Supplier;
import dtos.User;
import gui.SupplierPanel;
import utills.MyConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	public static Vector<Vector<String>> executeQuery(String sqlStatement,int ColumnNumber,Object... values) throws Exception{
		Connection cnn = null;
		PreparedStatement pre = null;
		Vector<Vector<String>>  productList = null;
		int index = 1;
		try{
			cnn = MyConnection.makeConnection();
			if(cnn == null) throw new RuntimeException("Shit went down");
			productList = new Vector<Vector<String>>();
			pre = cnn.prepareStatement(sqlStatement);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				Vector<String> productDetail = new Vector<String>();
				for(index = 1;index<=ColumnNumber;index++) productDetail.add(rs.getString(index));

				productList.add(productDetail);
			}
		} catch(Exception ex){
			throw new Exception(ex.getMessage());
		} finally{
			if(cnn != null) cnn.close();
		}
		return productList;
	}

	public static boolean checkUser(String userID, String password){
		if (userID.isBlank() || password.isBlank() ) return false;
		String sql = "select u.userID, u.userFullName, u.userPassword, u.userStatus\n" +
				"from tblUsers u\n" +
				"where u.userID = ?";
		Connection connection = MyConnection.makeConnection();
		boolean check = false;
		if (connection != null){
			try{
				System.out.println("here");
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userID);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next() && resultSet.getString("userPassword").equals(password)){
					check = true;
					System.out.println("here 2");
				}
				System.out.println(password);
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
		return check;
	}
}
