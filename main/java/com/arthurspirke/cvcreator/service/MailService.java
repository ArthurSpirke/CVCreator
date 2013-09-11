package com.arthurspirke.cvcreator.service;

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

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.enums.Language;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

public class MailService {
Logger log = Logger.getLogger(MailService.class);
	
	public void sendMessageToUser(Person person){
		
		final String firstName = person.getPersonalInfo().getFirstName();
		final String secondName = person.getPersonalInfo().getSecondName();
		
				
		final String msgTitle = getSendedEMailTitle();
		final String msgBody = createMessage(firstName, secondName);
		final String eMail = person.getPersonalInfo().geteMail();
		final String username = getEmailAdminUsername();
		final String password = getEmailAdminPassword();

		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		System.out.println("0");
		
       Session session = Session.getInstance(prop, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
       });
       
       try{
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress("CVCreator"));
    	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(eMail));
    	   message.setSubject(msgTitle);
    	   message.setText(msgBody);
    	   System.out.println("1");
    	   Transport.send(message);
    	   System.out.println("2");
    	   
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
	

/*	public static void main(String[] args){
		MailService m = new MailService();
		PersonalInfo p = new PersonalInfo(0, "John", "Amber", "", "dinapoli85@yandex.ru", 0, 0, 0, 0, "", "", "", Language.RUSSIAN);
		m.sendMessageToUser(p);
	}*/
	
}
