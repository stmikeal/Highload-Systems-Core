package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.repositories.AdvertRespondRepository;

@Service
@RequiredArgsConstructor
public class RespondService {
    private final AdvertRespondRepository advertRespondRepository;


}
