package dao;

import domain.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import util.DateUtil;
import util.KeyGenerator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.common.hash.Hashing;

@Stateless
public class AccountDaoJPAImp implements AccountDao {

    //@PersistenceContext(unitName = "tickitPersistence")
    //private EntityManager em;

    @Inject
    private KeyGenerator keyGenerator;
    @Context
    private UriInfo uriInfo;

    @Inject
    private DateUtil dateutil;

    private AccountDaoJPAImp() {

    }

    @Override
    public void authenticate(String username, String password) throws SecurityException {
        try {
            /*Account account = em.createQuery("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password", Account.class)
                    .setParameter("username", username).setParameter("password", Hashing.sha256()
                            .hashString(password, StandardCharsets.UTF_8)
                            .toString())
                    .getSingleResult();*/
        } catch (Exception e) {
            throw new SecurityException("Invalid user/password");
        }
    }

    @Override
    public String issueToken(String username) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuer(username)
                .setIssuedAt(new Date())
                .setExpiration(dateutil.toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }
}