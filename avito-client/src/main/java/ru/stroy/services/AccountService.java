package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.AccountPutConfirmationDto;
import ru.stroy.entity.datasource.*;
import ru.stroy.feign.IdpClientFeignClient;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.AccountRoleRepository;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountRoleConfirmService accountRoleConfirmService;
    private final DocumentService documentService;
    private final AccountRoleLinkService accountRoleLinkService;
    private final AccountRoleRepository accountRoleRepository;
    private final IdpClientFeignClient idpClientFeignClient;

    public Account getContextAccount() {
        Long id = idpClientFeignClient.getContextAccount().getId();
        return accountRepository.getById(id);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(new Account());
    }

    public void confirmAccountByDto(AccountPutConfirmationDto accountDto) {
        Document document = documentService.getDocumentWithPermission(accountDto.getDocumentId());
        AccountRoleConfirmation roleConfirmation = accountRoleConfirmService
                .createAttachedDocument(document, accountDto.getSignature(), accountDto.getDescription());
        AccountRole accountRole = accountRoleRepository.findByCode(accountDto.getRole().getCode());
        accountRoleLinkService.createRoleLink(getContextAccount(), roleConfirmation, accountRole);
    }
}
