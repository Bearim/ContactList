package utils;

import domain.Contact;
import org.stringtemplate.v4.ST;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtils {
    public static void sendEmail(String to, String subject, String messageText) throws MessagingException {
        boolean sessionDebug = false;

        Properties props = System.getProperties();
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session mailSession = Session.getDefaultInstance(props,
                new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "fogoutenlaught@gmail.com", "!6536528");
                    }
                });

        mailSession.setDebug(sessionDebug);

        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress("fogoutenlaught@gmail.com"));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(messageText);


        Transport.send(msg);
    }

    public static String renderTemplate(String str, String name){
        ST template = new ST(str);
        template.add("name", name);
        String output = template.render();

        return output;
    }

    public static int[] makeIntArr(String[] arr){
        int[] mas = new int[arr.length];
        for(int i = 0; i < mas.length; i++){
            mas[i] = Integer.parseInt(arr[i]);
        }
        return mas;
    }

    public static void sendBirthDayContacts(List<Contact> contacts) throws MessagingException{
        String message = "Contacts who have birthday today: ";

        for(Contact contact : contacts){
            message += contact.getName() + " , his eMail-> " + contact.geteMail() + "\n";
        }

        sendEmail("fogoutenlaught@gmail.com", "Birthday contacts", message);
    }

}
