package dao;

import dtos.Supplier;
import dtos.User;
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

	public static Vector<Supplier> getSuppliers(){
		String sql = "select s.supCode, s.supName, s.supAddress, s.collaborating\n" +
					"from tblSuppliers s";
		Connection connection = MyConnection.makeConnection();
		Vector<Supplier> suppliers = new Vector<>();
		if (connection != null){
			try{
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()){
					Supplier supplier = new Supplier(resultSet.getString("supCode"), resultSet.getString("supName"), resultSet.getString("supAddress"), resultSet.getBoolean("collaborating"));
					suppliers.add(supplier);
				}
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
		return suppliers;
	}

	public static boolean updateSupplier(Supplier supplier){
		String sql = "update tblSuppliers\n" +
					"set supAddress = ?, supName = ?, collaborating = ?\n" +
					"where supCode = ?";
		try{
			return executeNonQuery(sql, supplier.getAddress(), supplier.getName(), supplier.isCollaborating(), supplier.getCode());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Update failed!!");
		}
		return false;
	}

	public static boolean insertSupplier(Supplier supplier){
		String sql = "insert into tblSuppliers (supCode, supName, supAddress, collaborating)\n" +
					"values(?, ?, ?, ?)";
		try{
			return executeNonQuery(sql, supplier.getCode(), supplier.getName(), supplier.getAddress(), supplier.isCollaborating());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add new supplier failed!!");
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getSuppliers());
	}
}
