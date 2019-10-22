package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlanInterface {
	public abstract void addCourse(Course course);
	public abstract void addKnowledgeArea(String description);
	public abstract void addPlan(Plan plan) throws SQLException;
	public abstract ArrayList<Career> getCareer(String idCareer) throws SQLException;
	public abstract ArrayList<Plan> getPlans(String idCareer) throws SQLException;
	public abstract ArrayList<Plan> getAllPlans() throws SQLException;
	
}
