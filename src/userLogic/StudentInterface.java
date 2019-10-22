package userLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentInterface {
	
	public abstract String toString();
	public abstract void setComment(String text);
	public abstract void registerStudent(Student student) throws Exception;
	public abstract void addComment(ArrayList<Comment> result) throws SQLException;
	public abstract ArrayList<Comment> getComment(String option) throws SQLException;
	public abstract ArrayList<Comment> getCommentStudent(String id) throws SQLException;
}
