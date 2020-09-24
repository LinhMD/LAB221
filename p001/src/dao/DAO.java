package dao;

import utills.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
