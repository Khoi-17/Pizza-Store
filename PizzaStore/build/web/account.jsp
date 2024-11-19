<set<%@ page import="dtos.AccountDTO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
        <style>
            body {
                font-family: Arial, sans-serif; /* Changed font to Arial */
                margin: 0;
                padding: 0;
                background-color: #f2f2f2; /* Changed background color to a light gray */
            }

            .container {
                width: 90%; /* Increased container width */
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333; /* Changed heading color to a darker shade */
            }

            table {
                width: 70%; /* Increased table width */
                margin: 0 auto;
                border-collapse: separate; /* Changed border-collapse to separate */
                border-spacing: 0; /* Added border-spacing */
            }

            table td {
                padding: 12px; /* Increased cell padding */
                border-bottom: 1px solid #ddd;
            }

            table td:first-child {
                width: 35%; /* Increased width of first column */
            }

            input[type="text"],
            input[type="submit"] {
                width: calc(100% - 24px); /* Adjusted width calculation */
                padding: 12px; /* Increased input padding */
                border: 1px solid #ccc;
                border-radius: 6px; /* Increased border radius */
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #007bff; /* Changed submit button color to blue */
                color: #fff;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #0056b3; /* Darker blue color for hover effect */
            }

            input[type="text"][readonly] {
                background-color: #f7f7f7;
                cursor: not-allowed;
            }

            a {
                display: inline-block;
                margin-top: 24px; /* Increased margin top */
                text-align: center;
                color: #007bff; /* Changed link color to blue */
                text-decoration: none;
            }

            a:hover {
                color: #0056b3; /* Darker blue color for hover effect */
            }
        </style>
    </head>
    <body>
        <%
            if (session.getAttribute("account") == null) {
                response.sendRedirect("login");
            } else {
                AccountDTO account = (AccountDTO) session.getAttribute("account");
                if (account.getType() == 1) {
        %>
        <jsp:include page="header.jsp" />
        <%
        } else if (account.getType() == 2) {
        %>
        <jsp:include page="header-user.jsp" />
        <%
                }
            }
        %>

        <br/>
        <h1>Account</h1>
        <br/>
        <br/>
        <%--        <th:if test="${message != null}">--%>
        <%--            <p>${message}</p>--%>
        <%--        </th:if>--%>
        <%--        <th:if test="${orderDetails != null}">--%>
        <%--            <p>${orderDetails.size()} orders found</p>--%>
        <br/>
        <form action="./edit-member" method="post">
            <table>
                <th:set var="account" value="${sessionScope.account}" />
                <tbody>
                    <tr>
                        <td>Account ID</td>
                        <td style="padding: 5px 10px;"><input type="text" name="accountId" value="${account.accountId}" readonly/></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td style="padding: 5px 10px;"><input type="text" name="username" value="${account.username}" required/></td>
                    </tr>

                <td ><input type="hidden" name="password" value="${account.password}" required /></td>
                <tr>
                    <td>Full Name</td>
                    <td style="padding: 5px 10px;"><input type="text" name="fullName" value="${account.fullName}" required /></td>
                </tr>
                <input type="hidden" name="type" value="${account.type}" required />


                <td></td>
                <td><input type="submit" value="Save" style="color: white; background: #c0392b; padding: 5px; border-radius: 5px; cursor: pointer; padding: 10px"/></td>
                </tr>
                </tbody>
            </table>
        </form>

        <br/>
        <br/>

    </body>
</html>
