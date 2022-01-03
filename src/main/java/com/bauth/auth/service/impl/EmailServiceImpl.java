package com.bauth.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    @Autowired
    private final JavaMailSender emailSender;

    @Bean
    public SimpleMailMessage sendValidationCode() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
        "This is the test email template for your email:\n%s\n");
        return message;
    }

    /*@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }*/

    public void sendValidationCode(
      String to, String subject, String code) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@bauth.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(String.format("This is the test email template for your email:\n%s\n", code));
        emailSender.send(message);
    }

    public void sendPasswordResetCode(
      String to, String subject, String code) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@bauth.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(String.format("This is the test email template for your email:\n%s\n", code));
        emailSender.send(message);
    }
}