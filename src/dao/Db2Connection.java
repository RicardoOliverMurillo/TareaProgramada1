package dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class Db2Connection {
	
	private static Connection conn;
	private static Db2Connection db2Connection;
	private static String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
	
	private Db2Connection() {
		try {
            openConnection("jdbc:db2://dashdb-txn-sbox-yp-dal09-04.services.dal.bluemix.net:50000/BLUDB",
    	    		"cxf11927", "7qmf2j3x@b116dkr");
        } catch (Exception e) {
            System.out.println("Error connecting");
        }
	}
    
    public static Db2Connection getInstance() {
    	if (db2Connection == null){
            db2Connection = new Db2Connection();
        }
        else{
            System.out.println("Connection already stablished....");
        }
        
        return db2Connection;
    }  
    
    private static void openConnection(String url, String user, String password) {
        try {
        	Class.forName(jdbcClassName);
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            System.out.println("conexion abierta / papas fritas");
        } catch (Exception e) {
            System.out.println("Exception opening connection: " + e);
        }
    }

    public static void closeConnection() {
    	try {
    		conn.close();
    	}catch(Exception e) {
    		System.out.println("Exception closing current connection: "+ e);
    	}
    }
    
    public static Connection getConnection() {
    	return conn;
    }
}
