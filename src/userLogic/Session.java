package userLogic;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import dao.DaoStudent;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Session to control the login. 
 */
public class Session implements SessionInterface{
	
	private static String user;
	
	/**
	 * The Constructor of the class Session
	 * @param user that sign in the platform
	 */
	public Session(String user) {
		Session.setUser(user);
	}
	
	/**
	 * method that control the login process
	 * @param username of the user that is going to sign in
	 * @param password password of the user 
	 * @param role role of the user
	 * 
	 * @return boolean if true login success if false credentials error 
	 */
	public boolean login(String username, String password, String role) throws SQLException {
		DaoStudent db = new DaoStudent();
		ArrayList<Student> result = db.selectQuery("SELECT * FROM USERS WHERE IDUSER = '"+ username+"' AND ROLE = '"+role+"'");
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

	/**
	 * method that get the user that is going to sign in 
	 * @return
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * method that set the user that sign in the platform 
	 * @param user
	 */
	public static void setUser(String user) {
		Session.user = user;
	}
	
	/**
	 * Method that decrypt the password of the student
	 * @param pPassword that is going to be decrypted 
	 * @return string with the password decrypted 
	 * @throws UnsupportedEncodingException
	 */
	private static String decrypt(String pPassword) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(pPassword.getBytes());
        return new String(decode, "utf-8");
    }
	
	/**
	 * Method that encrypt the password of the user
	 * @param pPassword that is going to be encrypted
	 * @return string with the password encrypted
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String pPassword) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(pPassword.getBytes("utf-8"));
    }
}
