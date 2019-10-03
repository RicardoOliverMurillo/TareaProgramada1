package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Plan;

public class DaoPlan extends Dao{
    
    public DaoPlan() {
		super();
    }
    
    @Override
    public ArrayList<Plan> selectQuery(String query) throws SQLException{
        ArrayList<Plan> result = new ArrayList<Plan>();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	Plan plan = new Plan();
        	plan.setId(rs.getString(1));
        	result.add(plan);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

}
