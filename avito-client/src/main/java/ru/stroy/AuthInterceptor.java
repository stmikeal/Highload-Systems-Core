package ru.stroy;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
public class AuthInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization",
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest().getHeader("Authorization"));
    }

}
