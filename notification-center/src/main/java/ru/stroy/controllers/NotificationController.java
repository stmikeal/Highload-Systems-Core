package ru.stroy.controllers;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.stroy.entity.datasource.Message;
import ru.stroy.exceptions.JSONParseKeyException;
import ru.stroy.services.NotificationService;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @KafkaListener(id = "notification", topics = {"server.notification"}, containerFactory = "singleFactory")
    public void consume(Message message) throws ParseException{
        JSONObject json = (JSONObject) new JSONParser().parse(message.getJson());
        if (!json.containsKey("json"))
            throw new JSONParseKeyException("Can't find body in JSON object");
        sendNotification(json.get("json").toString());
    }
    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendNotification(@RequestBody String rawJson) throws ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(rawJson);
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
