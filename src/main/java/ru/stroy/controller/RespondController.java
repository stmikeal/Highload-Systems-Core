package ru.stroy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AdvertRespondCreateDto;
import ru.stroy.services.RespondService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/respond")
@Validated
public class RespondController {
    private final RespondService respondService;

    @PutMapping
    @ResponseBody
    public void createAdvertRespond(@Valid @RequestBody AdvertRespondCreateDto advertRespondCreateDto) {
        respondService.createAdvertRespondByDto(advertRespondCreateDto);
    }

}
