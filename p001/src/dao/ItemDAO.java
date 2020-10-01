package dao;

import dtos.Item;
import dtos.Supplier;
import gui.SupplierPanel;
import utills.MyConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static dao.DAO.executeNonQuery;

public class ItemDAO {
	public static Vector<Item> getAllItem(){
		String sql = "select i.itemCode, i.itemName, i.price, i.supCode, i.supplying, i.unit\n" +
				"from tblItems i";
		Connection connection = MyConnection.makeConnection();
		Vector<Item> items = new Vector<>();
		if (connection != null){
			try{
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()){
					String itemCode = resultSet.getString("itemCode");
					String itemName = resultSet.getString("itemName");
					double price = resultSet.getDouble("price");
					String supCode = resultSet.getString("supCode");
					boolean supplying = resultSet.getBoolean("supplying");
					Supplier supplier = null;
					for (Supplier supplier1 : SupplierPanel.SUPPLIER)
						if(supplier1.getCode().equals(supCode))
							supplier = supplier1;
					String unit = resultSet.getString("unit");
					Item item = new Item(itemCode, itemName, unit, price, supplying, supplier);
					items.add(item);
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
		return items;
	}
	public static boolean updateItem(Item item){
		String sql = "update tblItems\n" +
				"set itemName = ?, price = ?, supCode = ?, supplying = ?, unit = ?\n" +
				"where itemCode = ?";
		try{
			return executeNonQuery(sql, item.getName(), item.getPrice(), item.getSupplier().getCode(), item.isSupplying(), item.getUnit(), item.getCode());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Update Item"+ item.getName() +" failed!!");
		}
		return false;
	}
	public static boolean insertItem(Item item){
		String sql = "insert into dbo.tblItems (itemCode, itemName, price, supCode, supplying, unit)\n" +
				"values(?, ?, ?, ?, ?, ?)";
		try {
			return executeNonQuery(sql, item.getCode(), item.getName(), item.getPrice(), item.getSupplier().getCode(), item.isSupplying(), item.getUnit());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add Item " + item.getName() + " failed!!");
			return false;
		}
	}
	public static boolean deleteItem(Item item){
		String sql = "delete from dbo.tblItems\n" +
				"where itemCode = ?";
		try {
			return executeNonQuery(sql, item.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Delete Item " + item.getName() + " failed!!");
			return false;
		}
	}
}
