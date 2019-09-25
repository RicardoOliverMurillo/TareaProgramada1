package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import userLogic.Student;


public class DaoStudent {
	
	private Statement stmt;
    private ResultSet rs;
    private Db2Connection db; 
    private Connection conn;
    private ArrayList<Student> result = new ArrayList<Student>();
    public DaoStudent() {
    	db = Db2Connection.getInstance();
		conn = Db2Connection.getConnection();
    }
	
	public ArrayList<Student> selectQuery(String query) throws SQLException{
		Student newStudent = new Student();
        stmt = conn.createStatement();                                           
        System.out.println(" Creado el objeto Statement de JDBC");
        // Ejecutar una consulta y generar instancia del conjunto de resultados
        rs = stmt.executeQuery(query);
        System.out.println(" Creado el objeto JDBC ResultSet");
        // Imprimir todos los números de empleado en el dispositivo de salida estándar
        while (rs.next()) {
        	newStudent.setId(rs.getString(1));
        	newStudent.setName(rs.getString(2));
        	newStudent.setLastName(rs.getString(3));
        	newStudent.setEmail(rs.getString(4));
        	newStudent.setPassword(rs.getString(5));
        	result.add(newStudent);
        }
        System.out.println(" Buscadas todas las filas del conjunto resultados JDBC");
        // Cerrar el conjunto de resultados
        rs.close();
        System.out.println(" Cerrado el conjunto de resultados de JDBC");
        
        // Cerrar el objeto Statement
        stmt.close();
        System.out.println(" Cerrado el objeto Statement de JDBC");

        // La conexión debe estar en un límite de unidad trabajo para permitir cierre
        conn.commit();
        System.out.println ( " Transacción confirmada" );
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

}
