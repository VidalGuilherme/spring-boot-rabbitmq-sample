package com.micros.email.models;

import com.micros.email.enums.StatusEmail;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_emails")
public class EmailModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEmail;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    private EmailModel(String ownerRef, String emailFrom, String emailTo, String subject, String text) {
        this.ownerRef = ownerRef;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
    }

    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }

    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }

    public StatusEmail getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }

    //Builder Pattern
    public static class EmailModelBuilder{

        private String ownerRef;
        private String emailFrom;
        private String emailTo;
        private String subject;
        private String text;

        public EmailModelBuilder ownerRef(String ownerRef) {
            this.ownerRef = ownerRef;
            return this;
        }

        public EmailModelBuilder emailFrom(String emailFrom) {
            this.emailFrom = emailFrom;
            return this;
        }

        public EmailModelBuilder emailTo(String emailTo) {
            this.emailTo = emailTo;
            return this;
        }

        public EmailModelBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailModelBuilder text(String text) {
            this.text = text;
            return this;
        }

        public EmailModel build(){
            return new EmailModel(ownerRef, emailFrom, emailTo, subject, text);
        }
    }

}
