package com.ok.dvweb.service;

import com.ok.dvweb.dto.ProspectusDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MessagingService {

    protected final SendGrid sendGridClient;
    private final String MAIL_FROM = "bogadovd@hotmail.com";
    private final String MAIL_TO = "etcdev89@gmail.com";
    private final String SUBJECT = "NUEVO PROSPECTO";
    private final String CONTENT = "Hola amador!, tenes un mensaje de: %s, dice: %s. " +
            "te dejo los datos de contacto: (%s) (%s)";

    public Response notifyProspectus(ProspectusDTO dto) throws IOException {
        String contentText = String.format(CONTENT, dto.getUsername(), dto.getMessage(), dto.getPhone(), dto.getMail());
        Mail mail = createMail(contentText);
        return send(mail);
    }

    private Response send(Mail mail) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGridClient.api(request);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody());
        System.out.println("Headers: " + response.getHeaders());
        return response;
    }

    private Mail createMail(String contentText) {
        Email from = new Email(MAIL_FROM);
        Email to = new Email(MAIL_TO);
        Content content = new Content("text/plain", contentText);
        return new Mail(from, SUBJECT, to, content);
    }

}

