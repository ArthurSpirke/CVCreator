package com.arthurspirke.cvcreator.model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

public class MailService {
Logger log = Logger.getLogger(MailService.class);
	
	public void sendMessageToUser(PersonalInfo person){
		
		final String msgTitle = getSendedEMailTitle();
		final String msgBody = createMessage(person.getFirstName(), person.getSecondName());
		final String userEmail = person.geteMail();
		final String username = getEmailAdminUsername();
		final String password = getEmailAdminPassword();
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
       Session session = Session.getInstance(prop, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
       });
       
       try{
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress("CVCreator"));
    	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
    	   message.setSubject(msgTitle);
    	   message.setText(msgBody);
    	   
    	   Transport.send(message);
    	   
       } catch (MessagingException ex){
    	   log.error("Error - " + ex);
       }
	}
	
	
	private String createMessage(String firstName, String secondName){
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1>Hello, " + firstName + " " + secondName + "</h1>");
		sb.append("Congratulations on your first resume, established by CV Creator!");
		sb.append("</body>");
		sb.append("</html>");		
		return sb.toString();
	}
	
}
