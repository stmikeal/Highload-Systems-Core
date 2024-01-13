package ru.stroy.notificationcenter.controllers;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stroy.exceptions.JSONParseKeyException;
import ru.stroy.notificationcenter.services.NotificationService;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendNotification(String rawJson) throws ParseException {
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
