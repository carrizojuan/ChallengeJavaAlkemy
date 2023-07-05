package com.javaChallenge.demo.services;

import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    public String sendWelcomeEmail(String recipientEmail) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("juancru68@gmail.com");
        String subject = "BIENVENIDO";
        Email to = new Email(recipientEmail);
        Content content = new Content("text/plain", "Bienvenido a mi pagina en Spring");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
