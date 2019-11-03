package businessLogic.career;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CareerInterface {
	
	public abstract void addInfo(String type, String description);
	public abstract void addCareer(Career career) throws SQLException;
	public abstract void addPlan(Plan plan);
	public abstract ArrayList<Career> getAllCareer() throws SQLException;
}
