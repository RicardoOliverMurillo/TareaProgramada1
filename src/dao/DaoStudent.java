package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import userLogic.Student;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoStudent to execute the database operations of the students. 
 * The class inherits the class Dao.
 */
public class DaoStudent extends Dao{

    
    public DaoStudent() {
		super();
    }
	
    /**
     * The method execute the SELECT query that retrieves information of the students from the database
     * @param query with the query that is going to be executed
     */
	@Override
	public ArrayList<Student> selectQuery(String query) throws SQLException{
		ArrayList<Student> result = new ArrayList<Student>();
		Student newStudent = new Student();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	newStudent.setId(rs.getString(1));
        	newStudent.setName(rs.getString(2));
        	newStudent.setLastName(rs.getString(3));
        	newStudent.setEmail(rs.getString(4));
        	newStudent.setPassword(rs.getString(5));
        	result.add(newStudent);
        }

        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

}
