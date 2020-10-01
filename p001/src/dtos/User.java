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
		if(id == null || fullName == null || passWord == null){
			StringBuilder error = new StringBuilder();
			error.append("User ");
			if (id == null)
				error.append(" id,");
			if (fullName == null)
				error.append(" name,");
			if (passWord == null)
				error.append(" password,");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" invalid!!");
			throw new IllegalArgumentException(error.toString());
		}else if (id.isBlank() || fullName.isBlank() || passWord.isBlank()){
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
		}else if(id.length() > 10 || fullName.length() > 50 || passWord.length() > 50){
			StringBuilder error = new StringBuilder();
			error.append("User ");
			if (id.length() > 10)
				error.append(" id,");
			if (fullName.length() > 50)
				error.append(" name,");
			if (passWord.length() > 50)
				error.append(" password,");
			error.deleteCharAt(error.lastIndexOf(","));
			error.append(" is too long");
			throw new IllegalArgumentException(error.toString());
		} else{
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
		if (id == null)
			throw new IllegalArgumentException("User Id invalid!!");
		if (id.isBlank())
			throw new IllegalArgumentException("User id is empty");
		if (id.length() > 10)
			throw new IllegalArgumentException("User id is too long (max:10).");
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) throws IllegalArgumentException {
		if (fullName == null)
			throw new IllegalArgumentException("User name invalid!!");
		if (fullName.isBlank())
			throw new IllegalArgumentException("User name is empty");
		if (fullName.length() > 50)
			throw new IllegalArgumentException("User name is too long (max:50).");
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) throws IllegalArgumentException {
		if (passWord == null)
			throw new IllegalArgumentException("User password invalid!!");
		if (passWord.isBlank())
			throw new IllegalArgumentException("User password is empty");
		if (passWord.length() > 50)
			throw new IllegalArgumentException("User password is too long (max:50).");
			this.passWord = passWord;
	}

	public boolean getStatus() {
		return status;	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
