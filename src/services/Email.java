package services;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Email to use the API and send emails to users 
 */
public class Email {
	private final static String username = "tectrabajos2019@gmail.com";
	private final static String password = "ati123456";

	/**
	 * the method send the email with the information to an specific user
	 * @param mail_to the user that is going to receive the email
	 * @param subject the subject of the email
	 * @param message the body of the email (message)
	 */
    public static void sendEmail(String mail_to, String subject, String message) {
        Properties prop = configuration();
        
        Session session = Session.getDefaultInstance(prop, null);
        session.setDebug(true);
        
        Message mailMessage = new MimeMessage(session);
        
        try {
	        mailMessage.setFrom(new InternetAddress(username));
	        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
	        mailMessage.setContent(message, "text/html");
	        mailMessage.setSubject(subject);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", username, password);
	        
	        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * method that set the email properties
     * @return a Properties object
     */
    private static Properties configuration() {
    	Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.port", "465");
        return prop;
    }
}
