package dao;

import dtos.Supplier;
import utills.MyConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static dao.DAO.executeNonQuery;

public class SupplierDAO {
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
			} finally {
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
		if(supplier == null) return false;
		String sql = "insert into tblSuppliers (supCode, supName, supAddress, collaborating)\n" +
				"values(?, ?, ?, ?)";
		try{
			return executeNonQuery(sql, supplier.getCode(), supplier.getName(), supplier.getAddress(), supplier.isCollaborating());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add supplier "+ supplier.getName() +"failed!!");
		}
		return false;
	}

	public static boolean deleteSupplier(Supplier supplier){
		String sql = "delete from dbo.tblSuppliers\n" +
				"where supCode = ?";
		try{
			return executeNonQuery(sql, supplier.getCode());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Delete supplier failed!!");
		}
		return false;
	}
}
