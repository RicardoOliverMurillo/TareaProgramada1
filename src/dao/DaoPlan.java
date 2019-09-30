package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import careerLogic.Plan;

public class DaoPlan {
	private Statement stmt;
    private ResultSet rs;
    private Db2Connection db; 
    private Connection conn;
    
    public DaoPlan() {
    	db = Db2Connection.getInstance();
		conn = Db2Connection.getConnection();
    }
    
    public ArrayList<Plan> selectQueryPlan(String query) throws SQLException{
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

	public void manipulationQueryPlan(String query) throws SQLException {
        stmt = conn.createStatement();                                           
        stmt.executeUpdate(query);                   
        stmt.close();
        conn.commit();
        System.out.println ("Transacción confirmada" );
    }
}
