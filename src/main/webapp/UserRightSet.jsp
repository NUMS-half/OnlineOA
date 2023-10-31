<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户权限设置</title>

    <script>
        function logout() {
            const flag = confirm("确定退出当前帐号吗？");
            if (flag) window.location.href = "logout";
        }
    </script>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            padding: 20px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 600px;
            margin: 10px auto 0;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        input[type="checkbox"] {
            margin-right: 5px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
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
    </style>

</head>
<body>
<h3 align="center">为教师(ID:${uid})设置审批权限</h3>
<hr/>

<div class="func">
    <a onclick="history.back()">返回</a>
    <a onclick="logout()">退出登录</a>
</div>

<form action="processForm" method="post">
    <table>
        <tr>
            <th>选择</th>
            <th>序号</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>学分</th>
            <th>主讲教师</th>
        </tr>
        <c:forEach var="course" items="${courseList}" varStatus="status">
            <tr>
                <td><input type="checkbox" name="selectedCourses"></td>
                <td>${status.index + 1}</td>
                <td>${course.cid}</td>
                <td>${course.cname}</td>
                <td>${course.credit}</td>
                <td>${course.teacherName}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div align="center" style="margin-top: 10px">
        <input type="submit" value="提交">
    </div>
</form>

<div class="pagination">
    <%-- 上一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasPreviousPage}">
            <a href="?uid=${uid}&pageNum=${pageInfo.prePage}">&lt; 上一页</a>
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
                <a href="?uid=${uid}&pageNum=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- 下一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasNextPage}">
            <a href="?uid=${uid}&pageNum=${pageInfo.nextPage}">下一页 &gt;</a>
        </c:when>
        <c:otherwise>
            <span class="disabled">下一页 &gt;</span>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
