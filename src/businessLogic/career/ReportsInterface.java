package businessLogic.career;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportsInterface {

	public abstract int getCoursesLength(String plan) throws SQLException;
	public abstract ArrayList<String> getPassCoursesEquivalencesReport(String idStudent) throws SQLException;
	public abstract String getPassCredits(ArrayList<String> courses, String idPlan) throws SQLException;
	public abstract String getTotalCredits(String plan) throws SQLException;

}
