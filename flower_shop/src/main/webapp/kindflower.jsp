<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="">
<head>
    <meta charset="utf-8" />
    <title>美尼亚花店</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/list.css">

    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 60%;
        }
        .ulli ul{
            overflow: hidden;
            width: 100%;
            text-align: center;
            padding: 0;
        }
        .flowerkind li{
            list-style: none;
            float: left;
        }
        .flowerkind a{
            padding: 4px;
            text-decoration: none;
            color:#f8c78b;
        }
        .flowerkind a:hover{
            color: #f6a441;
        }
        .fakeimg{
            padding: 10px;
        }
    </style>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"ShoppingFlowerServlet?action=flowersMaxSales",
                success:function (respDate){

                    $(respDate).each(function (){
                            $("#box1").append(
                                "<ul  style='  padding:0 ;margin: 0;'>"+
                                "<li style=' width: 96%;height: 90%'>"+
                                " <div class='pro_img'><img src='images/jt.jpeg' style='width:150px; height:150px;' ></div>"+
                                "<h6 class='pro_name'><a href='#'> "+this.flowername+" </a></h6>"+
                                "<p class='pro_price'>"+this.price+"元</p><p class='pro_rank'>销量:"+this.sales+"</p>"+
                                "<a href='ShoppingFlowerServlet?action=flowersDetails&flowerid="+this.flowerid+"'><div class='add_btn' >立即选购</div></a>"+
                                " </li>"+
                                "</ul>"

                            );
                        }
                    )
                }
            });
        })
    </script>
    <script>
        $(function (){
            $.ajax({
                type:"GET",
                url:"ShoppingFlowerServlet?action=queryFlowerkind",
                success:function (respDate){

                    $(respDate).each(function (){
                            $("#flowerkind").append(
                                "<li><a href='ShoppingFlowerServlet?action=searchPageFlowerKind&kindid="+this.kindid+"'>"+this.kindname+"</a></li>"
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

<div id="demo" class="carousel slide" data-ride="carousel">

    <!-- 指示符 -->
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
        <li data-target="#demo" data-slide-to="2"></li>
    </ul>

    <!-- 轮播图片 -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <a href="ShoppingFlowerServlet?action=flowersDetails&flowerid=92"><img src="images/wide1.jpg"></a>
        </div>
        <div class="carousel-item">
            <a href="ShoppingFlowerServlet?action=flowersDetails&flowerid=8"><img src="images/wide2.jpg"></a>
        </div>
        <div class="carousel-item">
            <a href="ShoppingFlowerServlet?action=flowersDetails&flowerid=62"><img src="images/wide3.jpg"></a>
        </div>
    </div>

    <!-- 左右切换按钮 -->
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>

</div>


<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <div id="box">
                <h2 class="box_head">热门鲜花 欢迎选购</h2>
                <ul>
                    <c:forEach items="${pageBean.records}" var="f">
                        <li>
                            <div class="pro_img">
                                <img src="images/lh.jpeg" width="150" height="150">
                            </div>
                            <h6 class="pro_name"><a href="#">${f.flowername}</a></h6>
                            <p class="pro_price">${f.price}元</p>
                            <p class="pro_rank">销量:${f.sales}</p>
                            <a href="ShoppingFlowerServlet?action=flowersDetails&flowerid=${f.flowerid}"><div class="add_btn" >立即选购</div></a>
                        </li>
                    </c:forEach>



                </ul>

            </div>
            <div class="center">
                <ul class="pagination">
                    <li style="position: relative;top: 10px">第${pageBean.pageNum}页/共${pageBean.totalPage}页&nbsp;&nbsp;</li>
                    <li><a href="ShoppingFlowerServlet?action=searchPageFlowerKind&pageNum=${1}&kindid=${kindid}">首页</a></li>
                    <li><a href="ShoppingFlowerServlet?action=searchPageFlowerKind&pageNum=${pageBean.prePageNum}&kindid=${kindid}">«</a></li>
                    <li><a href="ShoppingFlowerServlet?action=searchPageFlowerKind&pageNum=${pageBean.nextPageNum}&kindid=${kindid}">»</a></li>
                    <li><a href="ShoppingFlowerServlet?action=searchPageFlowerKind&pageNum=${pageBean.totalPage}&kindid=${kindid}">尾页</a></li>
                    <li><input type="hidden" id="jumpKindid" name="kindid" value="${kindid}"><input type="text" id="tPageNum" name="tPageNum" value="${pageBean.pageNum}" size="2"></li>
                    <li><button class="pagebtn" id="jump">跳转</button></li>
                </ul>
            </div>
        </div>

    </div>
    <div class="rightcolumn">
        <div class="card">
            <h5>鲜花种类</h5>
            <div class="fakeimg" >
                <div class="ulli">
                    <ul class="flowerkind" id="flowerkind">

                    </ul>
                </div>


            </div>
            <p>关于花语的一些信息..</p>
        </div>
        <div class="card">
            <h5>热销鲜花</h5>
            <div id="box1" >

            </div>
        </div>
        <div class="card">
            <h3>关注我</h3>
            <p>一些文本...</p>
        </div>
    </div>
</div>

<div class="footer">
    <p>美尼亚首页|美尼亚智选|美尼亚精品|关于美尼亚|官方博客 <br>
        &copy;2022 美尼亚公司 美尼亚公司 隐私政策 京ICP证123456号 京ICP备100012345号</p>
</div>
</body>
<%--分页跳转--%>
<script>
    $(function () {
        $("#jump").click(function (){
            let tNum = $("#tPageNum").val();
            let kindid = $('#jumpKindid').val();
            let reg =/^[0-9]*$/;
            if (!reg.test(tNum)){
                alert("您的输入有误，请输入正确的页码数！");
                return;
            }
            if (tNum>${pageBean.totalPage}){
                alert("您输入的页码数超出了范围");
                return;
            }
            if (tNum == 0){
                alert("您输入的页码数超出了范围");
                return;
            }
            location.href='ShoppingFlowerServlet?action=searchPageFlowerKind&pageNum='+tNum+'&kindid='+kindid;
        });
    })
</script>
</html>
