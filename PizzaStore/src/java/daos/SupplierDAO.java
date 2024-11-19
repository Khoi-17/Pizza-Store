package daos;

import dtos.SupplierDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public List<SupplierDTO> getAllSuppliers() {
        String sql = "SELECT * FROM Suppliers";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<SupplierDTO> suppliers = new ArrayList<>();
            while (rs.next()) {
                long supplierId = rs.getLong(1);
                String companyName = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                SupplierDTO supplier = new SupplierDTO(supplierId, companyName, address, phone);
                suppliers.add(supplier);
            }
            return suppliers;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SupplierDTO getSupplierById(Long supplierId) {
        String sql = "SELECT * FROM Suppliers WHERE supplierId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, supplierId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                long supplierID = rs.getLong(1);
                String companyName = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                SupplierDTO supplier = new SupplierDTO(supplierID, companyName, address, phone);
                return supplier;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addSupplier(SupplierDTO supplier) {
        String sql = "INSERT INTO Suppliers (companyName, address, phone) VALUES (?, ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, supplier.getCompanyName());
            preStm.setString(2, supplier.getAddress());
            preStm.setString(3, supplier.getPhone());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(SupplierDTO supplier) {
        String sql = "UPDATE Suppliers SET companyName = ?, address = ?, phone = ? WHERE supplierId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, supplier.getCompanyName());
            preStm.setString(2, supplier.getAddress());
            preStm.setString(3, supplier.getPhone());
            preStm.setLong(4, supplier.getSupplierId());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(Long supplierId) {
        String sql = "DELETE FROM Suppliers WHERE supplierId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, supplierId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SupplierDTO> searchSuppliers(String search) {
        String sql = "SELECT * FROM Suppliers WHERE companyName LIKE ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            ResultSet rs = preStm.executeQuery();
            List<SupplierDTO> suppliers = new ArrayList<>();
            while (rs.next()) {
                long supplierId = rs.getLong(1);
                String companyName = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                SupplierDTO supplier = new SupplierDTO(supplierId, companyName, address, phone);
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isSupplierExists(Long supplierId) {
        String sql = "SELECT * FROM Suppliers WHERE supplierId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, supplierId);
            ResultSet rs = preStm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SupplierDAO supplierDAO = new SupplierDAO();
        List<SupplierDTO> suppliers = supplierDAO.getAllSuppliers();
        for (SupplierDTO supplier : suppliers) {
            System.out.println(supplier);
        }
    }
}
