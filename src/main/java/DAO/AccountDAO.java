package DAO;

import Model.Account;
import Util.ConnectionSingleton;
import java.sql.*;

public class AccountDAO {
    public static Account insertAccount(Account account) {
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "Insert INTO account (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Account userAccount(String username, String password) {
        Connection conn = ConnectionSingleton.getConnection();
        String sql = "SELECT * FROM account WHERE username=? AND password=?;";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_num"),
                        rs.getString("username"),
                        rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void deleteAccount(int account_num) {
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "DELETE FROM message WHERE account_num = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, account_num);

            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
