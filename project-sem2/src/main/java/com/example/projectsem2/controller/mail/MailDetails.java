package com.example.projectsem2.controller.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
