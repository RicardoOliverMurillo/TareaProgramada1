package businessLogic.career;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquivalencesInterface {

	public abstract String getPassCreditsEquivalences(ArrayList<String> courses, String idPlan) throws SQLException;
	public abstract ArrayList<String> getPassCourses(String idStudent) throws SQLException;
	public abstract ArrayList<String> getPassCoursesEquivalences(String idStudent) throws SQLException;
	public abstract String getEquivalences(String idCourse) throws SQLException;
	public abstract ArrayList<String> getRequirements(String idCourse, String idPlan) throws SQLException;
	public abstract ArrayList<String> getCorequirements(String idCourse, String idPlan) throws SQLException;
	public abstract void addEquivalence(String idCourse1,String idPlan1,String idCourse2, String idPlan2) throws SQLException;
}
