package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AdvertCreateDto;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.services.AdvertService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertRepository advertRepository;

    @PutMapping
    public void createAdvert(@RequestBody AdvertCreateDto advertCreateDto) {
        advertService.createAdvertByDto(advertCreateDto);
    }

    @GetMapping
    @ResponseBody
    public List<Advert> getAllAdvert() {
        return advertRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Advert getAdvert(@PathVariable Long id) {
        return advertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Advert not found"));
    }
}
