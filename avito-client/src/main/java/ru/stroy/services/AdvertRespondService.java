package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.AdvertRespond;

import ru.stroy.repositories.AdvertRespondRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertRespondService {
    private final AdvertRespondRepository advertRespondRepository;
    private final AccountService accountService;

    public Boolean isOwnedRespond(Long id) {
        Optional<AdvertRespond> respond = advertRespondRepository.findById(id);
        return
                respond.isPresent() &&
                        accountService.getContextAccount().getId()
                                .equals(respond.get().getApplicant().getId());
    }
}
