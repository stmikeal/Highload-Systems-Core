package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stroy.services.RespondService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/respond")
@Validated
public class RespondController {
    private final RespondService respondService;

}
