package com.micros.email.services;

import com.micros.email.interfaces.MailSender;
import com.micros.email.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderJava implements MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void send(EmailModel emailModel){
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom(emailModel.getEmailFrom());
        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());
        javaMailSender.send(message);
    }
}
