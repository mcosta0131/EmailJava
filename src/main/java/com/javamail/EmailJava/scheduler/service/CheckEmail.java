package com.javamail.EmailJava.scheduler.service;

import org.springframework.stereotype.Component;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class CheckEmail {

	public void setEmailProperties() {
		Properties props = new Properties();

		//PROPERTIES GMAIL
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		
		//PROPERTIES OUTLOOK
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");
		
		
		System.out.println("SETTING PROPERTIES....");
		createSession(props);
	}

	public void createSession(Properties props) {
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("", "");
			}
		});
		System.out.println("CREATING SESSION...");
		/** Ativa Debug para sessão */
	    session.setDebug(true);
	    readBox(session);
	  //  sendMessage(session);
	}
	
	public void readBox(Session session) {
		
		try {
			Store mailStore = session.getStore("imap");
			mailStore.connect("", "", ""); 
			Folder dFolder = mailStore.getDefaultFolder();
			System.out.println();
		} catch (MessagingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//METODO PARA ENVIAR MENSAGEM
	public void sendMessage(Session session) {
		try {
			  System.out.println("SENDING MESSAGE...");
		      Message message = new MimeMessage(session);
		      message.setFrom(new InternetAddress("")); 
		      //Remetente
		 
		      Address[] toUser = InternetAddress //Destinatário(s)
		                 .parse("");  
		 
		      message.setRecipients(Message.RecipientType.TO, toUser);
		      message.setSubject("Enviando email com JavaMail");//Assunto
		      message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
		      /**Método para enviar a mensagem criada*/
		      Transport.send(message);
		 
		      System.out.println("Feito!!!");
		 
		     } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
		  
	}
}
