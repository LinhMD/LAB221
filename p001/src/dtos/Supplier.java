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
		this.collaborating = false;
	}


	public Supplier(String code, String name, String address, boolean collaborating) throws IllegalArgumentException {
		if (code.isBlank() || name.isBlank() || address.isBlank()){
			StringBuilder error = new StringBuilder();
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
		this.code = code;
		this.name = name;
		this.address = address;
		this.collaborating = collaborating;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code.isBlank())
			throw new IllegalArgumentException("Supplier code is empty");
		else this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isBlank())
			throw new IllegalArgumentException("Supplier name is empty");
		else this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address.isBlank())
			throw new IllegalArgumentException("Supplier address is empty");
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
