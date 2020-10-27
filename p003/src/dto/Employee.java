package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Employee {
	public static final String EMP_ID_FORMAT = "^\\w{2,10}$";
	/*
	* ^(?![\s.]+$) check the whole string must not be empty(contain only white space)
	* [a-zA-Z\s.]{2,30} check string must only contain character and white space
	* */
	public static final String FULL_NAME_FORMAT = "^(?![\\s.]+$)[a-zA-Z\\s.]{2,30}$";
	/*
	* (?=.{1,30}$) check the string length 1-30
	* [a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+ check email format regardless of length, also must contain a '@'
	* */
	public static final String EMAIL_FORMAT = "^(?=.{1,30}$)[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	public static final String PHONE_FORMAT = "^\\d{0,15}$";
	public static final String ADDRESS_FORMAT = "^.{0,300}$";
	public static final SimpleDateFormat DOB_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	//also set lenient to false bc no mercy for thy whose commit a sin
	static {
		DOB_FORMAT.setLenient(false);
	}

	private String empID;
	private String fullName;
	private String address;
	private String phone;
	private String email;
	private Date dob;

	public Employee(){
	}

	public Employee(String empID) throws IllegalArgumentException {
		if(empID.matches(EMP_ID_FORMAT))
			this.empID = empID;
		else
			throw new IllegalArgumentException("EmpID invalid!!!");
		this.fullName = "";
		this.address = "";
		this.phone = "";
		this.email = "";
		this.dob = Calendar.getInstance().getTime();
	}

	public Employee(String empID, String fullName, String address, String phone, String email, String dob) throws IllegalArgumentException {
		StringBuilder errorMess = new StringBuilder();
		if(!empID.matches(EMP_ID_FORMAT))
			errorMess.append("EmpID, ");
		if(!fullName.matches(FULL_NAME_FORMAT))
			errorMess.append("Full name, ");
		if(!address.matches(ADDRESS_FORMAT))
			errorMess.append("Address, ");
		if(!phone.matches(PHONE_FORMAT))
			errorMess.append("Phone number, ");
		if(!email.matches(EMAIL_FORMAT))
			errorMess.append("Email, ");
		try{
			this.dob = DOB_FORMAT.parse(dob);
		}catch (ParseException e) {
			errorMess.append("DOB, ");
		}

		//no error -> errorMess is empty -> not throw exception
		if(!errorMess.toString().trim().isEmpty()){
			errorMess.deleteCharAt(errorMess.lastIndexOf(","));
			errorMess.append("invalid!!!");
			throw new IllegalArgumentException(errorMess.toString());
		}

		//when everything is how it supposed to be
		this.empID = empID;
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public void setEmpID(String empID) throws IllegalArgumentException {
		if(empID.matches(EMP_ID_FORMAT))
			this.empID = empID;
		else
			throw new IllegalArgumentException("EmpID invalid!!!");
	}

	public void setFullName(String fullName) {
		if(fullName.matches(FULL_NAME_FORMAT))
			this.fullName = fullName;
		else
			throw new IllegalArgumentException("Full name invalid!!!");
	}

	public void setAddress(String address) {
		if(address.matches(ADDRESS_FORMAT))
			this.address = address;
		else
			throw new IllegalArgumentException("Address invalid!!!");
	}

	public void setPhone(String phone) {
		if(phone.matches(PHONE_FORMAT))
			this.phone = phone;
		else
			throw new IllegalArgumentException("Phone invalid!!!");
	}

	public void setEmail(String email) {
		if(email.matches(EMAIL_FORMAT))
			this.email = email;
		else
			throw new IllegalArgumentException("Email invalid!!!");
	}

	public void setDob(String dob){
		try {
			this.dob = DOB_FORMAT.parse(dob);
		} catch (ParseException e) {
			throw new IllegalArgumentException("DOB invalid!!!");
		}
	}

	public String getEmpID() {
		return empID;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getDOB(){
		return DOB_FORMAT.format(this.dob);
	}

	//use toVector to get Employee information by a vector and then later use it as a row vector of a table
	public Vector<String> toVector(){
		Vector<String> vector = new Vector<>(4);
		vector.add(this.getEmpID());
		vector.add(this.getFullName());
		vector.add(this.getPhone());
		vector.add(this.getEmail());

		return vector;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return empID.equals(employee.empID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empID);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"empID='" + empID + '\'' +
				", fullName='" + fullName + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", dob=" + dob +
				'}';
	}

	public static void main(String[] args) {
		System.out.println("SE140063".matches(EMP_ID_FORMAT));
		System.out.println("        ".matches(FULL_NAME_FORMAT));
		System.out.println("maidinhlinh967@gmail.com".matches(EMAIL_FORMAT));
		System.out.println("10020010100".matches(PHONE_FORMAT));
		System.out.println("ajiskdnajb;2o3/13jinjasdf".matches(ADDRESS_FORMAT));
		try {
			System.out.println(DOB_FORMAT.parse("12/10/2020"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
