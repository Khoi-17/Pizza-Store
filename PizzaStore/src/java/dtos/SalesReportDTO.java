package dtos;


/**
 *
 * @author Admin
 */


public class SalesReportDTO {
    private Long orderId;
    private String orderDate;
    private Double totalSales;

    public SalesReportDTO() {
    }

    public SalesReportDTO(Long orderId, String orderDate, Double totalSales) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalSales = totalSales;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    
}