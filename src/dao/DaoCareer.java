package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Career;

public class DaoCareer extends Dao{
    
    public DaoCareer() {
		super();
    }
    
    @Override
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