package Initialize;

import service.AccountService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Initialize {

    @Inject
    private AccountService accountService;

    public Initialize()
    {

    }

    @PostConstruct
    public void initData(){
        try
        {
            accountService.createAccount("stefanoverhoeve44@gmail.com", "Stefano Verhoeve", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
            accountService.createAccount("hans@live.nl", "Hans Broekmans", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918" );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}