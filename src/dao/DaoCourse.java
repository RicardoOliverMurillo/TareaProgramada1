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
		Course newCourse = new Course();
        stmt = conn.createStatement();                                           
        System.out.println(" Creado el objeto Statement de JDBC");
        // Ejecutar una consulta y generar instancia del conjunto de resultados
        rs = stmt.executeQuery(query);
        System.out.println(" Creado el objeto JDBC ResultSet");
        // Imprimir todos los n�meros de empleado en el dispositivo de salida est�ndar
        while (rs.next()) {
        	newCourse.setId(rs.getString(1));
        	newCourse.setName(rs.getString(2));
        	newCourse.setSumCredits(Integer.parseInt(rs.getString(3)));
        	newCourse.setSemester((Integer.parseInt(rs.getString(4))));
        	newCourse.setKnowledgeArea(rs.getString(4));
        	newCourse.setType(rs.getString(5));
        	result.add(newCourse);
        }
        System.out.println(" Buscadas todas las filas del conjunto resultados JDBC");
        // Cerrar el conjunto de resultados
        rs.close();
        System.out.println(" Cerrado el conjunto de resultados de JDBC");
        
        // Cerrar el objeto Statement
        stmt.close();
        System.out.println(" Cerrado el objeto Statement de JDBC");

        // La conexi�n debe estar en un l�mite de unidad trabajo para permitir cierre
        conn.commit();
        System.out.println ( " Transacci�n confirmada" );
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
        // La conexi�n debe estar en un l�mite de unidad trabajo para permitir cierre
        conn.commit();
        System.out.println ( " Transacci�n confirmada" );
    }
}
