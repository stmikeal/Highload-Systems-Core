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
import ru.stroy.dto.request.AdvertRespondCreateDto;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.repositories.AdvertRespondRepository;
import ru.stroy.services.RespondService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/respond")
@Validated
public class RespondController {
    private final RespondService respondService;
    private final AdvertRespondRepository advertRespondRepository;

    @PutMapping
    @ResponseBody
    public void createAdvertRespond(@Valid @RequestBody AdvertRespondCreateDto advertRespondCreateDto) {
        respondService.createAdvertRespondByDto(advertRespondCreateDto);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void setAdvertRespond(@Valid @RequestBody AdvertRespondCreateDto advertRespondCreateDto,
                                 @PositiveOrZero @PathVariable Long id){
        respondService.setAdvertRespondByDto(advertRespondCreateDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteAdvertRespond(@PositiveOrZero @PathVariable Long id){
        respondService.deleteAdvertRespondByDto(id);
    }

    @GetMapping
    public ResponseEntity<Page<AdvertRespond>> getAllAdvert(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_RANGE, String.valueOf(advertRespondRepository.count()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(advertRespondRepository.findAll(PageRequest.of(offset, limit)));
    }

    @PostMapping("/{advertId}/document/{documentId}")
    @ResponseBody
    public void attachDocumentRespond(@PositiveOrZero @PathVariable Long advertId,
                                      @PositiveOrZero @PathVariable Long documentId){
        respondService.attachDocumentRespond(advertId, documentId);
    }

}
