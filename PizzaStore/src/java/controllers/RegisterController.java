/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AccountDAO;
import dtos.AccountDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {

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
        String accountId = request.getParameter("accountId");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", "Password and Confirm Password are not matched");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        int type = 2; //regular user at beginning
        AccountDTO accountDTO = new AccountDTO(accountId, username, password, fullName, type);
        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.isAccountIdExist(accountId)) {
            request.setAttribute("message", "Account ID already exists. Please choose a different ID.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        boolean result = accountDAO.createAccount(accountDTO);
        if (result) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("message", "Error occurred, please try again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
        response.sendRedirect("register.jsp");
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
        processRequest(request, response);
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
