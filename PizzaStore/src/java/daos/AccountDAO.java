package daos;

import dtos.AccountDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public List<AccountDTO> getAllAccounts() {
        String sql = "SELECT * FROM Account";
        String accountID, userName, password, fullName;
        int Type;
        long customerId;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                accountID = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                Type = rs.getInt(5);
                customerId = rs.getLong(6);
                AccountDTO accountDTO = new AccountDTO(accountID, userName, password, fullName, Type, customerId);
                accounts.add(accountDTO);
            }
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountById(String accountId) {
        String sql = "SELECT * FROM Account WHERE accountId = ?";
        String accountID, userName, password, fullName;
        int Type;
        long customerId;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, accountId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                accountID = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                Type = rs.getInt(5);
                customerId = rs.getLong(6);
                AccountDTO accountDTO = new AccountDTO(accountID, userName, password, fullName, Type, customerId);
                return accountDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        String accountID, userName, password, fullName;
        int Type;
        long customerId;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, username);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                accountID = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                Type = rs.getInt(5);
                customerId = rs.getLong(6);
                AccountDTO accountDTO = new AccountDTO(accountID, userName, password, fullName, Type, customerId);
                return accountDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createAccount(AccountDTO account) {
        String sql = "INSERT INTO Account(accountId, username, password, fullName, type) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, account.getAccountId());
            preStm.setString(2, account.getUsername());
            preStm.setString(3, account.getPassword());
            preStm.setString(4, account.getFullName());
            preStm.setInt(5, account.getType());
            int rowsInserted = preStm.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(AccountDTO account) {
        String sql = "UPDATE Account SET username = ?, password = ?, fullName = ?, type = ? WHERE accountId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, account.getUsername());
            preStm.setString(2, account.getPassword());
            preStm.setString(3, account.getFullName());
            preStm.setInt(4, account.getType());
            preStm.setString(5, account.getAccountId());
            int rowsUpdated = preStm.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(String accountId) {
        String sql = "DELETE FROM Account WHERE accountId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, accountId);
            int rowsDeleted = preStm.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AccountDTO login(String accountId, String passWord) {
        String sql = "SELECT * FROM Account WHERE accountId = ? AND password = ?";
        String accountID, userName, password, fullName;
        int Type;
        long customerId;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, accountId);
            preStm.setString(2, passWord);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                accountID = rs.getString(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                Type = rs.getInt(5);
                customerId = rs.getLong(6);
                AccountDTO accountDTO = new AccountDTO(accountID, userName, password, fullName, Type, customerId);
                return accountDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountByCustomerId(Long customerId) {
        String sql = "SELECT * FROM Account WHERE customerId = ?";
        String accountID, userName, password, fullName;
        int Type;

        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, customerId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                accountID = rs.getString(1);
                userName = rs.getString(1);
                password = rs.getString(2);
                fullName = rs.getString(3);
                Type = rs.getInt(4);
                AccountDTO accountDTO = new AccountDTO(accountID, userName, password, fullName, Type, customerId);
                return accountDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean isAccountIdExist(String accountId) {
        boolean exist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            String sql = "SELECT 1 FROM Account WHERE AccountID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        } finally {
//            DBUtils.close(con, ps, rs);
//        }
        return exist;
    }

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        List<AccountDTO> accounts = accountDAO.getAllAccounts();
        for (AccountDTO account : accounts) {
            System.out.println(account);
        }
    }
}
