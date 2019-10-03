package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import careerLogic.Course;

public class DaoCourse extends Dao{
    
    public DaoCourse() {
		super();
    }
	
    @Override
	public ArrayList<Course> selectQuery(String query) throws SQLException{		
		ArrayList<Course> result = new ArrayList<Course>();
        stmt = conn.createStatement();                                          
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	Course newCourse = new Course();
        	newCourse.setId(rs.getString(1));
        	newCourse.setName(rs.getString(2));
        	newCourse.setSumCredits(Integer.parseInt(rs.getString(3)));
        	newCourse.setSemester((Integer.parseInt(rs.getString(4))));
        	newCourse.setKnowledgeArea(rs.getString(5));
        	result.add(newCourse);
        }
        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }
}
