package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import userLogic.Student;


public class DaoStudent extends Dao{

    
    public DaoStudent() {
		super();
    }
	
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
