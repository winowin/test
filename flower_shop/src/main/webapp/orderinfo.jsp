<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="css/shop.css">
    <link rel="stylesheet" type="text/css" href="css/layui.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <style>
        .button {
            background-color: #f8c78b; /* Green */
            border: none;
            border-radius: 4px;
            color: white;
            padding: 12px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin: 4px 2px 4px 1px;
            cursor: pointer;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
        }
        .button2:hover {
            box-shadow: 0 10px 10px 0 rgba(0,0,0,0.24),0 10px 10px 0 rgba(0,0,0,0.19);
        }
        .order_right_form input[type="text"]{
            position: relative;
            top: 5px;

        }
        .order_left ul li{
            font-size: 15px;
        }
        .order_right,.order_left{
            border-radius:12px ;
        }
        .order_content_div{
            width: 100%;
        }
        .regTabs a{
            color: #f8c78b;
        }
        .regTabs a:hover{
            color: #f3dbbe;
        }
    </style>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"OrderServlet?action=allOrderInfo",
                success:function (respDate){
                    console.log(respDate)
                    $(respDate).each(function (){

                            $("#register_0").append(
                              "<div class='order_right_content' id='"+this.orderid+"' >"+
                              " <p>创建时间: "+this.createtime+" |  订单号： "+this.ordernum+" |在线支付|<i id='status"+this.orderid+"' style='color: #424242;'>&nbsp;&nbsp;&nbsp;</i> <span>订单金额：<b> "+this.totalprice+"</b> 元 </span></p>"+
                              "</div>"
                            );
                            var orderid = this.orderid;
                            $(this.items).each(function (){

                                $("#"+orderid).append(
                                    "<div class='order_right_shop'>"+
                                    "<img src='images/xbmg.jpg'>"+
                                    "<p>"+this.flowername+" <br>"+this.flowerprice+"元 X "+this.quantity+"</p>"+
                                    "<div class='order_right_div' id='pay"+this.orderid+"'></div>"+
                                    "<div class='clear'></div>"+
                                    "</div>"
                                );


                            })


                            if (this.status == 0){
                                $("#status"+orderid).append(
                                    "待支付"
                                );
                                $("#pay"+orderid).append(

                                    "<a href = 'OrderServlet?action=toFinishBuy&orderid="+this.orderid+"'><p>完成支付</p></a>"
                                );


                            }
                            if (this.status == 1){
                                $("#status"+orderid).append(
                                    "已支付"
                                );
                                $("#pay"+orderid).append(
                                    "<a href = 'OrderServlet?action=toOrderInfo&orderid="+this.orderid+"'><p>订单详情</p></a>"
                                );

                            }
                            if (this.status == 2){
                                $("#status"+orderid).append(
                                    "已收货"
                                );
                                $("#pay"+orderid).append(
                                    "<a href = 'OrderServlet?action=toOrderInfo&orderid="+this.orderid+"'><p>订单详情</p></a>"
                                );
                            }
                        }
                    )
                }
            });
        })
    </script>


</head>
<body>
<%@include file="header.jsp"%>
<!--   我的订单 -->
<div class="order_content">
    <div class="order">
        <p style="color: #333;font-size: 13px;">首页 > 我的</p>
        <div class="order_content_div">
            <div class="order_left">
                <ul>
                    <li class="box">订单中心</li>
                    <a href="${pageContext.request.contextPath}/orderinfo.jsp"><li class="min"  style="color:#f8c78b;">我的订单</li></a>

                    <li class="box">个人中心</li>
                    <a href="CustomerServlet?action=toCustomerInfo"><li class="min">我的信息</li></a>
                    <a href="${pageContext.request.contextPath}/manageAddress.jsp"><li class="min">收货地址</li></a>

                    <li class="box">账户管理</li>
                    <a href="${pageContext.request.contextPath}/updatePwd.jsp"><li class="min">修改密码</li></a>
                </ul>
            </div>
            <div class="order_right" >
                <div>

                </div>
                <p>我的订单<span style="font-size:13px;">&nbsp;&nbsp;  请谨防钓鱼链接或诈骗电话，了解更多></span></p>
                <div class="order_right_form">
                    <ul id="order_type">
                        <li id="regTabs_0" class="regTabs"><a href="${pageContext.request.contextPath}/orderinfo.jsp" >全部订单</a><span></span></li>

                        <li id="regTabs_1" ><a href="OrderServlet?action=toOrderTypeInfo&status=0" >待支付</a><span></span></li>
                        <li><a href="OrderServlet?action=toOrderTypeInfo&status=1">已支付</a><span></span></li>
<%--                        <li><a href="OrderServlet?action=toOrderTypeInfo&status=2">已收货</a></li>--%>
                    </ul>
<%--                    <form>--%>
<%--                        <input type="text" name="" placeholder="输入商品名称 订单号">--%>
<%--                        <div class="button button2">搜索</div>--%>
<%--                    </form>--%>
                    <div class="clear"></div>
                </div>
                <div class="order_right_show" id="register_0" style="display:block" >




                    <div class="clear"></div>
                </div>


            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="js/ordertype.js"></script>
</body>
</html>
