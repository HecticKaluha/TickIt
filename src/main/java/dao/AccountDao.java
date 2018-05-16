package dao;

public interface AccountDao
{
    void authenticate(String username, String password) throws SecurityException;

    String issueToken(String login);
}

