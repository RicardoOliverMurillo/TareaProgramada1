package userLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoComment;
import dao.DaoInterface;
import dao.DaoStudent;

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
	private DaoInterface dbStudent, dbComment;
	
	public Student () {
		dbStudent = new DaoStudent();
		dbComment = new DaoComment();
	}
	
	/**
	 * Constructor of the class Student with the id
	 * @param id of the student
	 */
	public Student(String id) {
		this();
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
	public void setComment(String text) {
		Comment comment = new Comment(text);
		comments.add(comment);
	}
	
	
	/**
	 * Method that registers a new student in the database
	 * @param student the student information
	 * @throws Exception SQLException
	 */
	public void registerStudent(Student student) throws Exception {
		dbStudent.manipulationQuery("INSERT INTO USERS (IDUSER, NAME, LASTNAME, EMAIL, PASSWORD, ROLE) VALUES ('"+student.getId()+"','"+
				student.getName()+"','"+student.getLastName()+"','"+student.getEmail()+"','"+student.getPassword()+"','"+student.getRole()+"')");
	}
	
	/**
	 * the method add the comment of the user in the database
	 * @param result the comments of the user
	 * @throws SQLException
	 */
	public void addComment(ArrayList<Comment> result) throws SQLException {
		for(int i = 0; i < result.size(); i++) {
			Comment comment = result.get(i);
			dbComment.manipulationQuery("INSERT INTO COMMENTS (IDOWNER, DESCRIPTION, TONENAME, SCORE) VALUES "
					+ "('"+Session.getUser()+"','"+comment.getDescription()+"','"+comment.getToneName()
					+ "',"+comment.getScore()+")");
		}
	}
	
	/**
	 * the method returns the comments with an specific sentiment tone
	 * @param option the sentiment tone requested 
	 * @return result Arraylist with the comments that match the sentiment tone 
	 * @throws SQLException
	 */
	public ArrayList<Comment> getComment(String option) throws SQLException {
		ArrayList<Comment> result = new ArrayList<Comment>();
		result = (ArrayList<Comment>) dbComment.selectQuery("SELECT * FROM COMMENTS WHERE TONENAME = '"+option+"'");
		return result;
	}
	
	/**
	 * the method retrieves the comments made by an specific student
	 * @param id of the student that request the info
	 * @return result Array with the comments of an specific student 
	 * @throws SQLException
	 */
	public ArrayList<Comment> getCommentStudent(String id) throws SQLException {
		ArrayList<Comment> result = new ArrayList<Comment>();
		result = (ArrayList<Comment>) dbComment.selectQuery("SELECT * FROM COMMENTS WHERE IDOWNER = '"+id+"'");
		return result;
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
