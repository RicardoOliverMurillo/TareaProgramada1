package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoKnowledgeArea extends Dao{
	
	public DaoKnowledgeArea() {
		super();
    }
    
    @Override
    public ArrayList<String> selectQuery(String query) throws SQLException{
        ArrayList<String> result = new ArrayList<String>();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	String knowledgeArea = rs.getString(2);
        	result.add(knowledgeArea);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

}