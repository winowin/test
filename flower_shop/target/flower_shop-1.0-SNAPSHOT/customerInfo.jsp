<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/shop.css">
    <link rel="stylesheet" type="text/css" href="css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/cinfo.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"CustomerServlet?action=customerInfo",
                success:function (respDate){
                    $(respDate).each(function (){

                        $("#customer_info").append(
                            "<form class='layui-form  layui-col-space10' method='post' action='CustomerServlet?action=updateCustomerInfo' enctype='multipart/form-data' style='width: 80%; padding: 30px;'>"+
                            "<div class='avatar'>"+
                            "<input type='hidden' name='avatar' value='"+this.avatar+"'>"+
                            "<div id='avatarimg'><img  src='${pageContext.request.contextPath}/images/"+this.avatar+"'  style='width: 84px; height:84px;border-radius:42px; '/></div>"+
                            "</div>"+
                            "<div style='text-align: center;'><a  class='file'>修改头像<input type='file' name='avatarimg' id=''></a></div>"+
                            "<div class='layui-form-item  layui-col-space10'>"+
                            "<div class='layui-col-md6'>"+
                            "<label class='labels' ><span style='float: left;'>姓</span><span style='float:right;'>名</span></label>"+
                            " <input type='text' name='username' value='"+this.username+"' placeholder='姓名' autocomplete='off' class='layui-input'>"+
                            "</div>"+
                            "<div class='layui-col-md6'>"+
                            "<label class='labels' ><span style='float: left;'>性</span><span style='float:right;'>别</span></label>"+
                            "<div id='gender'><input type='text' name='usergender' value='"+this.usergender+"' placeholder='姓别' autocomplete='off' class='layui-input'></div>"+
                            "</div>"+
                            "<div class='layui-col-md6'>"+
                            "<label class='labels' ><span>手机号码</span></label>"+
                            " <input type='text' name='phonenumber' value='"+this.phonenumber+"' placeholder='手机号' autocomplete='off' class='layui-input'>"+
                            "</div>"+
                            "<div class='layui-col-md6' >"+
                            "<label class='labels' ><span style='float: left;'>生</span><span style='float:right;'>日</span></label>"+
                            "<input type='date' name='birthday' class='layui-input' value='"+this.birthday+"'>"+
                            "</div>"+
                            "</div>"+
                            "<div>"+
                            "<button class='saveinfo b1' type='submit' style='float: right;'>保存</button>"+
                            "</div>"+
                            "</form>"

                        );
                            if (!this.avatar){
                                $('#avatarimg').empty();
                                $('#avatarimg').append(
                                    "<img src='images/3.jpg' style='width: 84px; height:84px; ' alt=''>"
                                );
                            }

                            if (!(this.usergender == '男'||this.usergender=='女'||this.usergender=='保密')){
                                alert("性别设置有误,请设置”正确信息“或”保密“！");
                                $('#gender').empty();
                                $('#gender').append(
                                    "<input type='text' name='usergender' value='保密' placeholder='姓别' autocomplete='off' class='layui-input'>"
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
<div class="order_content">
    <div class="order">
        <p style="color: #333;font-size: 13px;">首页 > 我的</p>
        <div class="order_content_div">
            <div class="order_left">
                <ul class="">
                    <li class="box">订单中心</li>
                    <a href="${pageContext.request.contextPath}/orderinfo.jsp"><li class="min"  >我的订单</li></a>

                    <li class="box">个人中心</li>
                    <a href="CustomerServlet?action=toCustomerInfo"><li class="min" style="color:#f8c78b;">我的信息</li></a>
                    <a href="${pageContext.request.contextPath}/manageAddress.jsp"><li class="min">收货地址</li></a>

                    <li class="box">账户管理</li>
                    <a href="${pageContext.request.contextPath}/updatePwd.jsp"><li class="min">修改密码</li></a>

                </ul>
            </div>
            <div class="order_right" >
                <p style="font-size: 20px;">个人中心>我的信息</p>
                <div class="addinfo" id="customer_info" style=" position: relative; width: 60%;margin: 0;left: 200px; ">

                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
