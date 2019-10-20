package careerLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseInterface {
	
	public abstract boolean isAprove(ArrayList<String> courses, String id);
	public abstract Course getCourse(String idCourse, String idPlan) throws SQLException;
	public abstract void registerCourse(Course course) throws Exception;
	public abstract void registerRequirements(String idCourse, String idRequirement, String idPlan) throws Exception;
	public abstract void registerCorequirements(String idCourse, String idCorequirement,String idPlan) throws Exception;
	public abstract String[][] getCourses(String plan) throws SQLException;
}
