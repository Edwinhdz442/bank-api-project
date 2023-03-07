package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    public static Account addAccount(Account account)
    {
        return (account.username.isEmpty() || account.password.length() < 4) ?
            null : AccountDAO.addAccount(account);
    }

    public static Account useAccount(Account account)
    {
        return AccountDAO.useAccount(account);
    }
}