package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoEquivalences to execute the database operations of the equivalences. 
 * The class inherits the class Dao.
 */
public class DaoEquivalences extends Dao {
	
	public DaoEquivalences() {
		super();
	}

	/**
     * The method execute the SELECT query that retrieves information of the equivalences from the database
     * @param query with the query that is going to be executed
     */
	@Override
	public ArrayList<String> selectQuery(String query) throws SQLException {
		stmt = conn.createStatement();                                           
		ArrayList<String> list = new ArrayList<String>();
		rs = stmt.executeQuery(query);
		while (rs.next()) {
    		list.add(rs.getString(1));
    	}
    	rs.close();
    	stmt.close();
    	conn.commit();
    	return list;
	}
}
