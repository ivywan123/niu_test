package Common;


import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.CharBuffer;

import java.util.Properties;


public class ReportMail {

	public static void main( String[] args ) throws IOException, MessagingException{
		String mailValue=Public.html("mail.txt");
		String newStr = new String(mailValue.getBytes(), "utf-8");  
	    ReportMail.reportmail(newStr, "test","ivy363701080@163.com");
	}

	/**
	 * 邮件发送服务配置
	 * @param mailValue
	 * @param tittle
	 * @param touser
	 * @throws MessagingException
	 * @throws IOException
     */
    public static void reportmail(String mailValue,String tittle,String touser) throws MessagingException, IOException {
    	  final Properties props = new Properties();
          String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
          props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
          props.put("mail.smtp.socketFactory.fallback", "false");
          props.put("mail.store.protocol", "smtp"); 
          props.put("mail.smtp.host", "smtp.163.com");
          props.put("mail.smtp.port", "465");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.auth", "true");
          props.put("mail.user", "ivy363701080@163.com");
          props.put("mail.password", "wan1129qin");
          Authenticator authenticator = new Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                  String userName = props.getProperty("mail.user");
                  String password = props.getProperty("mail.password");
                  return new PasswordAuthentication(userName, password);
              }
          };
          Session mailSession = Session.getInstance(props, authenticator);
          MimeMessage message = new MimeMessage(mailSession);
          InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
          message.setFrom(form);
          InternetAddress[] toall =userMail(touser);
          if(touser.isEmpty()==false){
          message.setRecipients(RecipientType.TO, toall);
          }
          message.setSubject(tittle);
          message.setContent(mailValue, "text/html;charset=utf-8");
        Transport.send(message);
   
    }

	/**
	 * 邮件接收人组合
	 * @param users
	 * @return
	 * @throws AddressException
     */
    public  static   InternetAddress[]  userMail(String users) throws AddressException{
	     InternetAddress[] toall = null;
	     try{
		     String mail[]=users.split(","); 
		    if(mail.length==1){
		    	InternetAddress  to1= new InternetAddress(mail[0]);
		    	toall = new InternetAddress[]{to1};
		    }
		    if(mail.length==2){
		    	InternetAddress  to1= new InternetAddress(mail[0]);
		    	InternetAddress  to2= new InternetAddress(mail[1]);
		    	toall = new InternetAddress[]{to1,to2};
		    	
		    }
		    if(mail.length==3){
		    	InternetAddress  to1= new InternetAddress(mail[0]);
		    	InternetAddress  to2= new InternetAddress(mail[1]);
		    	InternetAddress  to3= new InternetAddress(mail[2]);
		       	toall = new InternetAddress[]{to1,to2,to3};
		    }
		    if(mail.length==4){
		     	InternetAddress  to1= new InternetAddress(mail[0]);
		    	InternetAddress  to2= new InternetAddress(mail[1]);
		    	InternetAddress  to3= new InternetAddress(mail[2]);
		    	InternetAddress  to4= new InternetAddress(mail[3]);
		    	toall = new InternetAddress[]{to1,to2,to3,to4};
		    }
		    if(mail.length==5){
		     	InternetAddress  to1= new InternetAddress(mail[0]);
		    	InternetAddress  to2= new InternetAddress(mail[1]);
		    	InternetAddress  to3= new InternetAddress(mail[2]);
		    	InternetAddress  to4= new InternetAddress(mail[3]);
		    	InternetAddress  to5= new InternetAddress(mail[4]);
		    	toall = new InternetAddress[]{to1,to2,to3,to4,to5};
		    }   
	         }catch(Exception e){
		    	toall = new InternetAddress[]{};
		    }

		    return  toall;
}

}