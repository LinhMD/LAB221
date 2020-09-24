package dtos;

public class User {
	private String id;
	private String fullName;
	private String passWord;
	private boolean status;

	public User(){
		this.id = "";
		this.fullName = "";
		this.passWord = "";
		this.status = false;
	}

	public User(String id, String fullName, String passWord, boolean status) throws IllegalArgumentException{
		if (id.isBlank() || fullName.isBlank() || passWord.isBlank()){
			StringBuilder error = new StringBuilder();
			error.append("User ");
			if (id.isBlank())
				error.append(" id,");
			if (fullName.isBlank())
				error.append(" name,");
			if (passWord.isBlank())
				error.append(" password,");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is empty");
			throw new IllegalArgumentException(error.toString());
		}else {
			this.id = id;
			this.fullName = fullName;
			this.passWord = passWord;
			this.status = status;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws IllegalArgumentException {
		if (id.isBlank())
			throw new IllegalArgumentException("User id is empty");
		else
			this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) throws IllegalArgumentException {
		if (fullName.isBlank())
			throw new IllegalArgumentException("User password is empty");
		else
			this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) throws IllegalArgumentException {
		if (passWord.isBlank())
			throw new IllegalArgumentException("User password is empty");
		else
			this.passWord = passWord;
	}

	public boolean getStatus() {
		return status;	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
