package dao;

public interface AccountDao
{
    void authenticate(String username, String password) throws SecurityException;

    String issueToken(String login);

    void createAccount(String email, String username, String password);

    void test(String message);
}

