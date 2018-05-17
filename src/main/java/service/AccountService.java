package service;

import dao.AccountDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class AccountService implements Serializable {

    @Inject
    private AccountDao accountDao;

    public AccountService() {
    }

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

    public void createAccount(String email, String username, String password){
        accountDao.createAccount(email, username, password);
    }
}
