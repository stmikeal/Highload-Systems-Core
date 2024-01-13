package ru.stroy.notificationcenter.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.json.simple.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.stroy.exceptions.JSONParseKeyException;
import ru.stroy.notificationcenter.repositories.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;

    public void sendNotification(JSONObject json) {
        String pattern = notificationRepository
                .findByName(json.get("pattern").toString())
                .orElseThrow(() -> new JSONParseKeyException("Can't find pattern with that name"))
                .getPattern();
        String address = json.get("address").toString();
        String subject = json.get("subject").toString();
        for (var key : json.keySet()) {
            pattern = pattern.replaceAll("?{" + key.toString() +"}", json.get(key).toString());
        }
        javaMailSender.send(createMail(address, subject, pattern));
    }

    private SimpleMailMessage createMail(String address, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(address);
        mail.setSubject(subject);
        mail.setText(message);
        return mail;
    }
}
