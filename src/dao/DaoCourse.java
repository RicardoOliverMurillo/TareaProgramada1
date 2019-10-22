package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.Course;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoCourse to execute the database operations of the courses. 
 * The class inherits the class Dao.
 * 
 */
public class DaoCourse extends Dao{
    
    public DaoCourse() {
		super();
    }
	
    /**
     * The method execute the SELECT query that retrieves information of the courses from the database
     * @param query with the query that is going to be executed
     */
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
