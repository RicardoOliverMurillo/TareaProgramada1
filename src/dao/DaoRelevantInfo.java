package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import careerLogic.RelevantInfo;

public class DaoRelevantInfo {
	
	private Statement stmt;
    private ResultSet rs;
    private Db2Connection db; 
    private Connection conn;
    
    public DaoRelevantInfo() {
    	db = Db2Connection.getInstance();
		conn = Db2Connection.getConnection();
    }
    
    public ArrayList<RelevantInfo> selectQuery(String query) throws SQLException{
        ArrayList<RelevantInfo> result = new ArrayList<RelevantInfo>();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	RelevantInfo newInfo = new RelevantInfo();
        	newInfo.setType(rs.getString(2));
        	newInfo.setDescription(rs.getString(3));
        	newInfo.setCareer(rs.getString(4));
        	result.add(newInfo);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

	public void manipulationQuery(String query) throws SQLException {
        stmt = conn.createStatement();                                           
        stmt.executeUpdate(query);                   
        stmt.close();
        conn.commit();
        System.out.println ("Transacción confirmada" );
    }
	
	public String getInformation(String query) throws SQLException {
		String text = new String();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	text = rs.getString(1);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return text;
	}

}
