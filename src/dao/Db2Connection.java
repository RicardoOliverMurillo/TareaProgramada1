package dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class Db2Connection {
	
	private static Connection conn;
	private static Db2Connection db2Connection;
	//private static String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
	
	private Db2Connection() {
		try {
			 String hostName = "server1pyr.database.windows.net"; // update me
		     String dbName = "PYR1"; // update me
		     String user = "adminpyr@server1pyr"; // update me
		     String password = "Ati123456."; // update me
		     String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
		            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
            //openConnection("jdbc:db2://dashdb-txn-sbox-yp-dal09-04.services.dal.bluemix.net:50000/BLUDB", "cxf11927", "7qmf2j3x@b116dkr");
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
    
    //private static void openConnection(String url, String user, String password) {
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
    
    public static void main(String[] args) throws ClassNotFoundException {
    	Db2Connection.getInstance();
    }
}