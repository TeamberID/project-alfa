package ru.kpfu.itis.app.utils;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface EmailSender {
    void sendEmailMessage(String destEmail, String subject, String text);
}
