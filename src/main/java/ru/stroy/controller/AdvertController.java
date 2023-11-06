package ru.stroy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AdvertCreateDto;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.services.AdvertService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advert")
@Validated
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertRepository advertRepository;

    @PutMapping
    public void createAdvert(@Valid @RequestBody AdvertCreateDto advertCreateDto) {
        advertService.createAdvertByDto(advertCreateDto);
    }

    @GetMapping
    public ResponseEntity<Page<Advert>> getAllAdvert(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_RANGE, String.valueOf(advertRepository.count()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(advertRepository.findAll(PageRequest.of(offset, limit)));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Advert getAdvert(@PositiveOrZero @PathVariable Long id) {
        return advertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Advert not found"));
    }
}
