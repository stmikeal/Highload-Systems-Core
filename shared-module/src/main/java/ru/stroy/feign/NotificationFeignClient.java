package ru.stroy.feign;

import org.json.simple.parser.ParseException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stroy.entity.datasource.Account;

@FeignClient("notification-center")
public interface NotificationFeignClient {

    @GetMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void sendNotification(String rawJson) throws ParseException;
}
