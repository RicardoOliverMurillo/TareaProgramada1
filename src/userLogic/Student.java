package userLogic;

import java.util.ArrayList;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Student for the insertion and manipulation of the student information. 
 */
public class Student implements StudentInterface{
	
	private String id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String role = "Student";
	private ArrayList<Comment> comments;
	
	public Student () {}
	
	/**
	 * Constructor of the class Student with the id
	 * @param id of the student
	 */
	public Student(String id) {
		this.id = id;
		this.comments = new ArrayList<Comment>();
	}
	
	/**
	 * Constructor of the class Student
	 * @param id of the student
	 * @param name of the student
	 * @param lastName of the student
	 * @param email of the student
	 * @param password of the student
	 */
	public Student(String id, String name, String lastName, String email, String password) {
		this(id);
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}

	/**
	 * Method that add a new comment in the array list
	 * @param text the comment that wants to be added 
	 */
	public void addComment(String text) {
		Comment comment = new Comment(text);
		comments.add(comment);
	}
	
	/**
	 * Method toString that prettify the information 
	 * @return the information of the student formatted
	 */
	public String toString() {
		String msg = "";
		msg+= "Id: "+ id + "\n"; 
		msg+= "Name: "+ name + "" + lastName + "\n"; 
		msg+= "Email: "+ email + "\n"; 
		msg+= "Password: "+ password + "\n"; 
		return msg;
	}

	/**
	 * the method get the id of the student
	 * @return id of the student
	 */
	public String getId() {
		return id;
	}

	/**
	 * method that sets the id of the student
	 * @param id new id of the student
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * the method get the name of the student
	 * @return name of the student
	 */
	public String getName() {
		return name;
	}

	/**
	 * method that sets the name of the student
	 * @param name new name of the student
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * the method get the last name of the student
	 * @return lastName of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * method that sets the last name of the student
	 * @param lastName new lastName of the student
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * the method get the email of the student
	 * @return email of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * method that sets the email of the student
	 * @param email new email of the student
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * the method get the password of the student
	 * @return password of the student
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * method that sets the password of the student
	 * @param password new password of the student
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * the method get the role of the student
	 * @return role of the student
	 */
	public String getRole() {
		return role;
	}

	/**
	 * method that sets the role of the student
	 * @param role new role of the student
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * the method get the comments made by the student
	 * @return comments of the student
	 */
	public ArrayList<Comment> getComments(){
		return comments;
	}
}
