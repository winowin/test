<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/cart.css">
    <script src="js/jquery-3.6.0.min.js" type="text/javascript"></script>
</head>
<body>
<!-- 头部信息 -->
<div class="topnav">
    <a href="ShoppingFlowerServlet?action=queryPageFlowers">首页</a>
    <a href="#">关于我们</a>
    <a href="#">花语</a>

    <c:if test="${empty sessionScope['org.example.session.login.flag']}">
        <a href="${pageContext.request.contextPath}/customer.jsp"> 登录 </a>
    </c:if>
    <c:if test="${!empty sessionScope['org.example.session.login.flag']}">
        <a href="${pageContext.request.contextPath}/orderinfo.jsp">您好，${sessionScope['org.example.session.login.flag'].username}</a>
        <a href="CustomerServlet?action=logout">安全登出</a>
    </c:if>

    <a href="ShoppingFlowerServlet?action=toShoppingCart" style="float:right">购物车</a>
</div>
<%--购物车部分--%>
<c:if test="${empty sessionScope.cart.map}">
    <hr>
    <div  style="background: url(images/emptycart.jpg);background-repeat:no-repeat;height: 300px;text-align: center;">
        <div style="padding-top: 200px">
            <h3>您还未购买任何商品</h3>
            <a href="${pageContext.request.contextPath}/ShoppingFlowerServlet?action=queryPageFlowers">
                <button type="submit" class="ebutton b2" >
                    前往购物
                </button>
            </a>
        </div>
    </div>

</c:if>
<c:if test="${!empty sessionScope.cart.map}">
    <%  //产生一个唯一的令牌
        String token = UUID.randomUUID().toString();
        session.setAttribute("token",token);
    %>
    <hr>
    <form action="OrderServlet?action=orderDetails" method="post">
    <div style="background: #f5f5f5; padding-bottom: 10px;">
        <div id="box">
            <h2 class="box_head">
                <span>热门好物 快来看看</span>
                <a href="${pageContext.request.contextPath}/ShoppingFlowerServlet?action=queryPageFlowers">
                    <button type="button" class="button b1" >
                        继续购物
                    </button>
                </a>
            </h2>

        </div>



        <div id="car" class="car">

            <div class="head_row hid">
                <div class="check left"> <input type="checkbox" class="checkAll" style="margin-top: 25px"> </div>
                <div class="img left"> &nbsp;</div>
                <div class="name left">商品名称</div>
                <div class="price left">单价</div>
                <div class="number left">数量</div>
                <div class="subtotal left">小计</div>
                <div class="ctrl left">操作</div>
            </div>
            <c:forEach items="${sessionScope.cart.map}" var="me">
                <div class="row hid" >

                    <div class="check left">
                        <input type='checkbox' class='checkItem'/>
                    </div>
                    <div class="img left"><img src="images/3.jpg" width="80" height="80"></div>

                    <div class="name left" id="fname"><span>  ${me.value.flowers.flowername} </span></div>

                    <div class="price left" id="fprice"><span class='perPrice'>${me.value.flowers.price}</span>元</div>

                    <div class="item_count_i">
                        <div class="num_count">
                            <div class="count_d">-</div>
                            <div class="c_num"><input type="hidden" id="amount" value="${me.value.flowers.amount}"> <input type="text" id="num" class="amount" value="${me.value.num}" style="padding: 0;margin-top: 12px;" ></div>
                            <div class="count_i">+</div>
                        </div>
                    </div>
                    <div class="subtotal left"><span  class='smallPrice'>${me.value.flowers.price*me.value.num}</span>元</div>
                    <div class="ctrl left"><a href="javascript:deleteCart(${me.value.flowers.flowerid});" class='delete' >×</a></div>
                </div>
            </c:forEach>


        </div>
        <div id="sum_area">

            <div id="pay" style="width: 150px;margin-right: 84px">
                <input type="hidden" name="token" value="<%=token%>">
                <input type="submit" name="pay" onclick="compaerNum()" value="去结算">
            </div>
            <div id="pay_amout">
                共&nbsp;<span class="totalAmount" id="price_num">${sessionScope.cart.totalNum}</span>&nbsp;件商品
                &nbsp;&nbsp; 合计：<span class="total" id="price_num1" >${sessionScope.cart.totalPrice}</span>元
            </div>
        </div>

    </div>
    </form>
</c:if>

<%@include file="footer.jsp"%>
<script>
    function compaerNum(){
        var amount = $('#amont').val();
        var num = $('num').val();
        if (amount<num){
            alert("超出可购买数量！请重新选择")
            $.post(
                location.href='ShoppingFlowerServlet?action=toShoppingCart'
            );
        }
    }
</script>

<script>
    function deleteCart(flowerid) {
        if(confirm("确认删除?")){
            $.post(
                "ShoppingFlowerServlet",
                "action=removeFlowers&flowerid="+flowerid,
                location.href='ShoppingFlowerServlet?action=toShoppingCart'
            );
        }

    }


</script>
</body>
</html>
