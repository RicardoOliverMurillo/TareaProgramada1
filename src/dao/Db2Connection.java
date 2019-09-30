package dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class Db2Connection {
	
	private static Connection conn;
	private static Db2Connection db2Connection;
	
	private Db2Connection() {
		try {
			 String hostName = "server1pyr.database.windows.net";
		     String dbName = "PYR1";
		     String user = "adminpyr@server1pyr";
		     String password = "Ati123456.";
		     String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
		            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
			openConnection(url);
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
    
    private static void openConnection(String url) {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	conn = DriverManager.getConnection(url);
            System.out.println("conexion abierta");
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