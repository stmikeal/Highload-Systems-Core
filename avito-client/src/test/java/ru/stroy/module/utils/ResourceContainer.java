package ru.stroy.module.utils;

import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.entity.datasource.Company;
import ru.stroy.entity.datasource.Login;

public class ResourceContainer {
    public static final String testRandomString1 = "dfkASJ21a_1ASdl";

    public static final Login testLogin1 = createLogin(57875L, "password");

    public static final Account testAccount1 = createAccount(123L);
    public static final Account testAccount2 = createAccount(321L);
    public static final Account testAccount3 = createAccount(767L);

    public static final AdvertRespond testAdvertRespondApplicant1 = createRespondWithApplicant(testAccount1, 654L);
    public static final AdvertRespond testAdvertRespondApplicant2 = createRespondWithApplicant(testAccount2, 4312L);

    public static final Company testCompany1 = createCompany(847L);
    public static final Company testCompany2 = createCompany(331L);

    private static Login createLogin(Long id, String password) {
        Login login = new Login();
        login.setId(id);
        login.setPassword(password);
        return login;
    }

    private static Account createAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        return account;
    }

    private static AdvertRespond createRespondWithApplicant(Account account, Long id) {
        AdvertRespond advertRespond = new AdvertRespond();
        advertRespond.setId(id);
        advertRespond.setApplicant(account);
        return advertRespond;
    }

    private static Company createCompany(Long id) {
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
