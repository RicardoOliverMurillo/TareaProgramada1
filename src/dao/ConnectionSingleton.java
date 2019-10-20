package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
	
	private static Connection conn;
	
	private ConnectionSingleton() {
	}
    
    public static Connection getInstance() {
    	if (conn == null){
    		String hostName = "server1pyr.database.windows.net";
		    String dbName = "PYR1";
		    String user = "adminpyr@server1pyr";
		    String password = "Ati123456.";
		    String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
		            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
		    try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection(url);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		    
    	}
        return conn;
    }
}