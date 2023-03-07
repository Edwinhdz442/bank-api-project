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

public class BankController {
    private ObjectMapper mapper;
    public Javalin startAPI() {
        mapper = new ObjectMapper();
        Javalin app = Javalin.create();
        app.post("/register", this::accountRegister);
        app.delete("/account/{id}", this::accountDelete);
        app.post("/login", this::accountLogin);
        return app;
    }

    private void accountRegister(Context ctx) throws JsonProcessingException
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
    
    private void accountDelete(Context ctx) {
        Account account = AccountService.rmAccount(Integer.parseInt(ctx.pathParam("id")));
        if (account != null)
            ctx.json(account);
        ctx.status(200);
    }

    private void accountLogin(Context ctx) throws JsonProcessingException
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
