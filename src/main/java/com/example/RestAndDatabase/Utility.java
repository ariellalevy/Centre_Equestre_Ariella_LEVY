package com.example.RestAndDatabase;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static void sendingEmail(String adressemail, String subject, String corpsMessage){
        // Add recipient
        String to = adressemail;
        // Add sender
        String from = "centreequestrekafir@gmail.com";
        final String username = "centreequestrekafir@gmail.com";//your Gmail username
        final String password = "L&nCx&94";//your Gmail password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject
            message.setSubject(subject);

            // Put the content of your message
            message.setText(corpsMessage);

            // Send message
            Transport.send(message);
            message.getSentDate();
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean calculeDate(String date){
        int jour = Integer.parseInt(date.substring(8));
        int mois = Integer.parseInt(date.substring(5,7));
        int annee = Integer.parseInt(date.substring(0,4));
        Date dateCurrent = new Date();
        if(((dateCurrent.getDate() == (jour-1)) && ((dateCurrent.getMonth()+1)==mois) && ((dateCurrent.getYear()+1900)==annee))
                || ((dateCurrent.getDate() == (jour)) && ((dateCurrent.getMonth()+1)==mois) && ((dateCurrent.getYear()+1900)==annee))){
            return true;
        }else{
            return false;
        }
    }

    public static String hashPassword(String password){
        // Hashage d'un mot de passe
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        // Il est possible d'augmenter la complexité (et donc le temps
        // de traitement) en passant le "workfactor" en paramètre
        // ici : 12 La valeur par défaut est 10
        hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hashed;
    }

    public static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
}