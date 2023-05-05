<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="css/layui.css">
    <link rel="stylesheet" href="css/shop.css">
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
        .default{
            position: absolute;
            bottom: 137px;
            right: 20px;
            height: 20px;
            width: 80px;
            border: 1px solid #ccc;
            line-height: 20px;
            text-align: center;
            color: #424242;
            font-size: 14px;

        }

        .default:hover {
            color: #f8c78b;
            border-color: #f8c78b;

        }
        span.default{
            opacity: 0;
        }
        .close_add:hover span.cover{
            opacity: 1;
        }
        .isn{
            position: absolute;color: green;font-size: 23px;right: 0;bottom: 137px;opacity: 0;
        }

    </style>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"OrderServlet?action=showAddress",
                success:function (respDate){

                    // alert(typeof  respDate);
                    //前端编码
                    // $("#content").append("<table border='1' width='900px'> ");
                    $(respDate).each(function (){
                            $("#close_add").append(
                                "<div class='close_add' id="+'def'+this.addressid+">"+
                                "<span id="+'isd'+this.addressid+" class='isn'  >✔</span>"+
                                "<a href='javascript:setDefault("+this.addressid+")'><span  class='default cover' style='float: right;'>设为默认</span></a>"+
                                "<h1 >"+this.username+"</h1>" +
                                "<h6 >"+this.phonenumber+"</h6>" +
                                "<h6 >"+this.province+ '省' +this.city+ '市' +this.county+"</h6>" +
                                "<h6 >"+this.address+"</h6>" +
                                "<a href='OrderServlet?action=toUpdateAddress&addressid="+this.addressid+"'><span  class='del' style='margin-right:100px ;'>修改</span></a>" +
                                "<a href='OrderServlet?action=toDeleteAddress&addressid="+this.addressid+"'><span class='del'>删除</span></a>"+
                                "</div>"

                            );
                        }
                    )
                }
            });
        })
    </script>

</head>
<body >
<%@include file="header.jsp"%>
<!-- 主体内容 -->
<%  //产生一个唯一的令牌
    String token = UUID.randomUUID().toString();
    session.setAttribute("token",token);
%>
<form action="OrderServlet?action=orderSave" method="post">
<div class="close_content" >


        <div class="close_contenter">
            <h1>收货地址</h1>
            <div >

                <div id="close_add" >

                </div>

                <div class="close_add1">
                    <a href="${pageContext.request.contextPath}/addaddress.jsp">+</a>
                    <h6 onclick="location.href='addaddress.jsp'">添加新地址</h6>
                </div>



                <div class="clear"></div>
            </div>
            <h2>配送方式 <span style="color: #FF6A00;font-size: 13px;margin-left: 80px;">包邮</span></h2>
            <div class="close_data">
                <h3>送达时间</h3>
                <h5 style="border-color:rgb(255,106,0)">
                    <input type="datetime-local" name="datetime" class="measureDate" placeholder="请选择日期">
                </h5>
            </div>
            <h2 style="border: none;margin-top:0px; ">发票<span style="color: #FF6A00;font-size: 13px;margin-left: 125px;">电子发票&nbsp;&nbsp;  个人  &nbsp;&nbsp;商品明细&nbsp;&nbsp;  修改 ></span></h2>
            <h4>核对订单 <a href="ShoppingFlowerServlet?action=toShoppingCart">返回购物车&nbsp; <i class="fa fa-chevron-right"></i> </a></h4>

            <div class="close_shop">
                <c:forEach items="${items}" var="orderitem">
                        <p>
                            <a href=""><img src="images/1.jpg" ></a><a class="f_name" href="">${orderitem.flowername} </a>
                            <i>${orderitem.flowerprice}元 x ${orderitem.quantity}</i>
                            <span >${orderitem.smallprice}元</span>
                        </p>
                </c:forEach>

                <div class="clear"></div>
            </div>
            <div class="close_much">
                <div class="close_much_left">
                    <p><i class="fa fa-plus-square"></i>使用优惠券</p>
                    <p><i class="fa fa-plus-square"></i>使用礼品卡</p>
                </div>
                <div class="close_much_right">
                    <p>商品件数  :<span>件</span><span>${sessionScope.cart.totalNum}</span></p>
                    <p>商品总价  :<span>元</span><span>${sessionScope.cart.totalPrice}</span></p>
                    <p>优惠活动  :<span>元</span><span>-0</span></p>
                    <p>优惠券抵扣  :<span>元</span><span>-0</span></p>
                    <p>运费  :<span>元</span><span>0</span></p>
                    <p style="height: 50px;line-height:50px; ">应付总额  :<b class="zong"> 元</b><span class="zong">${sessionScope.cart.totalPrice}</span></p>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="close_clear">
            <input type="hidden" name="token" value="<%=token%>">
            <button type="submit" class="submit-cart">立即下单</button>
        </div>

</div>
</form>
<%@include file="footer.jsp"%>
<script>
    function  setDefault(addressid){
        $.post(
            "OrderServlet",
            "action=setDefaultAddress&addressid="+addressid,
            function (){
                //先清除所有的默认样式
                var def1 = document.querySelectorAll('.close_add');
                var isn = document.querySelectorAll('.isn');
                for (let i = 0; i < def1.length; i++) {
                    def1[i].style.border='1px solid #E0E0E0';
                }
                for (let i = 0; i < isn.length; i++) {
                    isn[i].style.opacity='0';
                }

                //在根据默认地址对应的地址id来添加默认样式
                var def =  document.getElementById('def'+addressid);
                var isd = document.getElementById('isd'+addressid);
                def.style.border="1px solid green";
                isd.style.opacity='1';
            }
        );
    }
</script>
<%--时间选择器js--%>
<script type="text/javascript">
    let date = new Date()
    let yyyy = date.getFullYear()
    let MM = (date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1)
    let dd = date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate()
    let HH = date.getHours() < 10 ? ("0" + date.getHours()) : date.getHours()
    let mm = date.getMinutes() < 10 ? ("0" + date.getMinutes()) : date.getMinutes()
    let curDay = yyyy + '-' + MM + '-' + dd + 'T' + HH + ':' + mm;
    $('.measureDate').val(curDay)
    console.log(date)
</script>

</body>

</html>

