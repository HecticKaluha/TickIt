package dao;

public interface AccountDao {
    void test(String message);

    void createAccount(String email, String username, String password);

    void authenticate(String username, String password) throws SecurityException;

    String issueToken(String login);
}
