/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.util;

import fi.lampola.lafka.domain.Henkilo;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Markus
 */
@Service
@PropertySource("classpath:application.properties")
public class EmailClient {

    @Value("${smtp.host}")
    private String host;

    @Value("${smtp.port}")
    private String port;

    @Value("${smtp.from}")
    private String from;

    @Value("${smtp.auth}") // true or false
    private String auth;

    @Value("${smtp.username}")
    private String username;

    @Value("${smtp.password}")
    private String password;

    public void send(Henkilo to, String link) {

        Map<String, String> env = System.getenv();
        
        // Override by environment variables
        if (env.containsValue("SMTP_HOST")) {
            host = env.get("SMTP_HOST");
        }
        
        if (env.containsValue("SMTP_PORT")) {
            port = env.get("SMTP_PORT");
        }
        
        if (env.containsValue("SMTP_FROM")) {
            from = env.get("SMTP_FROM");
        }
        
        if (env.containsValue("SMTP_AUTH")) {
            auth = env.get("SMTP_AUTH");
        }
        
        if (env.containsValue("SMTP_USERNAME")) {
            username = env.get("SMTP_USERNAME");
        }
        
        if (env.containsValue("SMTP_PASSWORD")) {
            password = env.get("SMTP_PASSWORD");
        }
        
        Properties props = new Properties();

        if (port.equals("587") || port.equals("465")) {
            if (port.equals("587")) {
                props.put("mail.smtp.starttls.enable", "true");
            } else if (port.equals("465")) { // Tested with smtp.gmail.com
                props.put("mail.smtp.socketFactory.port", port);
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            }

            props.put("mail.smtp.ssl.trust", "*");
        }

        if (Boolean.parseBoolean(auth)) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
        
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(to.getEmail(), false));
            message.setSubject("Salasanan asetus");
            message.setText("Aseta uusi salasana tällä linkillä:\n\n" + link);

            Transport.send(message);
        } catch (Exception e) {
            System.out.println("Email to " + to.getEmail() + " failed!");
        }
    }
}
