package com.example.demo.services;

import java.util.Date;

// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.domain.Client;
import com.example.demo.domain.Order;

public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    // @Autowired
    // private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Order obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
        sendMail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    protected String htmlFromTemplateOrder(Order obj) {
        Context context = new Context();
        context.setVariable("order", obj);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendNewPassword(Client cl, String newPass) {
        SimpleMailMessage sm = preparenewPasswordEmail(cl, newPass);
        sendMail(sm);
    }

    protected SimpleMailMessage preparenewPasswordEmail(Client obj, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Alterar senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }

    /*@Override
    public void sendOrderConfirmationHtmlEmail(Order obj) {
        try {
            MimeMessage mm = prepareMimeMessageFromOrder(obj);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(obj);
        }
    }*/

    /*protected MimeMessage prepareMimeMessageFromOrder(Order obj) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrder(obj), true);
        return mm;
    }*/

}
