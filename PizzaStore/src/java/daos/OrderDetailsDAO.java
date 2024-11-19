package daos;

import dtos.OrderDTO;
import dtos.OrderDetailsDTO;
import dtos.ProductDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO {

    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();

    public List<OrderDetailsDTO> getAllOrdersDetails() {
        String sql = "SELECT * FROM OrderDetails";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList = new ArrayList<>();
            while (rs.next()) {
                OrderDTO order = orderDAO.getOrderById(rs.getLong(1));
                ProductDTO product = productDAO.getProductById(rs.getLong(2));
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                OrderDetailsDTO orderDetail = new OrderDetailsDTO(order, product, unitPrice, quantity);
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderDetailsDTO getOrderDetailsById(Long orderId, Long productId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            preStm.setLong(2, productId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                OrderDTO order = orderDAO.getOrderById(rs.getLong(1));
                ProductDTO product = productDAO.getProductById(rs.getLong(2));
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                OrderDetailsDTO orderDetail = new OrderDetailsDTO(order, product, unitPrice, quantity);

                return orderDetail;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetailsDTO> getOrderDetailsByCustomerId(Long customerId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId IN (SELECT orderId FROM Orders WHERE customerId = ? )";

        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, customerId);
            ResultSet rs = preStm.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList = new ArrayList<>();
            while (rs.next()) {
                OrderDTO order = orderDAO.getOrderById(rs.getLong(1));
                ProductDTO product = productDAO.getProductById(rs.getLong(2));
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                OrderDetailsDTO orderDetail = new OrderDetailsDTO(order, product, unitPrice, quantity);
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
    public List<OrderDetailsDTO> getOrderDetailsByOrderId(Long orderId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            ResultSet rs = preStm.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList = new ArrayList<>();
            while (rs.next()) {
                OrderDTO order = orderDAO.getOrderById(rs.getLong(1));
                ProductDTO product = productDAO.getProductById(rs.getLong(2));
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                OrderDetailsDTO orderDetail = new OrderDetailsDTO(order, product, unitPrice, quantity);
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetailsDTO> getOrderDetailsByProductId(Long productId) {
        String sql = "SELECT * FROM OrderDetails WHERE productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, productId);
            ResultSet rs = preStm.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList = new ArrayList<>();
            while (rs.next()) {
                OrderDTO order = orderDAO.getOrderById(rs.getLong(1));
                ProductDTO product = productDAO.getProductById(rs.getLong(2));
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                OrderDetailsDTO orderDetail = new OrderDetailsDTO(order, product, unitPrice, quantity);
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addOrderDetails(OrderDetailsDTO orderDetails) {
        String sql = "INSERT INTO OrderDetails (orderId, productId, unitPrice, quantity) VALUES (?, ?, ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderDetails.getOrder().getOrderId());
            preStm.setLong(2, orderDetails.getProduct().getProductId());
            preStm.setDouble(3, orderDetails.getUnitPrice());
            preStm.setInt(4, orderDetails.getQuantity());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderDetails(OrderDetailsDTO orderDetails) {
        String sql = "UPDATE OrderDetails SET unitPrice = ?, quantity = ? WHERE orderId = ? AND productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setDouble(1, orderDetails.getUnitPrice());
            preStm.setInt(2, orderDetails.getQuantity());
            preStm.setLong(3, orderDetails.getOrder().getOrderId());
            preStm.setLong(4, orderDetails.getProduct().getProductId());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetails(Long orderId, Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            preStm.setLong(2, productId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByOrderId(Long orderId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByProductId(Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, productId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByOrderIdAndProductId(Long orderId, Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            preStm.setLong(2, productId);
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        List<OrderDetailsDTO> ordersDetailsList = orderDetailsDAO.getAllOrdersDetails();
        for (OrderDetailsDTO orderDetails : ordersDetailsList) {
            System.out.println(orderDetails);
        }
    }
}
