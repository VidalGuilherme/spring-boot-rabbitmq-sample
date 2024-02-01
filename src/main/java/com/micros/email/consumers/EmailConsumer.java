package com.micros.email.consumers;

import com.micros.email.dtos.EmailDto;
import com.micros.email.models.EmailModel;
import com.micros.email.services.EmailService;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class EmailConsumer {

    public void receiveMessage(@Payload EmailDto emailDto) {
        System.out.println("Received Message...");
        System.out.println(emailDto);
        EmailModel email = EmailService.getInstance().sendEmail(emailDto);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }

}
