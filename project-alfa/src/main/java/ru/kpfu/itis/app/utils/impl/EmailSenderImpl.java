package ru.kpfu.itis.app.utils.impl;

import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.app.utils.EmailSender;

import javax.mail.internet.MimeMessage;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class EmailSenderImpl implements EmailSender {

    private JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @SneakyThrows
    public void sendEmailMessage(String destEmail, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setContent(text, "text/html");
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(destEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }
}
