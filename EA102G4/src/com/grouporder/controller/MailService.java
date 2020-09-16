package com.grouporder.controller;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailService {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public static void main(String[]args) {
//		new MailService().sendMail("p124127@gmail.com","111","123","C:\\img\\20200908175503.jpg");
	}
	public void sendMail(String to,String mem_name,String classDetail ,byte[] pathFormQRcode) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "p1241271@gmail.com";
	     final String myGmail_password = "ingym0917";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });
		   Message message = new MimeMessage(session);
		   MimeMultipart multipart = new MimeMultipart("related");
		   BodyPart messageBodyPart = new MimeBodyPart();
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   String htmlText ="<html>"
		     + " <body>"
		     + "  <p>"+classDetail+"</p>"
		     +"<p>上課前一小時就可以打卡</p><br>"
		     +"<h3 style='color=red'>提醒:請務必讓教練掃描您的QRCODE!!以保證您的上課權益</h3>"
		     + "  <img src=\"cid:attach\"/>"
		     + " </body>"
		     + "</html>"  ;
		   messageBodyPart.setContent(htmlText, "text/html;charset=utf-8;");
		   multipart.addBodyPart(messageBodyPart);
		   
		   MimeBodyPart imagePart = new MimeBodyPart();
		   imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(pathFormQRcode,"image/jpeg")));
		   
		//  設置ID
		   imagePart.setHeader("Content-ID","<attach>");
		   imagePart.setHeader("Content-Type", "image/png");
		   imagePart.setDisposition(MimeBodyPart.INLINE);
		   imagePart.setFileName("attach.png");
		   //  內容
		   multipart.addBodyPart(imagePart);
		   //設定信中的主旨  
//		   message.setSubject(subject);
		   message.setSubject("您在INGYM購買了新的團課");
		   //設定信中的內容 
//		   message.setText("上課時間"+classDetail+"<br>");
//		   message.setText(pathFormQRcode)
		   message.setContent(multipart);
		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
}
