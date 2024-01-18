package ru.stroy.controllers;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.stroy.exceptions.JSONParseKeyException;
import ru.stroy.services.NotificationService;
import ru.stroy.entity.datasource.Message;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @KafkaListener(id = "notification", topics = {"server.notification"}, containerFactory = "singleFactory")
    public void consume(String message) throws ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(message);
        if (!json.containsKey("pattern"))
            throw new JSONParseKeyException("Can't find pattern name in JSON object");
        if (!json.containsKey("address"))
            throw new JSONParseKeyException("Can't find address in JSON object");
        if (!json.containsKey("subject"))
            throw new JSONParseKeyException("Can't find subject in JSON object");
        else
            notificationService.sendNotification(json);
    }
}
