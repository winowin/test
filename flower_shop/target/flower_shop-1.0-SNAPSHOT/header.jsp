<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
<div class="topnav">
    <a href="ShoppingFlowerServlet?action=queryPageFlowers">首页</a>
    <a href="#" hidden="hidden">关于我们</a>
    <a href="#" hidden="hidden">花语</a>

    <c:if test="${empty sessionScope['org.example.session.login.flag']}">
        <a href="${pageContext.request.contextPath}/customer.jsp"> 登录 </a>
    </c:if>
    <c:if test="${!empty sessionScope['org.example.session.login.flag']}">
        <a href="${pageContext.request.contextPath}/orderinfo.jsp">您好，${sessionScope['org.example.session.login.flag'].username}</a>
        <a href="CustomerServlet?action=logout">安全登出</a>
    </c:if>

    <a href="ShoppingFlowerServlet?action=toShoppingCart" style="float:right">购物车</a>
</div>

<div class="header">

    <div class="search">
        <form action="ShoppingFlowerServlet?action=searchPageFlowers" method="post">
            <label>
                <input type="text" name="search" placeholder="商品搜索..">
            </label>
            <button type="submit" class="button button2" >搜索</button>
        </form>

    </div>
</div>


</body>
</html>
