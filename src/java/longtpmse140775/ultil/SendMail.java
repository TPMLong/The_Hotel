/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.ultil;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
public class SendMail implements Serializable{

    private final Logger LOGGER = Logger.getLogger(SendMail.class);
    private String userEmail;
    private String code;
    
    public SendMail(String userMail, String code) {
        this.userEmail = userMail;
        this.code = code;
    }
    
//    public SendMail(String userMail, String body) {
//        this.userEmail = userMail;
//        this.information = body;
//    }

    public void sendMail() {
        final String email = "trieuminhlong2000@gmail.com";
        final String password = "trieuphuocminhlong";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("VERIFICATION CODE");
            message.setText("Thank for signing up for my website!\n Your verification code: " + "http://localhost:8080/The_Hotel/CheckCodeServlet?key1=" + code +"&key2="+userEmail);
            Transport.send(message);
        } catch (Exception e) {
            LOGGER.error("Error in SendingEmail: " + e);
        }
    }
    
    public void sendMailBooking() {
        final String email = "trieuminhlong2000@gmail.com";
        final String password = "trieuphuocminhlong";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Booking infomation");
            message.setText("Thank you for using out service!\n Your room information: " + code);
            Transport.send(message);
        } catch (Exception e) {
            LOGGER.error("Error in SendingEmail: " + e);
        }
    }
}
