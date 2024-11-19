<%-- 
    Document   : category-management
    Created on : Jul 10, 2024, 8:08:27 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PizzaStore</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete product with id = " + id)) {
                    window.location = "./delete-product?categoryId=" + id;
                }
            }
        </script>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                padding-left: 40px; /* Adding padding to the left */
                padding-right: 40px; /* Adding padding to the right */
            }

            h2 {
                text-align: center;
                color: #e64a19; /* Dark orange color for a pizza theme */
            }

            a.button {
                background-color: green;
                border-radius: 5px;
                color: white;
                padding: 10px;
                text-decoration: none;
            }

            a.button:hover {
                background-color: darkgreen;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table th,
            table td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            table th {
                background-color: #f2f2f2;
                color: #333;
            }

            table tr:hover {
                background-color: #f9f9f9;
            }

            .actions a {
                text-decoration: none;
                color: #333;
                margin-right: 10px;
            }

            .actions a:hover {
                color: blue;
            }
        </style>

    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/>
        <h2>Category Management</h2>
        <br/>
        <div style="">
            <a style="background-color: blue; border-radius: 5px; color: white; padding: 10px 30px; text-decoration: none; margin-left: 10px  " href="CreateCategoryController" >+ Create new</a>
        </div>
        <br/>
        <br/>
    <th:if test="${message != null}">
        <p>${message}</p>
    </th:if>
    <th:if test="${category != null}">
        <br/>
        <table>
            <tr >
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Description</th>
             </tr>
            <c:forEach items="${category}" var="category">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${category.categoryId}</td>
                    <td style="padding: 5px 10px;">${category.categoryName}</td>
                    <td style="padding: 5px 10px;">${category.description}</td>
                    
                    

                    <td>
                        <a href="EditCategoryController?categoryId=${category.categoryId}">Edit | </a>
                        <a href="DeleteCategoryController?categoryId=${category.categoryId}">Delete </a>

                        
                    </td>
                </tr>
            </c:forEach>
        </table>
    </th:if>
    
</body>
</html>