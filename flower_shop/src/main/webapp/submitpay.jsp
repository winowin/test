<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/pay.css">
    <script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<!-- 主体内容 -->


<div class="close_content">

    <%  //产生一个唯一的令牌
        String token = UUID.randomUUID().toString();
        session.setAttribute("token",token);
    %>


        <div class="close_contenter">
            <h1>订单详情:待付款</h1>
            <div class="orderinfo">

                <div class="close_add1">
                    <div class="circle"><img src="images/yes_ok.png" alt=""></div>

                    <h4>您的订单已提交成功！</h4>
                </div>

                <div class="close_add">

                        <h1>成功付款后，7天内发货</h1>
                        <h6>金额：${order.totalprice}元</h6>
                        <h6>订单号：${order.ordernum}</h6>
                        <h6>配送信息：${order.username} / ${order.phonenumber}  / ${order.address} / 送达时间:${order.datetime} / 个人电子发票</h6>
                        <h6>订单创建时间：${order.createdate}</h6>

                </div>

                <div class="clear"></div>
            </div>

            <h2>支付方式 </h2>
            <form method="post" action="OrderServlet?action=finishBuy&orderid=${order.orderid}">

            <div class="paym">

                <ul>
                    <li class="list">
                        <p  title="支付宝">
                            <input id="method1" type="hidden" name="paymethod" value="1">
                            <img src="images/zfb.png" alt="支付宝"></p>
                    </li>
                    <li>
                        <p  class="p2"  title="微信支付">
                            <input id="method2" type="hidden" name="" value="2">
                            <img src="images/payment_wx.png" alt="微信"></p>
                    </li>
                    <li>
                        <p title="银联支付">
                            <input id="method3" type="hidden" name="" value="3">
                            <img src="images/zxzf.png" alt="银联"></p>
                    </li>

                </ul>

            </div>
            <div class="close_clear">
                <input type="hidden" name="token" value="<%=token%>">
                <button type="submit"  class="submit">立即支付</button>
            </div>
            </form>

        </div>




</div>
<%@include file="footer.jsp"%>
<script src="js/pay.js"></script>
</body>
</html>
