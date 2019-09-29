package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Plan;

public interface DaoPlanInterface {
    public ArrayList<Plan> selectQueryPlan(String query) throws SQLException;

	public void manipulationQueryPlan(String query) throws SQLException;
}
