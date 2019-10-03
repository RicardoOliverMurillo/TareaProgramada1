package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.RelevantInfo;

public class DaoRelevantInfo extends Dao{
    
    public DaoRelevantInfo() {
		super();
    }
    
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
