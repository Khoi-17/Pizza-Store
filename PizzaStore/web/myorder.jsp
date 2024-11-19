

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order</title>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #fff; /* Creamy color for a warm feel */
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #e64a19; /* Dark orange color for a pizza theme */
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
                background-color: #004666; 
                color: white;
            }

            table tr:hover {
                background-color: #f2f2f2; /* Lighter shade of orange on hover */
            }

            .error-message {
                color: red;
                text-align: center;
            }

        </style>

    </head>
    <body>
        <%@include file="header-user.jsp" %>

        <br/>
        <br/>
        <div style="text-align: center">
            <image src="https://img.dominos.vn/BANNER+LTO+CUA+NEW+(3).jpg" style="width: 80%"/>

        </div>
        <br/>
    <th:if test="${message != null}">
        <p>${message}</p>
    </th:if>
    <th:if test="${orderDetails != null}">
        <br/>
        <table>
            <tr >
                <th>ID</th>
                <th>Contact Name</th>
                <th>Ship Address</th>
                <th>Phone</th>
                <th>Product</th>
                <th>Category</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Order Date</th>

            </tr>
            <c:forEach items="${orderDetails}" var="orderDetail">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${orderDetail.order.orderId}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.contactName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.address}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.phone}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.productName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.category.categoryName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.unitPrice}</td>
                    <td style="padding: 5px 10px;">${orderDetail.quantity}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.orderDate}</td>

                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>       <br/>
        <br/>       <br/>
        <br/>       <br/>
        <br/>
    </th:if>
</body>
</html>
