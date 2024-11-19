package daos;

import dtos.CategoryDTO;
import dtos.ProductDTO;
import dtos.SupplierDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public List<ProductDTO> getAllProducts() {
        String sql = "SELECT * FROM Products ORDER BY productId DESC";  // DESC: descending order
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (rs.next()) {
                long productId = rs.getLong(1);
                String productName = rs.getString(2);
                SupplierDTO supplier = supplierDAO.getSupplierById(rs.getLong(3));
                CategoryDTO category = categoryDAO.getCategoryById(rs.getLong(4));
                int quantityPerUnit = rs.getInt(5);
                double unitPrice = rs.getDouble(6);
                String productImage = rs.getString(7);
                ProductDTO product = new ProductDTO(productId, productName, category, supplier, quantityPerUnit, unitPrice, productImage);
                products.add(product);
            }
            return products;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductDTO getProductById(Long productId) {
        String sql = "SELECT * FROM Products WHERE productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, productId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                long productID = rs.getLong(1);
                String productName = rs.getString(2);
                SupplierDTO supplier = supplierDAO.getSupplierById(rs.getLong(3));
                CategoryDTO category = categoryDAO.getCategoryById(rs.getLong(4));
                int quantityPerUnit = rs.getInt(5);
                double unitPrice = rs.getDouble(6);
                String productImage = rs.getString(7);
                ProductDTO product = new ProductDTO(productId, productName, category, supplier, quantityPerUnit, unitPrice, productImage);
                return product;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addProduct(ProductDTO product) {
        String sql = "INSERT INTO Products (productName, supplierId, categoryId, quantityPerUnit, unitPrice, productImage) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, product.getProductName());
            preStm.setLong(2, product.getSupplier().getSupplierId());
            preStm.setLong(3, product.getCategory().getCategoryId());
            preStm.setInt(4, product.getQuantityPerUnit());
            preStm.setDouble(5, product.getUnitPrice());
            preStm.setString(6, product.getProductImage());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(ProductDTO product) {
        String sql = "UPDATE Products SET productName = ?, supplierId = ?, categoryId = ?, quantityPerUnit = ?, unitPrice = ?, productImage = ? WHERE productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, product.getProductName());
            preStm.setLong(2, product.getSupplier().getSupplierId());
            preStm.setLong(3, product.getCategory().getCategoryId());
            preStm.setInt(4, product.getQuantityPerUnit());
            preStm.setDouble(5, product.getUnitPrice());
            preStm.setString(6, product.getProductImage());
            preStm.setLong(7, product.getProductId());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(Long productId) {
        String sql = "DELETE FROM Products WHERE productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, productId);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductDTO> getProductByProductName(String productName) {
        String sql = "SELECT * FROM Products WHERE productName LIKE ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + productName + "%");
            ResultSet rs = preStm.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (rs.next()) {
                long productId = rs.getLong(1);
                String productname = rs.getString(2);
                SupplierDTO supplier = supplierDAO.getSupplierById(rs.getLong(3));
                CategoryDTO category = categoryDAO.getCategoryById(rs.getLong(4));
                int quantityPerUnit = rs.getInt(5);
                double unitPrice = rs.getDouble(6);
                String productImage = rs.getString(7);
                ProductDTO product = new ProductDTO(productId, productName, category, supplier, quantityPerUnit, unitPrice, productImage);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<ProductDTO> searchProductByIdOrNameOrUnitPrice(String keyword) {
        String sql = "SELECT * FROM Products WHERE productId LIKE ? OR productName LIKE ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + keyword + "%");
            preStm.setString(2, "%" + keyword + "%");
            ResultSet rs = preStm.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (rs.next()) {
                long productId = rs.getLong(1);
                String productName = rs.getString(2);
                SupplierDTO supplier = supplierDAO.getSupplierById(rs.getLong(3));
                CategoryDTO category = categoryDAO.getCategoryById(rs.getLong(4));
                int quantityPerUnit = rs.getInt(5);
                double unitPrice = rs.getDouble(6);
                String productImage = rs.getString(7);
                ProductDTO product = new ProductDTO(productId, productName, category, supplier, quantityPerUnit, unitPrice, productImage);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

    }
}
