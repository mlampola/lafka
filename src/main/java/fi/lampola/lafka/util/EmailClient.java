/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.util;

import fi.lampola.lafka.domain.Henkilo;
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

    @Value("${smtp.username}")
    private String username;

    @Value("${smtp.password}")
    private String password;

    public void send(Henkilo to, String link) {

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

        props.put("mail.smtp.auth", "true");
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
            message.setSubject("Password reset link");
            message.setText("Aseta uusi salasana tällä linkillä:\n\n" + link);

            Transport.send(message);
        } catch (Exception e) {
            System.out.println("Email to " + to.getEmail() + " failed!");
        }
    }
}
