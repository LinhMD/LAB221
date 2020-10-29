package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class SQLQuery {
	public static boolean executeNonQuery(String sqlStatement, Object... values) throws Exception {
		boolean result = false;
		int index = 1;
		try (Connection cnn = MyConnection.makeConnection(); PreparedStatement ps = cnn.prepareStatement(sqlStatement)) {
			for (index = 0; index < values.length; index++) {
				ps.setObject(index + 1, values[index]);
			}
			result = (ps.executeUpdate() > 0);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return result;
	}

	public static Vector<Vector<String>> executeQuery(String sqlStatement, Object... values) throws Exception {
		Connection cnn = null;
		PreparedStatement pre = null;
		Vector<Vector<String>> dataTable = null;
		int index, columnsNumber;
		try {
			cnn = MyConnection.makeConnection();
			dataTable = new Vector<>();
			pre = cnn.prepareStatement(sqlStatement);
			for (index = 0; index < values.length; index++) {
				pre.setObject(index + 1, values[index]);
			}
			ResultSet rs = pre.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				Vector<String> rowData = new Vector<>();
				for (index = 1; index <= columnsNumber; index++) rowData.add(rs.getString(index));
				System.out.println(rowData);
				dataTable.add(rowData);
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			cnn.close();
		}
		return dataTable;
	}
}
