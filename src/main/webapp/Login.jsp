<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/20
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>登录-在线课程申请审批系统</title>

    <script>
        function closeLoginFailedPopup() {
            var loginFailedPopup = document.getElementById("loginFailedPopup");
            loginFailedPopup.style.display = "none";
        }

        function goBack() {
            window.history.back();
        }

        // 在页面加载完成时检查登录失败的请求属性，并显示相应的弹窗
        window.onload = function() {
            var loginFailed = "${login}";
            var loginFailedPopup = document.getElementById("loginFailedPopup");
            if (loginFailed === "false") {
                loginFailedPopup.style.display = "block";
            } else {
                loginFailedPopup.style.display = "none";
            }
        };
    </script>

    <style>
        * {
            box-sizing: border-box;
        }

        *:focus {
            outline: none;
        }

        /*设置页面背景样式*/
        body {
            font-family: Arial;
            background-image: url("imgs/background.jpg");
            background-size: 1920px 1080px;
            padding: 100px;
        }

        /*设置网页标题样式*/
        .web_title {
            font-size: medium;
            text-align: center;
        }

        /*设置登录模块背景样式*/
        .login {
            margin: 20px auto;
            width: 320px;
        }

        .login_screen {
            background-color: #FFF;
            padding: 20px;
            border-radius: 20px
        }

        /*设置登录模块标题样式*/
        .login_title {
            font-size: small;
            text-align: center;
            color: #777;
        }

        .login_form {
            text-align: center;
        }

        .control_group {
            margin-bottom: 10px;
        }

        /*设置输入框样式*/
        input {
            text-align: center;
            background-color: #ECF0F1;
            border: 2px solid transparent;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 200;
            padding: 10px 0;
            width: 250px;
            transition: border .5s;
        }

        /*设置输入框聚焦样式*/
        input:focus {
            border: 2px solid #3498DB;
            box-shadow: none;
        }

        /*设置按钮样式*/
        .btn {
            border: 2px solid transparent;
            background: #3498DB;
            color: #ffffff;
            font-size: 16px;
            line-height: 25px;
            padding: 10px 0;
            text-decoration: none;
            text-shadow: none;
            border-radius: 10px;
            box-shadow: none;
            transition: 0.25s;
            display: block;
            width: 250px;
            margin: 0 auto;
        }

        /*设置按钮悬停样式*/
        .btn:hover {
            background-color: #2980B9;
        }

        /*设置注册超链接的样式*/
        .signup_link {
            font-size: 12px;
            color: #2980B9;
            display: block;
            margin-top: 12px;
        }

         /*设置弹窗样式 */
        .custom-popup {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: 99999;
            background-color: rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(5px);
            align-items: center;
            justify-content: center;
            display: none;
        }

        .popup-content {
            background-color: #FFF;
            width: 400px;
            max-width: 80%;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .popup-content h3 {
            font-size: 18px;
            color: #777;
        }

        .popup-content p {
            font-size: 16px;
            color: #777;
        }

        .popup-content button {
            border: none;
            background-color: #3498DB;
            color: #FFF;
            font-size: 14px;
            padding: 10px 20px;
            margin-top: 20px;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div id="loginFailedPopup" class="custom-popup">
    <div class="popup-content">
        <h3>登录失败</h3>
        <p>用户名或密码错误，请重试！</p>
        <button onclick="closeLoginFailedPopup()">关闭</button>
    </div>
</div>

<form action="login" method="post">
    <div class="login">
        <div class="web_title">
            <h1>在线课程申请审批系统</h1>
        </div>
        <div class="login_screen">
            <div class="login_title">
                <h1>登录</h1>
            </div>
            <div class="login_form">
                <div class="control_group">
                    <input type="text" class="login-field" value="" placeholder="uid" name="uid"
                           id="login_uid">
                    <label class="login-field-icon fui-user" for="login_uid"></label>
                </div>
                <div class="control_group">
                    <input type="password" class="login-field" value="" placeholder="password" name="password"
                           id="login_pw">
                    <label class="login-field-icon fui-lock" for="login_pw"></label>
                </div>
                <input type="submit" class="btn"/>
                <a class="signup_link" href="SignUp.html">没有账户? 去注册</a>

            </div>
        </div>
    </div>
</form>

</body>
</html>
