package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CareerInterface {
	
	public abstract void addInfo(String pType, String pDescription);
	public abstract void addCareer(Career career) throws SQLException;
	public abstract void addPlan(Plan plan);
	public abstract ArrayList<Career> getAllCareer() throws SQLException;
}
