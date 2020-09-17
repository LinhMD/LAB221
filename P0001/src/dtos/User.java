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
		if (id.isBlank())
			throw new IllegalArgumentException("User id is empty");
		else
			this.id = id;

		if (passWord.isBlank())
			throw new IllegalArgumentException("User password is empty");
		else
			this.passWord = passWord;

		if (passWord.isBlank())
			throw new IllegalArgumentException("User password is empty");
		else
			this.passWord = passWord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
