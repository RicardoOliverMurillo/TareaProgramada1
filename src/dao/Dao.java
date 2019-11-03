package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import creationalLogic.ConnectionSingleton;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Dao to execute the database operations. 
 * The class implements the DaoInterface.
 */
public abstract class Dao implements DaoInterface{
	
	protected Statement stmt;
	protected ResultSet rs;
	protected Connection conn;
	
	/**
	 * Constructor of the class dao that establish the database connection.
	 */
	public Dao() {
		conn = ConnectionSingleton.getInstance();
	}
	
	/**
	 * the method executes an operation in the database, it could be INSERTION, DELETE or UPDATE.
	 * @param query the query that is going to be executed
	 */
	public void manipulationQuery(String query) throws SQLException {
        stmt = conn.createStatement();                                           
        stmt.executeUpdate(query);                   
        stmt.close();
        conn.commit();
    }

}
