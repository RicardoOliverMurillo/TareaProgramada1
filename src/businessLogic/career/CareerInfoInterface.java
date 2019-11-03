package businessLogic.career;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CareerInfoInterface {
	
	public abstract void insertInformation(Career info) throws SQLException;
	public abstract String getInformation(String type, String career) throws SQLException;
	public abstract void updateInformation(Career info) throws SQLException;
	public abstract ArrayList<RelevantInfo> getInformation(String career) throws SQLException;

}
