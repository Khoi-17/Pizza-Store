<%-- 
    Document   : edit-category
    Created on : Jul 10, 2024, 9:04:33 AM
    Author     : Admin
--%>

<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
       <style>
    body {
        font-family: 'Roboto', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #fff; /* Light beige color for a warm feel */
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

<h1>Edit Category</h1>
<br/><br/>

<form action="EditCategoryController" method="post">
    <table>
        <th:set var="category" value="${category}" />
        <tbody>
        <tr>
            <td>CategoryID</td>
            <td><input type="text" name="categoryId" value="${category.categoryId}" readonly/></td>
        </tr>
        <tr>
            <td>CategoryName</td>
            <td><input type="text" name="categoryname" value="${category.categoryName}" required/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="${category.description}" required /></td>
        </tr>
        
        <td></td>
        <td><input type="submit" value="Edit" class="btn" style="background-color: #c0392b" /></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
