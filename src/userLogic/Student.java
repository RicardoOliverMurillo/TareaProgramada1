package userLogic;

public class Student {
	
	private String id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String role = "student";
	
	public Student(String id, String name, String lastName, String email, String password) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String msg = "";
		msg+= "Id: "+ id + "\n"; 
		msg+= "Name: "+ name + "" + lastName + "\n"; 
		msg+= "Email: "+ email + "\n"; 
		msg+= "Password: "+ password + "\n"; 
		return msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
