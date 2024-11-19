<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : create-pizza
    Created on : Feb 25, 2024, 7:20:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
        <style>

            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                /*                background-color: #fff;  Light beige color for a warm feel */
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #d84315; /* Dark red color for a pizza theme */
            }

            form {
                width: 50%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;

            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            table td:first-child {
                width: 30%;
            }

            input[type="text"],
            input[type="password"],
            select {
                width: calc(100% - 20px);
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                font-family: 'Roboto', sans-serif;
            }

            select {
                padding: 10px;
            }

            .btn {
                padding: 10px;
                background-color: #d84315; /* Dark red color for a pizza theme */
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn:hover {
                background-color: #bf360c; /* Darker shade of red for hover effect */
            }
        </style>

    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/><br/>

        <h1>Detail Pizza</h1>
        <th:set var="product" value="${requestScope.product}" />
        <br/><br/>

        <form>
            <table>

                <tbody>
                <input type="hidden" name="productId" value="${product.productId}" readonly/>
                <tr>
                    <td>Pizza Name</td>
                    <td><span>${product.productName}</span></td>
                </tr>

                <tr>
                    <td>Price</td>
                    <td><span>${product.unitPrice}</span></td>
                </tr>

                <tr>
                    <td>Quantity</td>
                    <td><span>${product.quantityPerUnit}</span></td>
                </tr>

                <tr>
                    <td>Category</td>
                    <td><span>${product.category.categoryName}</span></td>
                </tr>

                <tr>
                    <td>Supplier</td>
                    <td><span>${product.supplier.companyName}</span></td>
                </tr>

                <tr>
                    <td>Image Url</td>
                    <td><span>${product.productImage}</span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="./product-management" class="btn" style="text-decoration: none">Close</a></td>
                </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
