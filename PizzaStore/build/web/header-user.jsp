<%-- 
    Document   : header
    Created on : Jun 11, 2023, 4:25:09 PM
    Author     : Nguyen Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>JSP Page</title>
        <link rel="stylesheet" href="./asset/styles.css">
    
    </head>
    <body>
        <div id="header">
          
            <nav>
                <ul id="main-menu">
                    <li>
                        <a href="./home"><image src="https://i.pinimg.com/736x/a1/c4/04/a1c404bea49c0143058bc4dd9da79d33.jpg"style="width: 100px; height: auto;"/></a>
                    </li>
                  
                    <li>
                        <a href="./my-order?accountId=${sessionScope.account.customerId}">My Orders</a>
                    </li>
                      <li>
                        <a href="./home">Pizzas List</a>
                    </li>

                </ul>

                <div class="header_login">
                    <c:if test="${sessionScope.account == null}">
                        
                        <a href="register.jsp">Register</a>
                        /
                        <a href="login.jsp">Login</a>
                    </c:if>
                        <c:if test="${sessionScope.account != null}">
                            Welcome, <a href="account"> ${sessionScope.account.username}</a>
                            /
                            <a href="./logout">Logout</a>
                            
                        </c:if>
                </div>
            </nav>
        </div>
        
        <script>
        $(document).ready(function () {
            $('#toggle').click(function () {
                $('nav').slideToggle();
            })
        })
        </script>
    </body>
</html>
