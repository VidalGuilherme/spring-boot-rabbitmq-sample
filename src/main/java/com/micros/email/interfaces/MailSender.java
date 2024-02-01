package com.micros.email.interfaces;

import com.micros.email.models.EmailModel;

public interface MailSender {
    void send(EmailModel emailModel);
}
