package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ApprovedInterface {
	
	public abstract void insertApprovedFilter(ArrayList<Course> data) throws Exception;
	public abstract void deleteApprovedFilter(ArrayList<Course> data) throws Exception;
	public abstract ArrayList<Course> getCoursesPlan(String plan) throws SQLException;
	public abstract ArrayList<Course> getCoursesSemester(String semester) throws SQLException;
	public abstract ArrayList<Course> getCoursesArea(String area) throws SQLException;
	public abstract void insertStudentCourse(String idStudent, String idCourse) throws Exception;
	public abstract void deleteStudentCourse(String idStudent, String idCourse) throws Exception;
}
