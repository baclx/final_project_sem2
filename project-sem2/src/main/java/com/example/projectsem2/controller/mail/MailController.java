package com.example.projectsem2.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    // Sending a simple Email
    @GetMapping("/sendMail")
    public void sendMail() {
        mailService.sendSimpleMail();
    }

//    @PostMapping("/sendMail") - api
//    public void sendMail(
//            @RequestBody MailDetails details
//    ) {
//        mailService.sendSimpleMail(details);
//    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody MailDetails details
    ) {
        return mailService.sendMailWithAttachment(details);
    }
}
