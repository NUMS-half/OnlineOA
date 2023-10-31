<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/10
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>修改课程信息</title>

    <script>
        function updateConfirm() {
            confirm("请您再次确定修改后的课程信息！(注意：对课程进行修改后，该课程的审批流将恢复默认！若需修改还请在“审批流设置”功能中重新设置。)");
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
            padding: 20px;
        }

        /*设置网页标题样式*/
        .web_title {
            font-size: medium;
            text-align: center;
        }

        /*设置背景样式*/
        .update {
            margin: 20px auto;
            width: 450px;
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
<form action="updateCourse" method="get" accept-charset="UTF-8">
    <div class="update">
        <div class="web_title">
            <h1>修改课程信息</h1>
        </div>

        <div class="update_screen">
            <div class="update_title">
                <h1>修改课程 (ID:${cname}) 的信息</h1>
            </div>
            <div class="update_form">
                <div class="control_group">
                    <h4>课程编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="update-field" value="${cid}"
                                                               name="cid" id="new_cid" readonly="readonly"></h4>
                    <label class="update-field-icon fui-user" for="new_cid"></label>
                </div>

                <div class="control_group">
                    <h4>课程名称&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="update-field" value="${cname}"
                                                               name="cname" id="new_cname" required="required"></h4>
                    <label class="update-field-icon fui-user" for="new_cname"></label>
                </div>

                <div class="control_group">
                    <h4>课程学分&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="update-field" value="${credit}"
                                                               name="credit" id="new_credit" required="required"></h4>
                    <label class="update-field-icon fui-user" for="new_credit"></label>
                </div>

                <div class="control_group">
                    <h4>主讲教师&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="add-field" value="${teacherId}"
                                                               name="teacherId" id="new_teacherId" required="required">
                    </h4>
                    <label class="add-field-icon fui-user" for="new_teacherId"></label>
                </div>

                <div class="control_group">
                    <h4>课时安排&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="add-field" value="${takeTime}"
                                                               name="takeTime" id="new_takeTime" required="required">
                    </h4>
                    <label class="add-field-icon fui-user" for="new_takeTime"></label>
                </div>

                <div class="control_group">
                    <h4>课程备注&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="add-field" value="${note}" name="note"
                                                               id="new_note"></h4>
                    <label class="add-field-icon fui-user" for="new_note"></label>
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
