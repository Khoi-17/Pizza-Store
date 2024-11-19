package daos;

import dtos.CustomerDTO;
import dtos.OrderDTO;
import dtos.SalesReportDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public List<OrderDTO> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<OrderDTO> orders = new ArrayList<>();
            while (rs.next()) {
                long orderId = rs.getLong(1);
                CustomerDTO customer = customerDAO.getCustomerById(rs.getLong(2));
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double freight = rs.getDouble(6);
                String shipAddress = rs.getString(7);
                OrderDTO order = new OrderDTO(orderId, customer, orderDate, requiredDate, shippedDate, freight, shipAddress);
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderDTO getOrderById(Long orderId) {
        String sql = "SELECT * FROM Orders WHERE orderId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, orderId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                long orderID = rs.getLong(1);
                CustomerDTO customer = customerDAO.getCustomerById(rs.getLong(2));
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double freight = rs.getDouble(6);
                String shipAddress = rs.getString(7);
                OrderDTO order = new OrderDTO(orderId, customer, orderDate, requiredDate, shippedDate, freight, shipAddress);
                return order;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addOrder(OrderDTO order) {
        String sql = "INSERT INTO Orders (customerId, orderDate, requiredDate, shippedDate, freight, shipAddress) VALUES (?, GETDATE(), GETDATE(), GETDATE(), ?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, order.getCustomer().getCustomerId());
            preStm.setString(2, order.getOrderDate());
            preStm.setString(3, order.getRequiredDate());
            preStm.setString(4, order.getShippedDate());
            preStm.setDouble(5, order.getFreight());
            preStm.setString(6, order.getShipAddress());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public OrderDTO getTheLastOrder() {
        String sql = "SELECT TOP 1 * FROM Orders ORDER BY orderId DESC";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                long orderId = rs.getLong(1);
                CustomerDTO customer = customerDAO.getCustomerById(rs.getLong(2));
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double freight = rs.getDouble(6);
                String shipAddress = rs.getString(7);
                OrderDTO order = new OrderDTO(orderId, customer, orderDate, requiredDate, shippedDate, freight, shipAddress);
                return order;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateOrder(OrderDTO order) {
        String sql = "UPDATE Orders SET customerId = ?, freight = ?, shipAddress = ? WHERE orderId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, order.getCustomer().getCustomerId());
            preStm.setDouble(2, order.getFreight());
            preStm.setString(3, order.getShipAddress());
            preStm.setLong(4, order.getOrderId());
            int result = preStm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(Long orderId) {
        String sql = "DELETE FROM Orders WHERE orderId = ?";
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

    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        String sql = "SELECT * FROM Orders WHERE customerId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, customerId);
            ResultSet rs = preStm.executeQuery();
            List<OrderDTO> orders = null;
            while (rs.next()) {
                long orderId = rs.getLong(1);
                CustomerDTO customer = customerDAO.getCustomerById(rs.getLong(2));
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double freight = rs.getDouble(6);
                String shipAddress = rs.getString(7);
                OrderDTO order = new OrderDTO(orderId, customer, orderDate, requiredDate, shippedDate, freight, shipAddress);
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDTO> getOrdersByDate(String date) {
        String sql = "SELECT * FROM Orders WHERE orderDate = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, date);
            ResultSet rs = preStm.executeQuery();
            List<OrderDTO> orders = null;
            while (rs.next()) {
                long orderId = rs.getLong(1);
                CustomerDTO customer = customerDAO.getCustomerById(rs.getLong(2));
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double freight = rs.getDouble(6);
                String shipAddress = rs.getString(7);
                OrderDTO order = new OrderDTO(orderId, customer, orderDate, requiredDate, shippedDate, freight, shipAddress);
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SalesReportDTO> getSalesReportByPeriod(String startDate, String endDate) {
        String sql = "SELECT Orders.orderId, Orders.orderDate, SUM(OrderDetails.unitPrice * OrderDetails.quantity) as totalSales "
                + "FROM Orders JOIN OrderDetails ON Orders.orderId = OrderDetails.orderId "
                + "WHERE Orders.orderDate BETWEEN ? AND ? "
                + "GROUP BY Orders.orderId, Orders.orderDate "
                + "ORDER BY totalSales DESC";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, startDate);
            preStm.setString(2, endDate);
            ResultSet rs = preStm.executeQuery();
            List<SalesReportDTO> salesReports = new ArrayList<>();
            while (rs.next()) {
                SalesReportDTO salesReport = new SalesReportDTO(
                        rs.getLong("orderId"),
                        rs.getString("orderDate"),
                        rs.getDouble("totalSales")
                );
                salesReports.add(salesReport);
            }
            return salesReports;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getAllOrders());
    }

}
