<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="">
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <style>
        :root {
            /* COLORS */
            --white: #e9e9e9;
            --gray: #333;
            --blue: #0367a6;
            --lightblue: #008997;

            /* RADII */
            --button-radius: 0.7rem;

            /* SIZES */
            --max-width: 758px;
            --max-height: 500px;

            font-size: 16px;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
            Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
        }

        body {
            align-items: center;
            background-color: white;

            /* 决定背景图像的位置是在视口内固定，或者随着包含它的区块滚动。 */
            /* https://developer.mozilla.org/zh-CN/docs/Web/CSS/background-attachment */

            background-attachment: fixed;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            display: grid;
            height: 100vh;
            place-items: center;
        }

        .form__title {
            font-weight: 300;
            margin: 0;
            margin-bottom: 1.25rem;
        }

        .link {
            color: var(--gray);
            font-size: 0.9rem;
            margin: 1.0rem 0;
            text-decoration: none;
        }

        .container {

            background-color: var(--white);
            border-radius: var(--button-radius);
            box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
            0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
            height: var(--max-height);
            max-width: var(--max-width);
            overflow: hidden;
            position: relative;
            width: 100%;
        }

        .container__form {
            height: 100%;
            position: absolute;
            top: 0;
            transition: all 0.6s ease-in-out;
        }

        .container--signin {
            left: 0;
            width: 50%;
            z-index: 2;
        }

        .container.right-panel-active .container--signin {
            transform: translateX(100%);
        }

        .container--signup {
            left: 0;
            opacity: 0;
            width: 50%;
            z-index: 1;
        }

        .container.right-panel-active .container--signup {
            animation: show 0.6s;
            opacity: 1;
            transform: translateX(100%);
            z-index: 5;
        }

        .container__overlay {
            height: 100%;
            left: 50%;
            overflow: hidden;
            position: absolute;
            top: 0;
            transition: transform 0.6s ease-in-out;
            width: 50%;
            z-index: 100;
        }

        .container.right-panel-active .container__overlay {
            transform: translateX(-100%);
        }

        .overlay {
            background-color: var(--lightblue);
            background: url("https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg");
            background-attachment: fixed;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            height: 100%;
            left: -100%;
            position: relative;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
            width: 200%;
        }

        .container.right-panel-active .overlay {
            transform: translateX(50%);
        }

        .overlay__panel {
            align-items: center;
            display: flex;
            flex-direction: column;
            height: 100%;
            justify-content: center;
            position: absolute;
            text-align: center;
            top: 0;
            transform: translateX(0);
            transition: transform 0.6s ease-in-out;
            width: 50%;
        }

        .overlay--left {
            transform: translateX(-20%);
        }

        .container.right-panel-active .overlay--left {
            transform: translateX(0);
        }

        .overlay--right {
            right: 0;
            transform: translateX(0);
        }

        .container.right-panel-active .overlay--right {
            transform: translateX(20%);
        }

        .btn {
            background-color: var(--blue);
            background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
            border-radius: 20px;
            border: 1px solid var(--blue);
            color: var(--white);
            cursor: pointer;
            font-size: 0.8rem;
            font-weight: bold;
            letter-spacing: 0.1rem;
            padding: 0.9rem 4rem;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
        }

        #form1>.btn {
            margin-top: 1.0rem;
        }
        #form2>.btn {
            margin-top: 1.0rem;
        }

        .btn:active {
            transform: scale(0.95);
        }

        .btn:focus {
            outline: none;
        }

        .form {
            background-color: var(--white);
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 0 ;
            height: 100%;
            text-align: center;
        }

        .input {
            background-color: #fff;
            border: none;
            padding: 0.9rem 0.9rem;
            margin: 0.5rem 0;
            width: 100%;
        }

        input::-webkit-input-placeholder {
            /* placeholder颜色 */
            color: #aab2bd;
            /* placeholder字体大小 */
            font-size: 10px;
        }
        li{
            list-style:none ;
            display: flex;
        }
        .ulsignup label{
            display: block;
            text-align: right;
            width: 100px;
            font-size: 20px;
            margin-right: 10px;
            margin-top: 10px;
            padding: 0.5rem ;
        }
        .ulsignin label{
            display: block;
            text-align: right;
            width: 120px;
            font-size: 20px;

            margin-top: 10px;
            padding: 0.5rem 0.5rem  0.5rem  0;
        }

        #incode{
            width: 40%;
            padding: 0.9rem 0px 0.9rem 10px ;
        }

        .img{
            background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
            display: flex;
            width: 200px;
            margin-left: 10px;
            margin-right: 5px;
        }
        span{
            text-align: left;
            display: flex;
            margin-right: auto;
        }

        @keyframes show {

            0%,
            49.99% {
                opacity: 0;
                z-index: 1;
            }

            50%,
            100% {
                opacity: 1;
                z-index: 5;
            }
        }

    </style>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script>
        function changeImg(){
            var imgObj = document.getElementById("img");
            imgObj.src = "CaptchaServlet?"+new Date().getTime();
        }
    </script>
    <script>
        $(function (){
            $("#sinphonenumber").blur(function (){
                $.post("CustomerServlet?action=checkRegistPhone","phonenumber="+$(this).val(),
                    function (responseDate){
                    if (responseDate != "empty"){
                        if (responseDate != "false"){
                            console.log(responseDate)
                            $("#sinpnspan1").css("color","green").html("手机号未被注册");
                        }
                        if (responseDate == "false"){
                            console.log(responseDate);
                            $("#sinpnspan1").css("color","red").html("手机号已被注册");
                        }
                    }

                    },
                    "text"
                )
            })
        })
    </script>

</head>

<body>
<div class="container right-panel-active">
    <!-- Sign Up -->
    <div class="container__form container--signup">
        <form method="post" action="CustomerServlet?action=login" class="form" id="form1">
            <h2 class="form__title">用 户 登 录</h2>
            <ul class="ulsignup" style="margin: 0; padding:0;">
                <li>
                    <label >手机号</label>
                    <input type="text" id="phonenumber" name="phonenumber" placeholder="请输入您的手机号码:" class="input" onblur="pncheck()"  style="margin-right:5px ;"/>

                </li>
                <span id="pnspan"></span>

                <li>
                    <label >密码 </label>
                    <input type="password" id="password" name="password" placeholder="请输入密码:" class="input" onblur="pwdcheck()" style="margin-right:5px ;"/>

                </li>
                <span id="pwdspan">  </span>



                <li class="li" style="list-style: none;">
                    <label style="margin-right: 2.4px;">验证码</label>
                    <input type="text"  placeholder="请输入验证码:" class="input" id="incode"  name="captcha" style="float: left;"/>
                    <img src="CaptchaServlet" alt="" id="img" class="img" onclick="changeImg()">
                </li>

            </ul>
            <a href="#" class="link">忘记密码?</a>
            <input type="submit"  value="登 录" class="btn" />
        </form>
    </div>

    <!-- Sign In -->
    <div class="container__form container--signin">
        <form method="post" action="CustomerServlet?action=regist" class="form" id="form2">
            <h2 class="form__title">用 户 注 册</h2>
            <ul style="margin: 0; padding:0;" class="ulsignin">
                <li>
                    <label >用户名</label>
                    <input type="text" id="username" name="username" placeholder="请输入您的用户名:"  onblur="unamecheck()" class="input" />
                </li>
                <span id="unamespan"></span>
                <li>
                    <label >手机号</label>
                    <input type="text" id="sinphonenumber" name="phonenumber" placeholder="请输入您的手机号码:" onblur="phncheck()" class="input" />
                </li>
                <span id="sinpnspan"></span><span id="sinpnspan1"></span>
                <li>
                    <label >密码</label>
                    <input type="password" id="sinpassword" name="password" placeholder="输入8~20以内的数字、英文字母、下划线组合" onblur="signincheck()" class="input" />
                </li>
                <span id="sinpwdspan"></span>
                <li>
                    <label >确认密码</label>
                    <input type="password" id="surepwd" name="surepassword" placeholder="请输入确认密码:" onblur="spwdcheck()" class="input" />
                </li>
                <span id="spwdspan"></span>
            </ul>



            <input type="submit" class="btn" value="注 册" />
        </form>
    </div>

    <!-- Overlay -->
    <div class="container__overlay">
        <div class="overlay">
            <div class="overlay__panel overlay--left">
                <button class="btn" id="signIn">前往注册</button>
            </div>
            <div class="overlay__panel overlay--right">
                <button class="btn" id="signUp">返回登录</button>
            </div>
        </div>
    </div>
</div>


</body>
<script>
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");

    signInBtn.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
    });

    signUpBtn.addEventListener("click", () => {
        container.classList.add("right-panel-active");
    });


</script>
<script>
    function pncheck(){
        var reg1 = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        var pnspan = document.querySelector("#pnspan");
        var phone = document.querySelector("#phonenumber");
        if ( !phone.value ) {
            pnspan.innerHTML=` &emsp;&emsp;&emsp;&emsp; &emsp;&emsp;手机号不能为空！`;
            pnspan.style.color = 'red';

        }else{
            if(reg1.test(phone.value) ){
                pnspan.innerHTML=' &emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☑';
                pnspan.style.color = 'green';
                phone.style.color = 'green';

            }else{
                pnspan.innerHTML='&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒请输入正确的手机号！';
                pnspan.style.color = 'red';
                phone.style.color = 'red';
            }
        }
    }

    function pwdcheck(){
        var reg2 = /^\w{8,20}$/;
        var pwdspan = document.querySelector("#pwdspan");
        var pwd = document.querySelector("#password");
        if (!pwd.value) {
            pwdspan.innerHTML=` &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 密码不能为空！`;
            pwdspan.style.color = 'red';
        }else{
            if(reg2.test(pwd.value) ){
                pwdspan.innerHTML=' &emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☑';
                pwdspan.style.color = 'green';
                pwd.style.color = 'green';

            }else{
                pwdspan.innerHTML='&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒请输入正确的密码！';
                pwdspan.style.color = 'red';
                pwd.style.color = 'red';
            }
        }
    }

</script>
<script>
    function unamecheck(){
        var reg3 = /^.{1,20}$/;
        var unamespan = document.querySelector("#unamespan");
        var username = document.querySelector("#username");
        if (!username.value) {
            unamespan.innerHTML=`&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 用户名不能为空`;
            unamespan.style.color = 'red';
        }else{
            if(reg3.test(username.value) ){
                username.style.color = 'green';
                unamespan.innerHTML='';
            }else{
                unamespan.innerHTML='&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒请输入字符数小于20的用户名！';
                unamespan.style.color = 'red';
                username.style.color = 'red';
            }
        }
    }
    function phncheck(){
        var reg4 = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        var pnspan = document.querySelector("#sinpnspan");
        var phone = document.querySelector("#sinphonenumber");
        if ( !phone.value ) {
            pnspan.innerHTML=` &emsp;&emsp;&emsp;&emsp; &emsp;&emsp;手机号不能为空！`;
            pnspan.style.color = 'red';

        }else{
            if(reg4.test(phone.value) ){
                pnspan.innerHTML='';
                phone.style.color = 'green';

            }else{
                pnspan.innerHTML='&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒请输入正确的手机号！';
                pnspan.style.color = 'red';
                phone.style.color = 'red';
            }
        }
    }
    function signincheck(){
        var reg5 = /^\w{8,20}$/;
        var pwdspan = document.querySelector("#sinpwdspan");
        var pwd = document.querySelector("#sinpassword");
        if (!pwd.value) {
            pwdspan.innerHTML=` &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;密码不能为空`;
            pwdspan.style.color = 'red';
        }else{
            if(reg5.test(pwd.value) ){
                pwd.style.color = 'green';
                pwdspan.innerHTML='';

            }else{
                pwdspan.innerHTML='&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒请输入正确格式的密码！';
                pwdspan.style.color = 'red';
                pwd.style.color = 'red';
            }
        }
    }
    function spwdcheck(){

        var spwdspan = document.querySelector("#spwdspan");
        var pwd = document.querySelector("#sinpassword");
        var spwd = document.querySelector("#surepwd");
        if (spwd.value == pwd.value ) {
            spwd.style.color='green';
            spwdspan.innerHTML='';
        }else{
            spwd.style.color='red';
            spwdspan.innerHTML="<font color='red'>&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;☒两次密码不同</font>";

        }
    }

</script>
</html>
