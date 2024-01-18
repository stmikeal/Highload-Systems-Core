package ru.stroy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("account-server")
public interface AccountClientFeignClient {

    @PutMapping(path = "/empty", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Long createEmptyAccount();
}
