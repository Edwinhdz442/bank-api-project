package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    public Account insertAccount(Account account) {
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Insert INTO account (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account userAccount(String username, String password) {
        Connection conn = ConnectionUtil.getConnection();
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

    public void deleteAccount(int account_num) {
        Connection conn = ConnectionUtil.getConnection();
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
