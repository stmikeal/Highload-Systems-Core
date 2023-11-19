package ru.stroy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
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



}
