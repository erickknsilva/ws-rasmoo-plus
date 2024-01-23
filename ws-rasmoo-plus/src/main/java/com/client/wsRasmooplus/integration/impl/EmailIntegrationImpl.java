package com.client.wsRasmooplus.integration.impl;

import com.client.wsRasmooplus.Exception.EmailFail;
import com.client.wsRasmooplus.integration.EmailIntegration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailIntegrationImpl implements EmailIntegration {

    private final JavaMailSender javaMailSender;

    public EmailIntegrationImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String emailTo, String subject, String body) {

        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("RasmooPlus<erickdecker23@gmail.com>");
            simpleMailMessage.setTo(emailTo);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
        } catch (Exception ex) {
            throw new EmailFail("Falha ao enviar o email");
        }
    }


}
