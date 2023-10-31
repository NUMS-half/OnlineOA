<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/22
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>审批流设置</title>

    <script>
        function updateFlow(row) {
            var cid = row.cells[1].innerText;
            var firstTeacherSelect = row.querySelector('#setTeacher1');
            var secondTeacherSelect = row.querySelector('#setTeacher2');

            var firstTeacherId = firstTeacherSelect.options[firstTeacherSelect.selectedIndex].text;
            var secondTeacherId = secondTeacherSelect.options[secondTeacherSelect.selectedIndex].text;

            var flag = confirm("请确认修改信息：" + "\n课程编号：" + cid + "\n第一审批人：" + firstTeacherId + "\n第二审批人：" + secondTeacherId);

            if (flag) window.location.href = "updateFlow?cid=" + cid + "&tea1=" + firstTeacherId + "&tea2=" + secondTeacherId;
        }

        function backToMain() {
            window.location.href = "AdminMain.html";
        }
    </script>

    <style>
        * {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        body {
            font-family: Arial;
            -webkit-font-smoothing: antialiased;
            background-image: url("imgs/background.jpg");
            background-size: 1920px 1080px;
        }

        h3 {
            text-align: center;
            font-size: 25px;
            letter-spacing: 1px;
            color: black;
            padding: 10px 0;
        }

        table {
            border-radius: 5px;
            font-size: 16px;
            font-weight: normal;
            border: none;
            border-collapse: collapse;
            width: 100%;
            max-width: 1000px;
            white-space: nowrap;
            background-color: white;
            margin: 20px auto;
        }
        th, td {
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .fl-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .fl-table tbody tr:hover {
            background-color: #e5e5e5;
        }
        .fl-table select {
            padding: 6px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .fl-table input[type="button"] {
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        .fl-table input[type="button"]:hover {
            background-color: #45a049;
        }
        .func a::before {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }
        .func a::after {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }
        .func {
            text-align: center;
            color: #3498DB;
        }
        .func a {
            color: #3498DB;
            text-decoration: none;
        }
        .func a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
<h3>审批流设置</h3>
<hr/>

<div class="func">
    <a onclick="backToMain()">返回管理员主界面</a>
</div>

<table class="fl-table">
    <thead>
    <tr>
        <th>序号</th>
        <th>课程编号</th>
        <th>第一审批人</th>
        <th>第二审批人</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flowList}" var="flow" varStatus="status">
    <tr>
        <td>${status.index + 1}</td>
        <td>${flow.cid}</td>
        <td>
            <label for="setTeacher1"></label><select name="firstTeacherId" id="setTeacher1">
                <c:forEach items="${teacherList}" var="teacher" varStatus="i">
                    <c:choose>
                        <c:when test="${teacher.uid == flow.firstTeacherId}">
                            <option value="i" selected>${teacher.uid}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="i">${teacher.uid}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </td>
        <td>
            <label for="setTeacher2"></label><select name="secondTeacherId" id="setTeacher2">
                <c:forEach items="${headTeacherList}" var="teacher" varStatus="i">
                    <c:choose>
                        <c:when test="${teacher.uid == flow.secondTeacherId}">
                            <option value="i" selected>${teacher.uid}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="i">${teacher.uid}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </td>
        <td><input type="button" value="确认设置" onclick="updateFlow(this.parentNode.parentNode)"></td>
    </tr>
    </c:forEach>
    <tbody>
</table>

</body>
</html>
