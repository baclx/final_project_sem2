package com.example.projectsem2.controller.mail;

public interface MailService {
    // Method
    // To send a simple email
    void sendSimpleMail();

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(MailDetails details);
}
