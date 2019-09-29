package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Career;

public interface DaoCareerInterface {
    public ArrayList<Career> selectQueryCareer(String query) throws SQLException;

	public void manipulationQueryCareer(String query) throws SQLException;

}
