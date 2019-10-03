package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao implements DaoInterface{
	
	protected Statement stmt;
	protected ResultSet rs;
	protected Connection conn;
	
	public Dao() {
		conn = ConnectionSingleton.getInstance();
	}
	
	public void manipulationQuery(String query) throws SQLException {
        stmt = conn.createStatement();                                           
        stmt.executeUpdate(query);                   
        stmt.close();
        conn.commit();
    }

}
