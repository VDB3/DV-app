package com.ok.dvweb.service;

import com.ok.dvweb.dto.ProspectusDTO;
import com.ok.dvweb.dto.SubscriberDTO;
import com.ok.dvweb.util.ConstantsUtil;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessagingService {

    protected final SendGrid sendGridClient;

    public Response notifyProspectus(ProspectusDTO dto) throws IOException {
        String contentText = String.format(ConstantsUtil.Messaging.CONTENT, dto.getUsername(),
                dto.getMessage(), dto.getPhone(), dto.getMail());
        Mail mail = createMail(contentText);
        return send(mail);
    }

    private Mail createMail(String contentText) {
        Email from = new Email(ConstantsUtil.Messaging.MAIL_FROM);
        Email to = new Email(ConstantsUtil.Messaging.MAIL_TO);
        Content content = new Content("text/plain", contentText);
        return new Mail(from, ConstantsUtil.Messaging.SUBJECT, to, content);
    }

    public Response sendMailToConfirm(String mail, String token) throws IOException {
        String contentText = String.format(ConstantsUtil.Messaging.CONTENT_CONFIRM, token);
        Mail mailSend = createMail(contentText, mail);
        return send(mailSend);
    }

    private Mail createMail(String contentText, String mailTo) {
        Email from = new Email(ConstantsUtil.Messaging.MAIL_FROM);
        Email to = new Email(mailTo);
        Content content = new Content("text/html", contentText);
        return new Mail(from, ConstantsUtil.Messaging.SUBJECT_VERIFY, to, content);
    }

    public void sendNotifySubsVerified(List<String> listMail, String img){
        listMail.forEach((a) -> {
            try {
                createMail(a, img, ConstantsUtil.Messaging.CONTENT_TYPE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createMail(String email, String img, String typeContent) throws IOException {
        Email from = new Email(ConstantsUtil.Messaging.MAIL_FROM);
        Email to = new Email(email);

        Content content = new Content(typeContent, ConstantsUtil.Messaging.CONTENT_IMG);
        Mail mail = new Mail(from, ConstantsUtil.Messaging.SUBJECT_NOTIFY, to, content);

        Attachments attachments = new Attachments();
        attachments.setContent(img);
        attachments.setType("image/png");
        attachments.setFilename("image.png");
        attachments.setDisposition("inline");
        attachments.setContentId("image1");
        mail.addAttachments(attachments);

        send(mail);
    }


    private Response send(Mail mail) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGridClient.api(request);
        log.info("Status Code: " + response.getStatusCode());
        log.info("Body: " + response.getBody());
        log.info("Headers: " + response.getHeaders());
        return response;
    }

}

