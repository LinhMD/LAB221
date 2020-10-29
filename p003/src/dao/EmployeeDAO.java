package dao;

import dto.Employee;

import java.text.SimpleDateFormat;
import java.util.Vector;

public class EmployeeDAO {
	public static final SimpleDateFormat SQL_DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static Vector<Employee> getAllEmployee(){
		String sqlStatement = "select EmpID, Fullname, Address, Phone, Email, DateOfBirth\n" +
				"from tblEmployee\n" +
				"where isDelete = 0";
		Vector<Employee> employees = new Vector<>();
		try {
			Vector<Vector<String>> table = SQLQuery.executeQuery(sqlStatement);
			if(table == null) return employees;
			for (Vector<String> row : table) {
				try{
					employees.add(new Employee(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), Employee.DOB_FORMAT.format(SQL_DATA_FORMAT.parse(row.get(5)))));
				}catch (IllegalArgumentException ex){
					ex.printStackTrace();
				}
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public static Employee findEmployee(String EmpID){
		String sqlStatement = "select EmpID, Fullname, Address, Phone, Email, DateOfBirth\n" +
				"from tblEmployee\n" +
				"where EmpID = ?";
		try{
			Vector<Vector<String>> table = SQLQuery.executeQuery(sqlStatement, EmpID);
			Vector<String> row = table.get(0);
			return new Employee(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), Employee.DOB_FORMAT.format(SQL_DATA_FORMAT.parse(row.get(5))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean insertEmployee(Employee employee){
		String sqlStatement = "insert into tblEmployee(EmpID, Fullname, Address, Phone, Email, DateOfBirth, isDelete)\n" +
				"values(?, ?, ?, ?, ?, ?, ?)\n";
		try{
			return SQLQuery.executeNonQuery(sqlStatement, employee.getEmpID(), employee.getFullName(), employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getDOB(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteEmployee(String EmpID){
		String sql = "delete tblEmployee\n" +
				"where EmpID = ?";
		try{
			return SQLQuery.executeNonQuery(sql, EmpID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getAllEmployee());
		System.out.println(findEmployee("ID1"));
		Employee employee = new Employee("AD", "linh", "nha", "01002", "Email@email", "06/12/2000");
		System.out.println(insertEmployee(employee));
		System.out.println(deleteEmployee("AD"));
	}
}
