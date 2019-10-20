package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Career;

public interface DaoInterface {

	public abstract void manipulationQuery(String query) throws SQLException;
	public abstract ArrayList<?> selectQuery(String query) throws SQLException;
}



