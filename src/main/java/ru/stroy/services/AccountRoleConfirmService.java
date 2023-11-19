package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.AccountRoleConfirmation;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.AccountRoleConfirmRepository;

@Service
@RequiredArgsConstructor
public class AccountRoleConfirmService {
    private final AccountRoleConfirmRepository accountRoleConfirmRepository;

    public AccountRoleConfirmation createAttachedDocument(Document document, String signature, String description) {
        AccountRoleConfirmation roleConfirmation = new AccountRoleConfirmation();
        roleConfirmation.setConfirmDocument(document);
        roleConfirmation.setSignature(signature);
        roleConfirmation.setDescription(description);
        return  accountRoleConfirmRepository.save(roleConfirmation);
    }
}
