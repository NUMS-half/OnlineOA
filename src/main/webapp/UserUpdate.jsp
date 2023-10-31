<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/8
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>修改用户信息</title>

    <script>
        function updateConfirm() {
            confirm("请您再次确定修改后的用户信息！");
        }
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
            background-color: #80cfff;
            padding: 60px;
        }

        /*设置网页标题样式*/
        .web_title {
            font-size: medium;
            text-align: center;
        }

        /*设置背景样式*/
        .update {
            margin: 20px auto;
            width: 400px;
        }

        .update_screen {
            background-color: #FFF;
            padding: 25px;
            border-radius: 20px
        }

        /*设置标题样式*/
        .update_title {
            font-size: small;
            text-align: center;
            color: #777;
        }

        .update_form {
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
            width: 120px;
            margin: 0 auto;
        }

        .btn-cancel {
            border: 2px solid transparent;
            background: gray;
            color: #ffffff;
            font-size: 16px;
            line-height: 25px;
            padding: 10px 0;
            text-decoration: none;
            text-shadow: none;
            border-radius: 10px;
            box-shadow: none;
            transition: 0.25s;
            width: 120px;
            margin: 0 auto;
        }

        /*设置按钮悬停样式*/
        .btn:hover {
            background-color: #2980B9;
        }

        .btn-cancel:hover {
            background-color: black;
        }

        /*设置下拉框样式*/
        select {
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

        select:focus {
            border: 2px solid #3498DB;
            box-shadow: none;
        }

        #state {
            display: none;
        }
    </style>
</head>
<body>
<form action="updateUser" method="get" accept-charset="UTF-8">
    <div class="update">
        <div class="web_title">
            <h1>修改用户信息</h1>
        </div>

        <div class="update_screen">
            <div class="update_title">
                <h1>修改用户：${username} 的信息</h1>
            </div>
            <div class="update_form">
                <div class="control_group">
                    <h4>用户编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="update-field" value="${uid}"
                                                               name="uid"
                                                               id="new_uid" readonly="readonly"></h4>
                    <label class="update-field-icon fui-user" for="new_uid"></label>
                </div>

                <div class="control_group">
                    <h4>用户姓名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="update-field" value="${username}"
                                                               name="username" id="new_username" required="required">
                    </h4>
                    <label class="update-field-icon fui-user" for="new_username"></label>
                </div>

                <div class="control_group">
                    <h4>用户密码&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="add-field" value="${password}"
                                                               name="password" id="new_pw" required="required"></h4>
                    <label class="update-field-icon fui-user" for="new_pw"></label>
                </div>

                <div class="control_group">
                    <label for="new_identity">
                        <h4>用户身份&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="identityId" id="new_identity">
                                <c:choose>
                                    <c:when test="${identityId == 0}">
                                        <option value="0" selected>系统管理员</option>
                                        <option value="1">学生</option>
                                        <option value="2">课程主讲教师</option>
                                        <option value="3">主管教师</option
                                    </c:when>
                                    <c:when test="${identityId == 1}">
                                        <option value="0">系统管理员</option>
                                        <option value="1" selected>学生</option>
                                        <option value="2">课程主讲教师</option>
                                        <option value="3">主管教师</option
                                    </c:when>
                                    <c:when test="${identityId == 2}">
                                        <option value="0">系统管理员</option>
                                        <option value="1">学生</option>
                                        <option value="2" selected>课程主讲教师</option>
                                        <option value="3">主管教师</option
                                    </c:when>
                                    <c:when test="${identityId == 3}">
                                        <option value="0">系统管理员</option>
                                        <option value="1">学生</option>
                                        <option value="2" selected>课程主讲教师</option>
                                        <option value="3">主管教师</option
                                    </c:when>
                                </c:choose>
                            </select></h4>
                    </label>
                </div>
                <div class="control_group">
                    <label for="state">
                        <input type="text" value="set" name="updateState" id="state">
                    </label>
                </div>
                <input type="button" value="取消" onclick="history.back()" class="btn-cancel">&nbsp;&nbsp;&nbsp;
                <input type="submit" onclick="updateConfirm()" class="btn"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>
