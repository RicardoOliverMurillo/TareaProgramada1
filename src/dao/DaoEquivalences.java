package dao;

import java.sql.SQLException;
import java.util.ArrayList;


public class DaoEquivalences extends Dao {
	
	public DaoEquivalences() {
		super();
	}

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
