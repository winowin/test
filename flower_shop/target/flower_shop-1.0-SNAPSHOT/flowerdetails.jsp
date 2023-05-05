<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>美尼亚-鲜花详情页</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/reset.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

</head>
<body>
<!-- 头部信息 -->
<%@include file="header.jsp"%>
<%--<div class="carouselnav">--%>
<%--    <a href="#">链接</a>--%>
<%--    <a href="#">链接</a>--%>
<%--    <a href="#">链接</a>--%>
<%--    <a href="#" style="float:right">链接</a>--%>
<%--</div>--%>
<hr>
<div class="submena clearfix">
    <a href=" ">全部分类</a>
    <span>></span>
    <a href=" ">鲜花</a>
    <span>></span>
    <a href=" ">商品详情</a>
</div>
<div class="center_con clearfix">
    <div class="main_menu fl"><img src="images/xbmg.jpg" style="height: 350px;width: 350px;"></div>

    <div class="goods_detail_list fr">
        <h3>${f.flowername}</h3>
        <p>${f.description}</p>
        <div class="prize_bar">
            <div class="show_prize fl">￥<em>${f.price}</em></div>
            <div class="show_unit fl">单位：一捧</div>
            <c:if test="${f.stockstatus==true}">
                <div class="num_name fl" style="padding-left: 60px">商品剩余：${f.amount} <input id="amount" type="hidden" value="${f.amount}"></div>
            </c:if>
            <c:if test="${f.stockstatus==false}">
                <div class="num_name fl">商品已售罄 剩余：${f.amount} <input id="amount" type="hidden" value="${f.amount}"></div>
            </c:if>
        </div>
        <div class="goods_num clearfix">
            <div class="num_name fl">数量：</div>
            <div class="num_add fl">
                <input type="text" id="num" name="num" class="num_show fl" value="1">
                <a href="javascript:;" class="add fr">+</a>
                <a href="javascript:;" class="minus fr">-</a>
            </div>
        </div>
        <div class="total">总价：<em>${f.price}元</em></div>
        <div class="operate_btn">
            <a href="javascript:addFlowersToCart(${f.flowerid})"><input type="submit" class="buy_btn" value="立即购买"></a>
            <a href="javascript:addFlowersToCart(${f.flowerid})" class="add_cart">加入购物车</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<hr>
<script>
    function addFlowersToCart(flowerid) {
        var amount = $("#amount").val();

        console.log(amount)
        var num = $("#num").val();
        if (amount != 0){
            if (num>amount){
                alert("超出可购买数量,请重新选择");
                $.post(
                    "ShoppingFlowerServlet",
                    "action=flowersDetails&flowerid="+flowerid
                );
            }
            if (num<=amount){
                $.post(
                    "ShoppingFlowerServlet",
                    "action=addFlowersToCart&num="+num+"&flowerid="+flowerid,
                    function(respData) {
                        $("#msg").html(respData.message);
                    },"json",
                    location.href='ShoppingFlowerServlet?action=toShoppingCart'
                );
            }

        }
        if (amount == 0){
            alert("商品已售罄，请耐心等待补货");
            $.post(
                "ShoppingFlowerServlet",
                "action=flowersDetails&flowerid="+flowerid
            );
        }
    }
</script>
<script>
    var amounts = document.getElementsByClassName('num_show');


    //减少商品数量功能
    var leftbtns = document.getElementsByClassName('minus');
    for (var i = 0; i < leftbtns.length; i++) {
        leftbtns[i].index = i;
        leftbtns[i].addEventListener('click', function () {
            var amount = amounts[this.index].value;
            if (amount == 1) {
                return;
            } else {
                amount--;
            }
            amounts[this.index].value = amount;

        })
    }
    //增加商品数量功能
    var rightbtns = document.getElementsByClassName('add');
    for (var i = 0; i < rightbtns.length; i++) {
        rightbtns[i].index = i;
        rightbtns[i].addEventListener('click', function () {
            var amount = amounts[this.index].value;
            amount++;
            amounts[this.index].value = amount;

        })
    }
</script>
</body>
</html>
