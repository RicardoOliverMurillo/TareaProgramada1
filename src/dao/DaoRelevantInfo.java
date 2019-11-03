package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.career.RelevantInfo;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoRelevantInfo to execute the database operations of the RelevantInfo. 
 * The class inherits the class Dao.
 */
public class DaoRelevantInfo extends Dao{
    
    public DaoRelevantInfo() {
		super();
    }
    
    /**
     * The method execute the SELECT query that retrieves information of the relevantInfo from the database
     * @param query with the query that is going to be executed
     */
    @Override
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

}
