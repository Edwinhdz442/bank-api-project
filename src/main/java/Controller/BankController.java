package Controller;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.ignoreStubs;

import com.fasterxml.jackson.core.JsonParseException;

//import static org.mockito.ArgumentMatchers.nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class BankController {
    private ObjectMapper mapper;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        mapper = new ObjectMapper();
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        return app;
    }

    private void registerHandler(Context ctx) throws JsonProcessingException
    {
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account newAccount = AccountService.addAccount(account);
        if (newAccount == null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(newAccount));
            ctx.status(200);
        }
    }

    private void loginHandler(Context ctx) throws JsonProcessingException
    {
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account user = AccountService.useAccount(account);
        if (user == null) {
            ctx.status(401);
        } else {
            ctx.json(mapper.writeValueAsString(user));
            ctx.status(200);
        }
    }
}
