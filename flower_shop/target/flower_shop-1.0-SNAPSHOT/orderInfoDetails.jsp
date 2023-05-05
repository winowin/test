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
    <style>
        .close_contenter .close_add2 {
            border: 1px solid #E0E0E0;
            width: 1032px;

            float: left;
            padding: 10px 20px;
            position: relative;
            margin-right: 10px;
        }

        .order_right_content {
            width: 100%;
            border: none;
            margin-top: 30px;
        }

        .order_right_content h2 {
            width: 120px;
            margin: 20px 30px 0px 30px;
            color: #C1C1C1;
        }

        .order_right_shop {
            margin: 20px 20px;
            width: 100%;
            height: 100px;
            line-height: 100px;
        }

        .order_right_shop img {
            float: left;width: 90px;height: 90px;


        }
    </style>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"OrderServlet?action=orderInfo",
                success:function (respDate){
                    console.log(respDate)

                    $("#close_add").append(
                        "<h1>成功付款后，7天内发货</h1>"+
                        "<h6>金额："+respDate.totalprice+"元</h6>"+
                        "<h6>订单号："+respDate.ordernum+"</h6>"+
                        "<h6>配送信息： "+respDate.username+" / "+respDate.phonenumber+"   /  "+respDate.address+" / 送达时间: "+respDate.datetime+" / 个人电子发票</h6>"+
                        "<h6>订单创建时间："+respDate.createdate+"</h6>"
                    );

                    var orderid = respDate.orderid;
                    $("#ors").append(
                        "<img src='images/xbmg.jpg' style='float: left;width: 90px;height: 90px;'>"+
                        "<div id='os'></div>"
                    );
                    $(respDate.items).each(function (){

                        $("#os").append(
                            "<p>"+this.flowername+" <br>"+this.flowerprice+"元 X "+this.quantity+"</p>"
                        );


                    })

                }
            });
        })
    </script>
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
        <h1>订单详情:已支付</h1>
        <div class="orderinfo">

            <div class="close_add1">
                <div class="circle"><img src="images/yes_ok.png" alt=""></div>

                <h4>您已成功支付！</h4>
            </div>

            <div class="close_add" id="close_add">

            </div>
            <div class="close_add2" >
                <div class="order_right_content">
                    <h2>已支付</h2>
                    <div class="order_right_shop">
                        <div id="ors"></div>

                        <div class="order_right_div">

                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="clear"></div>

            <div class="close_clear">
                <a href="${pageContext.request.contextPath}/orderinfo.jsp"><button type="submit"  class="submit">返回</button></a>
            </div>
    </div>



</div>
<%@include file="footer.jsp"%>
<script src="js/pay.js"></script>
</body>
</html>
