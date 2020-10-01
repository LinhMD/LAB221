package dtos;

import java.util.Objects;

public class Supplier {
	private String code;
	private String name;
	private String address;
	private boolean collaborating;

	public Supplier(){
		this.code = "";
		this.name = "";
		this.address = "";
		this.collaborating = true;
	}


	public Supplier(String code, String name, String address, boolean collaborating) throws IllegalArgumentException {
		StringBuilder error = new StringBuilder();

		if(code == null || name == null || address == null){
			error.append("Supplier ");
			if(code == null)
				error.append("code, ");
			if(name == null)
				error.append("name, ");
			if(address == null)
				error.append("address, ");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is empty.");
			throw new IllegalArgumentException(error.toString());
		}

		if (code.isBlank() || name.isBlank() || address.isBlank()){
			error.append("Supplier ");
			if (code.isBlank())
				error.append(" code,");
			if(name.isBlank())
				error.append(" name,");
			if (address.isBlank())
				error.append(" address,");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is empty.");
			throw new IllegalArgumentException(error.toString());
		}

		if(code.length() > 10 || name.length() > 50 || address.length() > 50){
			error.append("Supplier ");
			if (code.length() > 10)
				error.append(" code(max: 10),");
			if(name.length() > 50)
				error.append(" name(max: 50),");
			if (address.length() > 50)
				error.append(" address(max: 50),");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is too long.");
			throw new IllegalArgumentException(error.toString());
		}
		System.out.println("here3");
		this.code = code;
		this.name = name;
		this.address = address;
		this.collaborating = collaborating;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null)
			throw new IllegalArgumentException("Supplier code invalid");
		if (code.isBlank())
			throw new IllegalArgumentException("Supplier code is empty");
		if (code.length() > 10)
			throw new IllegalArgumentException("Supplier code is too long (max: 10)");
		else this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Supplier name invalid");
		if (name.isBlank())
			throw new IllegalArgumentException("Supplier name is empty");
		if (name.length() > 50)
			throw new IllegalArgumentException("Supplier name is too long (max: 50)");
		else this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null)
			throw new IllegalArgumentException("Supplier address invalid");
		if (address.isBlank())
			throw new IllegalArgumentException("Supplier address is empty");
		if (address.length() > 50)
			throw new IllegalArgumentException("Supplier address is too long (max: 50)");
		else this.address = address;
	}

	public boolean isCollaborating() {
		return collaborating;
	}

	public void setCollaborating(boolean collaborating) {
		this.collaborating = collaborating;
	}

	@Override
	public String toString() {
		return code + '-' + name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Supplier supplier = (Supplier) o;
		return Objects.equals(code, supplier.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}
}
