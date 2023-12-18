package util;

import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Login;

public class ResourceContainer {

    public static final Long falseId = 123456l;

    public static final Login testLogin1 = createLogin(57875L, "password1");

    public static final Login testLogin2 = createLogin(57872L, "password2");

    public static final Account testAccount1 = createAccount(123L);
    public static final Account testAccount2 = createAccount(321L);
    public static final Account testAccount3 = createAccount(767L);

    private static Account createAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        return account;
    }

    private static Login createLogin(Long id, String password) {
        Login login = new Login();
        login.setId(id);
        login.setPassword(password);
        return login;
    }
}
