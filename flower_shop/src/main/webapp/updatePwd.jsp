<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/shop.css">
    <link rel="stylesheet" type="text/css" href="css/layui.css">
    <style>
        .addinfo{
            background-color: #fff;
            margin: 20px auto;
        }
        .layui-form input{
            border-color: black;
        }
        .layui-form textarea{
            border-color: black;
        }
        .saveinfo{

            background-color: #f8c78b; /* Green */
            border: none;
            border-radius: 4px;
            color: white;
            padding: 13px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px 4px -2px;
            cursor: pointer;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;


        }
        .b2{
            margin-right: 10px;
        }
        .b1:hover {
            box-shadow: 0 10px 10px 0 rgba(0,0,0,0.24),0 10px 10px 0 rgba(0,0,0,0.19);
        }
        .b2:hover {
            box-shadow: 0 10px 10px 0 rgba(0,0,0,0.24),0 10px 10px 0 rgba(0,0,0,0.19);
        }
        .order_right,.order_left{
            border-radius:12px ;
        }
        .order_left ul li{
            font-size: 15px;
        }
        .layui-col-md6{
            width: 100%;
            padding-bottom: 16px;
        }
        .labels{
            float: left;
            width: 60px;
            margin-top: 8px;
        }
        .layui-input{
            width: 70%;
            float:right;
        }
        .layui-col-space10{
            margin-top: 20px;
        }
    </style>
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
                    <a href="${pageContext.request.contextPath}/manageAddress.jsp"><li class="min">收货地址</li></a>

                    <li class="box">账户管理</li>
                    <a href="${pageContext.request.contextPath}/updatePwd.jsp"><li class="min" style="color:#f8c78b;">修改密码</li></a>

                </ul>
            </div>
            <div class="order_right" >
                <p style="font-size: 20px;">账号管理>修改密码</p>
                <div class="addinfo" style=" position: relative; width: 60%;margin: 0;left: 200px; ">
                    <form class="layui-form  layui-col-space10" method="post" action="CustomerServlet?action=updatePwd" style="width: 80%; padding: 30px;">

                        <div class="layui-form-item  layui-col-space10">
                            <div class="layui-col-md6">
                                <label class="labels" >
                                    <span>旧</span>
                                    <span>密</span>
                                    <span>码</span>
                                </label>
                                <input type="password" name="oldpwd" value="" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-col-md6">
                                <label class="labels" >
                                    <span>新</span>
                                    <span>密</span>
                                    <span>码</span>
                                </label>
                                <input type="password" name="newpwd" value="" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-col-md6">
                                <label class="labels" >
                                    <span>确认密码</span>
                                </label>
                                <input type="password" name="renewpwd" value="" placeholder="确认输入的新密码" autocomplete="off" class="layui-input">
                            </div>

                        </div>


                        <div>
                            <button class="saveinfo b1" type="submit" style="float: right;">保存</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
