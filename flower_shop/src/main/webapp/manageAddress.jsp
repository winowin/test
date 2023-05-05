<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/shop.css">
    <style>
        .close_add1 a {
            font-size: 60px;
            cursor: pointer;
            display: block;
            width: 36px;
            height: 36px;
            margin: 30px auto;
            line-height: 36px;
        }

        .close_add1 a:hover {
            color: #FFF;
            background: #f8c78b;
            border-radius: 50%;
        }
        .close_add,.close_add1{
            cursor: pointer;
        }

        .close_contenter{
            width: 900px;
            padding: 20px;
        }
        .order_right,.order_left{
            border-radius:12px ;
        }

    </style>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"OrderServlet?action=showAddress",
                success:function (respDate){
                    $(respDate).each(function (){
                            $("#close_add").append(
                                "<div class='close_add' id="+'def'+this.addressid+">"+
                                "<h1 >"+this.username+"</h1>" +
                                "<h6 >"+this.phonenumber+"</h6>" +
                                "<h6 >"+this.province+ '省' +this.city+ '市' +this.county+"</h6>" +
                                "<h6 >"+this.address+"</h6>" +
                                "<a href='CustomerServlet?action=toUpdateAddress&addressid="+this.addressid+"'><span  class='del' style='margin-right:100px ;'>修改</span></a>" +
                                "<a href='CustomerServlet?action=toDeleteAddress&addressid="+this.addressid+"'><span class='del'>删除</span></a>"+
                                "</div>"

                            );
                        }
                    )
                }
            });
        })
    </script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="order_content">
    <div class="order">
        <p style="color: #333;font-size: 13px;">首页 > 我的</p>
        <div class="order_content_div">
            <div class="order_left">
                <ul class="">
                    <li class="box">订单中心</li>
                    <a href="${pageContext.request.contextPath}/orderinfo.jsp"><li class="min"  >我的订单</li></a>

                    <li class="box">个人中心</li>
                    <a href="CustomerServlet?action=toCustomerInfo"><li class="min" s>我的信息</li></a>
                    <a href="${pageContext.request.contextPath}/manageAddress.jsp"><li class="min" style="color:#f8c78b;">收货地址</li></a>

                    <li class="box">账户管理</li>
                    <a href="${pageContext.request.contextPath}/updatePwd.jsp"><li class="min">修改密码</li></a>

                </ul>
            </div>
            <div class="order_right" >
                <p style="font-size: 20px;">个人中心>收货地址</p>
                <div class="close_contenter">
                    <div id="close_add">

                    </div>

                    <div class="close_add1">
                        <a href="${pageContext.request.contextPath}/addManageAddress.jsp">+</a>
                        <h6>添加新地址</h6>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
