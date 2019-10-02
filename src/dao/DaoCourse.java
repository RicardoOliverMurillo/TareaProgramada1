package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import careerLogic.Course;

public class DaoCourse {
	private Statement stmt;
    private ResultSet rs;
    private Db2Connection db; 
    private Connection conn;
    private ArrayList<Course> result = new ArrayList<Course>();
    public DaoCourse() {
    	db = Db2Connection.getInstance();
		conn = Db2Connection.getConnection();
    }
	
	public ArrayList<Course> selectQuery(String query) throws SQLException{		
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

	public void manipulationQuery(String query) throws SQLException {
    	// Crear el objeto Statement
        stmt = conn.createStatement();                                           
        System.out.println("Creado el objeto Statement de JDBC");
        // Ejecutar una consulta 
        stmt.executeUpdate(query);                   
        System.out.println("Consulta hecha");
        // Cerrar el objeto Statement
        stmt.close();
        System.out.println(" Cerrado el objeto Statement de JDBC");
        // La conexión debe estar en un límite de unidad trabajo para permitir cierre
        conn.commit();
        System.out.println ( " Transacción confirmada" );
    }
	
	public Course getCourse(String query) throws SQLException {
		stmt = conn.createStatement();                                           
		Course newCourse = new Course();
		rs = stmt.executeQuery(query);
		while (rs.next()) {
    		newCourse.setId(rs.getString(1));
    		newCourse.setName(rs.getString(2));
    		newCourse.setSumCredits(Integer.parseInt(rs.getString(3)));
    		newCourse.setSemester((Integer.parseInt(rs.getString(4))));
    		newCourse.setKnowledgeArea(rs.getString(5));
    	}
    	rs.close();
    	stmt.close();
    	conn.commit();
    	return newCourse;
	}
	
	public ArrayList<String> getPassCourses(String query) throws SQLException{
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
	
	public ArrayList<String> getId(String query) throws SQLException{
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
