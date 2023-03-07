package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    public static Account addAccount(Account account)
    {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.username);
            statement.setString(2, account.password);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                account.account_id = (int)result.getLong(1);
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Account useAccount(Account account)
    { 
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM account WHERE username=? AND password=?;";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, account.username);
            statement.setString(2, account.password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                account.account_id = (int)result.getLong(1);
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
