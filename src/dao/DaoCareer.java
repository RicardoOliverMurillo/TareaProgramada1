package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.Career;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoCareer to execute the database operations of the careers. 
 * The class inherits the class Dao.
 */
public class DaoCareer extends Dao{
    
    public DaoCareer() {
		super();
    }
    
    @Override
    /**
     * The method execute the SELECT query that retrieves information of the careers from the database
     * @param query with the query that is going to be executed
     */
    public ArrayList<Career> selectQuery(String query) throws SQLException{
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

	
}