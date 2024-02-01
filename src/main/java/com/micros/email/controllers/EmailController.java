package com.micros.email.controllers;

import com.micros.email.dtos.EmailDto;
import com.micros.email.models.EmailModel;
import com.micros.email.producers.EmailProducer;
import com.micros.email.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailProducer emailProducer;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel email = EmailService.getInstance().sendEmail(emailDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(email);
    }

    @PostMapping("/send-email/queue")
    public ResponseEntity<Object> sendingEmailQueue(@RequestBody @Valid EmailDto emailDto){
        try {
            emailProducer.sendMessage(emailDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("No momento não foi possível completar a solicitação. Tente novamente mais tarde.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Seu email foi processado.");
    }
}
