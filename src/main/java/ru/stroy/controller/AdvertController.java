package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stroy.dto.request.AdvertCreateDto;
import ru.stroy.services.AdvertService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;

    @PutMapping
    public void createAdvert(@RequestBody AdvertCreateDto advertCreateDto) {
        advertService.createAdvertByDto(advertCreateDto);
    }
}
