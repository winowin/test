
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>
    <link rel="stylesheet" href="css/layui.css">
    <style>
        body{
            background-color: #f9f9fa;
        }
        .addinfo{
            background-color: #fff;
            margin: 100px auto;
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
    </style>
</head>
<body>
<div class="addinfo" style=" width: 55%;">
    <h2 style=" padding-left: 50px;padding-top: 30px;">修改收货信息</h2>
    <form class="layui-form  layui-col-space10" method="post" action="CustomerServlet?action=updateAddress&addressid=${address.addressid}" style="width: 60%; padding: 50px;">
        <div class="layui-form-item  layui-col-space10">
            <div class="layui-col-md6">
                <input type="text" name="username" value="${address.username}"    class="layui-input">
            </div>
            <div class="layui-col-md6">
                <input type="text" name="phonenumber" value="${address.phonenumber}"   class="layui-input">
            </div>
        </div>
        <div class="layui-form-item  layui-col-space10">
            <div class="layui-col-md4" >
                <select name="province" style="display: block;" ></select>
            </div>
            <div class="layui-col-md4" >
                <select name="city"  style="display: block;" ></select>
            </div>
            <div class="layui-col-md4" >
                <select name="county" style="display: block;float: right;"></select>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-col-md12">
                <textarea name="address"  placeholder="请输入详细地址："  class="layui-textarea"> ${address.address}</textarea>
            </div>
        </div>
        <div class="layui-form-item  layui-col-space10">
            <div class="layui-col-md6">
                <input type="text" name="zipcode" value="${address.zipcode}" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-col-md6">
                <input type="text" name="nickname" value="${address.nickname}"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div>
            <button class="saveinfo b1" type="submit" style="float: right;">保存</button>
            <a href="${pageContext.request.contextPath}/manageAddress.jsp"><button class="saveinfo b2" type="button"  style="float: right;">取消</button></a>
        </div>
    </form>
</div>


<!-- 导入js文件 -->
<script type="text/javascript" src="js/city.js"></script>
<script type="text/javascript" src="js/cascading.js"></script>
</body>
</html>
