package userLogic;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import dao.DaoStudent;

public class Session {
	
	private static String user;
	
	public Session(String user) {
		Session.setUser(user);
	}
	
	public boolean login(String username, String password, String role) throws SQLException {
		DaoStudent db = new DaoStudent();
		ArrayList<Student> result = db.selectQuery("SELECT * FROM CXF11927.STUDENT WHERE ID = '"+ username+"'");
		System.out.println(result.size());
		for(int i = 0; i < result.size(); i++) {
			String tempPassword = result.get(i).getPassword();
			String decryptedPassword = "";
			try {
				decryptedPassword = decrypt(tempPassword);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(result.get(i).getId().equals(username) && decryptedPassword.equals(password)) {
				return true;
			}
		}
		return false;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Session.user = user;
	}
	
	private static String decrypt(String pPassword) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(pPassword.getBytes());
        return new String(decode, "utf-8");
    }

}
