package service;

import dao.AccountDao;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {
    @Inject
    private AccountDao accountDao;

    public void authenticate(String login, String password) throws SecurityException {
        try {
            accountDao.authenticate(login, password);
        } catch (Exception e) {
            throw new SecurityException("Invalid user/password");
        }
    }

    public String issueToken(String login) {
        return accountDao.issueToken(login);
    }
}
