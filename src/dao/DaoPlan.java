package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.career.Plan;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoPlan to execute the database operations of the plans. 
 * The class inherits the class Dao.
 */
public class DaoPlan extends Dao{
    
    public DaoPlan() {
		super();
    }
    
    /**
     * The method execute the SELECT query that retrieves information of the plans from the database
     * @param query with the query that is going to be executed
     */
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
