package dtos;

public class Item {
	private String code;
	private String name;
	private String unit;
	private double price;
	private boolean supplying;
	private Supplier supplier;

	public Item(String code, String name, String unit, double price, boolean supplying, Supplier supplier) throws IllegalArgumentException {
		if (code.isBlank() || name.isBlank() || unit.isBlank() || price <= 0 || supplier == null){
			StringBuilder error = new StringBuilder();
			error.append("Item ");
			if (code.isBlank())
				error.append(" code,");
			if(name.isBlank())
				error.append(" name,");
			if(unit.isBlank())
				error.append(" unit,");
			if(supplier == null)
				error.append(" supplier,");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is empty.\n");
			if(price <= 0)
				error.append("price must be more than 0.");
			throw new IllegalArgumentException(error.toString());
		}
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.supplying = supplying;
		this.supplier = supplier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws IllegalArgumentException {
		if(code.isBlank())
			throw new IllegalArgumentException("Item code can not be empty");
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException {
		if(name.isBlank())
			throw new IllegalArgumentException("Item name can not be empty");
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) throws IllegalArgumentException {
		if(unit.isBlank())
			throw new IllegalArgumentException("Item Unit can not be empty");
		this.unit = unit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws IllegalArgumentException {
		if(price <= 0)
			throw new IllegalArgumentException("Item price must be more than 0");
		this.price = price;
	}

	public boolean isSupplying() {
		return supplying;
	}

	public void setSupplying(boolean supplying) {
		this.supplying = supplying;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) throws IllegalArgumentException {
		if(supplier == null)
			throw new IllegalArgumentException("Supplier invalid");
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Item{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", unit='" + unit + '\'' +
				", price=" + price +
				", supplying=" + supplying +
				", supplier=" + supplier +
				'}';
	}
}
