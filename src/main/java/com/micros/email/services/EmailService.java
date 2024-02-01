package com.micros.email.services;

import com.micros.email.dtos.EmailDto;
import com.micros.email.enums.StatusEmail;
import com.micros.email.factories.EmailModelFactory;
import com.micros.email.interfaces.MailSender;
import com.micros.email.models.EmailModel;
import com.micros.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class EmailService {

    private static EmailService instance;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private MailSender emailSenderJava; //Here we can change the mail sender implementation

    @Bean
    public static EmailService getInstance(){
        if(Objects.isNull(instance)){
            instance = new EmailService();
        }

        return instance;
    }

    public EmailModel sendEmail(EmailDto emailDto){
        EmailModel email = EmailModelFactory.getInstance(emailDto);
        email.setSendDateEmail(LocalDateTime.now());
        try{
            emailSenderJava.send(email);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
