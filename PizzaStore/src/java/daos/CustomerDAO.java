package daos;

import dtos.CustomerDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<CustomerDTO> getAllCustomers() {
        String sql = "SELECT * FROM Customers";
        long customerID;
        String password, contactName, address, phone;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<CustomerDTO> customers = new ArrayList<>();
            while (rs.next()) {
                customerID = rs.getLong(1);
                password = rs.getString(2);
                contactName = rs.getString(3);
                address = rs.getString(4);
                phone = rs.getString(5);
                CustomerDTO customerDTO = new CustomerDTO(customerID, password, contactName, address, phone);
                customers.add(customerDTO);
            }
            return customers;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerDTO getCustomerById(Long customerId) {
        String sql = "SELECT * FROM Customers WHERE customerId = ?";
        long customerID;
        String password, contactName, address, phone;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, customerId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                        customerID=rs.getLong(1);
                        password=rs.getString(2);
                        contactName=rs.getString(3);
                        address=rs.getString(4);
                        phone=rs.getString(5);
                        CustomerDTO customerDTO = new CustomerDTO(customerID, password, contactName, address, phone);
                return customerDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addCustomer(CustomerDTO customer) {
        String sql = "INSERT INTO Customers (password, contactName, address, phone) VALUES (?, ?, ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, customer.getPassword());
            preStm.setString(2, customer.getContactName());
            preStm.setString(3, customer.getAddress());
            preStm.setString(4, customer.getPhone());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(CustomerDTO customer) {
        String sql = "UPDATE Customers SET password = ?, contactName = ?, address = ?, phone = ? WHERE customerId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, customer.getPassword());
            preStm.setString(2, customer.getContactName());
            preStm.setString(3, customer.getAddress());
            preStm.setString(4, customer.getPhone());
            preStm.setLong(5, customer.getCustomerId());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(Long customerId) {
        String sql = "DELETE FROM Customers WHERE customerId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, customerId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CustomerDTO findOneByPhone(String phone) {
        String sql = "SELECT * FROM Customers WHERE phone = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, phone);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                        long customerId=rs.getLong(1);
                        String password=rs.getString(2);
                        String contactName=rs.getString(3);
                        String address=rs.getString(4);
                        String Phone=rs.getString(5);
                        CustomerDTO customerDTO = new CustomerDTO(customerId, password, contactName, address, Phone);
                return customerDTO;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        List<CustomerDTO> customers = customerDAO.getAllCustomers();
        for (CustomerDTO customer : customers) {
            System.out.println(customer);
        }

    }
}
