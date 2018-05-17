package controller;

import jsonbody.LoginAccountBody;
import service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@RequestScoped
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    @Inject
    private AccountService accountService;


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginAccountBody loginAccountBody) {
        try {

            // Authenticate the user using the credentials provided
            accountService.authenticate(loginAccountBody.getUsername(), loginAccountBody.getPassword());

            // Issue a token for the user
            String token = accountService.issueToken(loginAccountBody.getUsername());

            // Return the token on the response
            return Response.ok("\"" + token + "\"")
                    .header(AUTHORIZATION, token)
                    .build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
}
