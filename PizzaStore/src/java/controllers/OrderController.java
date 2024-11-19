/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.*;
import dtos.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductById(Long.parseLong(productId));
        request.setAttribute("product", product);
        request.getRequestDispatcher("orderpage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy các tham số từ yêu cầu
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String contactName = request.getParameter("contactName");

        // Tạo các đối tượng DAO
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        // Lấy thông tin sản phẩm dựa trên productId
        ProductDTO product = productDAO.getProductById(Long.parseLong(productId));
        if (product == null) {
            request.setAttribute("messageOrder", "Product not found!");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }

        // Lấy đối tượng AccountDTO từ session
        AccountDTO loggedInAccount = (AccountDTO) request.getSession().getAttribute("account");
        if (loggedInAccount == null) {
            request.setAttribute("messageOrder", "You must be logged in to place an order!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Kiểm tra nếu khách hàng đã tồn tại
        CustomerDTO customer = customerDAO.findOneByPhone(phone);
        if (customer == null) {
            // Tạo đối tượng CustomerDTO mới với thông tin từ form
            customer = new CustomerDTO();
            customer.setContactName(contactName);
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setPassword(loggedInAccount.getPassword());

            // Lưu CustomerDTO mới vào cơ sở dữ liệu
            customerDAO.addCustomer(customer);
            customer = customerDAO.findOneByPhone(phone);
        }

        // Cập nhật customerId trong AccountDTO và lưu vào cơ sở dữ liệu
        loggedInAccount.setCustomerId(customer.getCustomerId());
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.updateAccount(loggedInAccount);

        // Tạo đối tượng OrderDTO mới với thông tin khách hàng và địa chỉ giao hàng
        OrderDTO order = new OrderDTO();
        order.setCustomer(customer);
        order.setFreight(300.00);
        order.setShipAddress(address);

        // Lưu OrderDTO mới vào cơ sở dữ liệu
        orderDAO.addOrder(order);
        order = orderDAO.getTheLastOrder();

        // Tạo đối tượng OrderDetailsDTO mới với thông tin đơn hàng, sản phẩm, đơn giá và số lượng
        OrderDetailsDTO orderDetails = new OrderDetailsDTO();
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setUnitPrice(product.getUnitPrice());
        orderDetails.setQuantity(Integer.parseInt(quantity));

        // Lưu OrderDetailsDTO mới vào cơ sở dữ liệu
        orderDetailsDAO.addOrderDetails(orderDetails);

        // Thiết lập thông báo và chuyển hướng về trang home với thông báo thành công
        request.setAttribute("messageOrder", "Order successfully!");
        response.sendRedirect("./home");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
