package ru.stroy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;
import ru.stroy.entity.datasource.Account;

@FeignClient("idp-server")
public interface IdpClientFeignClient {

    @GetMapping(path = "/context", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Account getContextAccount();
}
