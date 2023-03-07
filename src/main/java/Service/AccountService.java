package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    public static Account addAccount(Account account) {
        return (account.account_type == Account.Account_Type.NULL || account.username.isEmpty() ||
            account.password.length() < 4 || account.personal_info.full_name.isEmpty()) ?
            null : AccountDAO.insertAccount(account);
    }

    public static Account rmAccount(int id) {
        Account account = AccountDAO.getAccount(id);
        if (account == null)
            return null;
        AccountDAO.deleteAccount(id);
        return account;
    }

    public static Account useAccount(Account account) {
        return AccountDAO.userAccount(account.username, account.password);
    }

    /*
    public static Account deposit(Account account, int sum) {
        return sum > 0 ? AccountDAO.newTransaction(sum) : null;
    }

    public static Account withdraw(Account account, int sum) {
        return sum > 0 ? AccountDAO.newTransaction(-sum) : null;
    }

    public static Account transfer(Account from, Account to, int sum) {
        return sum > 0 ? AccountDAO.transfer(from ,to, sum) : null;
    }
    */
}
