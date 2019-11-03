package businessLogic.user;

import java.sql.SQLException;

public interface SessionInterface {

	public abstract boolean login(String username, String password, String role) throws SQLException;
	
}
