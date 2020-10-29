package dao;

import dto.Employee;

import java.util.Vector;

public class EmployeeDAO {
	public Vector<Employee> getAllEmployee(){
		String sqlStatement = "select EmpID,Fullname,Phone,Email,Address,DateOfBirth\n" +
				"from tblEmployee\n" +
				"where isDelete = 0";
		Vector<Employee> employees = new Vector<>();
		try {
			Vector<Vector<String>> data = SQLQuery.executeQuery(sqlStatement);
			if(data == null) return employees;
			for (Vector<String> row : data) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
