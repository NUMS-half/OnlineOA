<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/7
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        body {
            font-family: Arial;
            -webkit-font-smoothing: antialiased;
        }

        h3 {
            text-align: center;
            font-size: 25px;
            letter-spacing: 1px;
            color: black;
            padding: 2px;
        }

        /* Table Styles */

        .table-wrapper {
            margin: 10px 70px 70px;
            box-shadow: 0px 25px 25px rgba(0, 0, 0, 0.2);
        }

        .fl-table {
            border-radius: 5px;
            font-size: 16px;
            font-weight: normal;
            border: none;
            border-collapse: collapse;
            width: 100%;
            max-width: 100%;
            white-space: nowrap;
            background-color: white;
        }

        .fl-table td, .fl-table th {
            text-align: center;
            padding: 8px;
        }

        .fl-table td {
            border-right: 1px solid #f8f8f8;
            font-size: 14px;
        }

        .fl-table thead th {
            color: #EBEBEB;
            background: black;
        }


        .fl-table thead th:nth-child(odd) {
            color: #EBEBEB;
            background: black;
        }

        .fl-table tr:nth-child(even) {
            background: #F8F8F8;
        }

        .fl-table tbody tr:hover {
            background: #E6E6E6;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease;
        }

        /* Responsive */

        @media (max-width: 767px) {
            .fl-table {
                display: block;
                width: 100%;
            }

            .table-wrapper:before {
                content: "Scroll horizontally >";
                display: block;
                text-align: right;
                font-size: 11px;
                color: white;
                padding: 0 0 10px;
            }

            .fl-table thead, .fl-table tbody, .fl-table thead th {
                display: block;
            }

            .fl-table thead th:last-child {
                border-bottom: none;
            }

            .fl-table thead {
                float: left;
            }

            .fl-table tbody {
                width: auto;
                position: relative;
                overflow-x: auto;
            }

            .fl-table td, .fl-table th {
                padding: 20px .625em .625em .625em;
                height: 60px;
                vertical-align: middle;
                box-sizing: border-box;
                overflow-x: hidden;
                overflow-y: auto;
                width: 120px;
                font-size: 13px;
                text-overflow: ellipsis;
            }

            .fl-table thead th {
                text-align: left;
                border-bottom: 1px solid #f7f7f9;
            }

            .fl-table tbody tr {
                display: table-cell;
            }

            .fl-table tbody tr:nth-child(odd) {
                background: none;
            }

            .fl-table tr:nth-child(even) {
                background: transparent;
            }

            .fl-table tr td:nth-child(odd) {
                background: #F8F8F8;
                border-right: 1px solid #E6E4E4;
            }

            .fl-table tr td:nth-child(even) {
                border-right: 1px solid #E6E4E4;
            }

            .fl-table tbody td {
                display: block;
                text-align: center;
            }
        }

        .func {
            text-align: center;
            color: #3498DB;
        }

        /* 可以通过修改a标签的样式来控制链接的外观 */
        .func a {
            color: #3498DB;
            text-decoration: none;
        }

        /* 样式表中使用::before与::after伪元素来创建竖线 */
        .func a:before {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }

        .func a:after {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }

        /* 鼠标悬停时，链接的下划线样式 */
        .func a:hover {
            text-decoration: underline;
        }

        .btn {
            background: #3498DB;
            color: #ffffff;
            border-radius: 20px;
            margin: 0 auto;
            border: 2px solid transparent;
            transition: 0.25s;
        }

        .btn:hover {
            background-color: #2980B9;
        }

        /*设置分页超链接的样式*/
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a, .pagination span {
            padding: 8px 12px;
            margin: 0 5px;
            border: 1px solid #ccc;
            text-decoration: none;
            border-radius: 8px;
            color: #333;
        }

        .pagination a:hover {
            background-color: #f5f5f5;
        }

        .pagination .active {
            background-color: #2980B9;
            color: white;
        }

        .pagination .disabled {
            opacity: 0.6;
            cursor: not-allowed;
        }

        .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            justify-content: center;
            padding: 2px;
            margin-right: 10px;
        }

        .form-row label {
            margin-right: 10px;
        }

        .form-row input[type="text"],
        .form-row select {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 200px;
        }

        .form-row input[type="submit"] {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }

        .form-row input[type="button"] {
            padding: 5px 10px;
            background-color: #777777;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }

        .form-row input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-row input[type="button"]:hover {
            background-color: dimgrey;
        }
    </style>

    <title>系统用户管理</title>
    <script>
        document.getElementById("query_identity").selectedIndex = -1;

        function toggleColumn(colIndex) {
            var table = document.getElementsByTagName('table')[0];
            var ths = table.getElementsByTagName('th');
            var tds = table.getElementsByTagName('td');
            var visible = ths[colIndex].getAttribute('data-visible') === 'true';
            for (var i = colIndex; i < tds.length; i += ths.length) {
                tds[i].style.visibility = visible ? 'hidden' : 'visible';
            }
            ths[colIndex].setAttribute('data-visible', !visible);
        }

        function addUser() {
            window.location.href = "UserAdd.html";
        }

        function updateUser(uid) {
            window.location.href = "updateUser?uid=" + uid + "&username=&password=&identityId=&updateState=get";
        }

        function deleteUser(uid) {
            const flag = confirm("确定要删除此用户吗？(uid: " + uid + " )");
            if (flag) window.location.href = "deleteUser?uid=" + uid;
        }

        function userRightSet(uid) {
            window.location.href = "setRight?uid=" + uid;
        }

        function backToMain() {
            window.location.href = "AdminMain.html";
        }

        function logout() {
            const flag = confirm("确定退出当前帐号吗？");
            if (flag) window.location.href = "logout";
        }

        function resetList() {
            window.location.href = "userList";
        }
    </script>
</head>

<body>
<h3>系统用户管理</h3>

<form class="form-row" action="queryUser" method="get" accept-charset="UTF-8">
    <div class="form-row">
        <label>查询: </label>
        <label for="query_uid">uid </label>
        <input type="text" name="uid" id="query_uid">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_username">用户姓名 </label>
        <input type="text" name="username" id="query_username">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_identity">用户身份 </label>
        <select name="identity" id="query_identity">
            <option value="-1" hidden="hidden"></option>
            <option value="0">系统管理员</option>
            <option value="1">学生</option>
            <option value="2">课程主讲教师</option>
            <option value="3">主管教师</option>
        </select>&nbsp;
    </div>

    <div class="form-row">
        <input type="submit" value="查询">&nbsp;&nbsp;
        <input type="button" value="重置" onclick="resetList()">
    </div>
</form>

<div class="func">
    <a onclick="addUser()">新建用户</a>
    <a href="userExport">导出所有(.xls)</a>
    <a onclick="backToMain()">返回管理员主界面</a>
    <a onclick="logout()">退出登录</a>
</div>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>uid</th>
            <th>用户姓名</th>
            <th>密码&nbsp;
                <input type="button" class="btn" value="隐藏" onclick="toggleColumn(3)"></th>
            <th>身份</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="user" varStatus="status">
        <tr>
            <td>${status.index + 1 + (pageNum - 1) * 10}</td>
            <td>${user.uid}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <c:choose>
                <c:when test="${user.identityId == 0}">
                    <td>系统管理员</td>
                </c:when>
                <c:when test="${user.identityId == 1}">
                    <td>学生</td>
                </c:when>
                <c:when test="${user.identityId == 2}">
                    <td>课程主讲教师</td>
                </c:when>
                <c:when test="${user.identityId == 3}">
                    <td>主管教师</td>
                </c:when>
            </c:choose>
            <td><input class="btn" type="button" value="修改信息" onclick="updateUser('${user.uid}')"/> &nbsp;&nbsp;
                <input class="btn" type="button" value="删除" onclick="deleteUser('${user.uid}')">
                <c:if test="${user.identityId == 2 or user.identityId == 3}">
                    &nbsp;&nbsp;<input class="btn" type="button" value="审批权限设置" onclick="userRightSet('${user.uid}')">
                </c:if>
            </td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

<c:if test="${list.isEmpty() or list == null}">
    <h4 align="center">暂无相关用户信息!</h4>
</c:if>

<div class="pagination">
    <%-- 上一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasPreviousPage}">
            <a href="?uid=${uid}&username=${username}&identity=${identity}&pageNum=${pageInfo.prePage}">&lt; 上一页</a>
        </c:when>
        <c:otherwise>
            <span class="disabled">&lt; 上一页</span>
        </c:otherwise>
    </c:choose>

    <%-- 所有可用页码 --%>
    <c:forEach var="i" begin="1" end="${pageInfo.pages}">
        <c:choose>
            <c:when test="${pageInfo.pageNum == i}">
                <span class="active">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="?uid=${uid}&username=${username}&identity=${identity}&pageNum=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- 下一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasNextPage}">
            <a href="?uid=${uid}&username=${username}&identity=${identity}&pageNum=${pageInfo.nextPage}">下一页 &gt;</a>
        </c:when>
        <c:otherwise>
            <span class="disabled">下一页 &gt;</span>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
