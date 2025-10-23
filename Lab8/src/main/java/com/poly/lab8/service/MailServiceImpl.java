package com.poly.lab8.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled; 
import org.springframework.stereotype.Service;

import com.poly.lab8.service.MailService.Mail; 

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
    
    private List<Mail> queue = new ArrayList<>();

    @Override
    public void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            helper.setTo(mail.getTo());
            if (!this.isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!this.isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true); 

            String filenames = mail.getFilenames();
            if (!this.isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    if (file.exists() && file.isFile()) {
                         helper.addAttachment(file.getName(), file);
                    }
                }
            }
            
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi gửi mail: " + e.getMessage(), e);
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().length() == 0);
    }

 
    @Override
    public void push(Mail mail) {
        queue.add(mail);
    }
    
 
    @Scheduled(fixedDelay = 500) 
    public void run() {
        while (!queue.isEmpty()) {
            try {
                Mail mailToSend = queue.remove(0); 
                this.send(mailToSend);
                System.out.println("Mail đã được gửi từ hàng đợi đến: " + mailToSend.getTo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}