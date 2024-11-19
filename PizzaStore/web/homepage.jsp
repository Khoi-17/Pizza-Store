<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pizza Company</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff; /* Creamy color for a warm feel */
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .main-content {
            flex: 1;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }

        .banner-container {
            text-align: center;
            margin: 20px 0;
        }

        .banner-container img {
            width: 70%;
            height: auto;
        }

        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 0;
        }

        h1 {
            text-align: left;
            color: #e64a19; /* Dark orange color for a pizza theme */
        }

        .search-form {
            text-align: right;
        }

        input[type="text"],
        input[type="submit"] {
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-right: 10px;
        }

        input[type="text"] {
            width: 200px;
            border: 1px solid #333;
        }

        input[type="submit"] {
            background-color: #e64a19; /* Dark orange color for a pizza theme */
            color: white;
            cursor: pointer;
        }

        .pizza-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .pizza-item {
            border: 1px solid #777777;
            border-radius: 5px;
            padding: 10px;
            width: calc(33.333% - 20px);
            box-sizing: border-box;
        }

        .pizza-item img {
            width: 100%;
            height: auto;
            display: block;
        }

        .pizza-item h3, .pizza-item p, .pizza-item form {
            margin: 10px 0;
        }

        .pizza-item form input[type="submit"] {
            color: white;
            background: orange;
            padding: 10px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        footer {
            text-align: center;
            padding: 20px;
            background-color: #e64a19; /* Dark orange color for footer */
            color: white;
        }
    </style>
</head>
<body>
    <div class="main-content">
        <%@include file="header-user.jsp" %>
        <div class="banner-container">
            <img src="https://d1csarkz8obe9u.cloudfront.net/posterpreviews/pizza-banner-design-template-6beca5c58902ff2791ecfc9f577a7bc1_screen.jpg?ts=1627100096" alt="Pizza Banner" />
        </div>
        <div class="container">
            <div class="header-container">
                <h1>Menu Pizza</h1>
                <form class="search-form" method="get" action="./home">
                    <input type="text" name="search" value="${param.search}" placeholder="Find pizza">
                    <input type="submit" value="Go">
                </form>
            </div>
            <th:if test="${message != null}">
                <p>${message}</p>
            </th:if>
            <th:if test="${pizzas != null}">
                <br/>
                <div class="pizza-container">
                    <c:forEach items="${pizzas}" var="pizza">
                        <div class="pizza-item">
                            <h3>${pizza.productName}</h3>
                            <p>Price: $${pizza.unitPrice}</p>
                            <p>Description: ${pizza.category.description}</p>
                            <img src="${pizza.productImage}" alt="Image" />
                            <p>Category: ${pizza.category.categoryName}</p>
                            <form method="get" action="./order">
                                <input type="hidden" name="productId" value="${pizza.productId}">
                                <input type="hidden" name="productName" value="${pizza.productName}">
                                <input type="hidden" name="unitPrice" value="${pizza.unitPrice}">
                                <input type="hidden" name="quantity" value="1">
                                <input type="submit" value="Place Order">
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </th:if>
            <br/>
        </div>
    </div>
    <footer>
        <p>© 2024 Pizza Company. All rights reserved.</p>
    </footer>
</body>
</html>
