package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import careerLogic.Career;

public class DaoCareer {
	private Statement stmt;
    private ResultSet rs;
    private Db2Connection db; 
    private Connection conn;
    
    public DaoCareer() {
    	db = Db2Connection.getInstance();
		conn = Db2Connection.getConnection();
    }
    
    public ArrayList<Career> selectQueryCareer(String query) throws SQLException{
        ArrayList<Career> result = new ArrayList<Career>();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	Career career = new Career();
        	career.setId(rs.getString(1));
        	career.setName(rs.getString(2));
        	result.add(career);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

	public void manipulationQueryCareer(String query) throws SQLException {
        stmt = conn.createStatement();                                           
        stmt.executeUpdate(query);                   
        stmt.close();
        conn.commit();
        System.out.println ("Transacción confirmada" );
    }
}