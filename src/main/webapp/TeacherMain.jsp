<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/4/12
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>在线审批</title>

    <script>
        function applyApprove(aid, teacherId, status) {
            const flag = confirm("请您再次确定对本申请(申请编号:" + aid + ")进行审批!");
            if (flag) window.location.href = "applyUpdate?aid=" + aid + "&status=" + status
                + "&teacherId=" + teacherId + "&type=changeStatus";
        }

        function applyReject(aid, teacherId) {
            var reason = prompt("请填写驳回申请的原因:");
            if (reason === "") {
                alert("驳回原因不能为空！");
            } else if (reason != null) {
                window.location.href = "applyUpdate?aid=" + aid + "&status=4&teacherId=" + teacherId
                    + "&rejectReason=" + reason + "&type=changeStatus";
            }
        }

        function showHistory(teacherId) {
            window.location.href = "applyHistory?uid=" + teacherId;
        }

        function logout() {
            const flag = confirm("确定退出当前帐号吗？");
            if (flag) window.location.href = "logout";
        }

        function resetList() {
            window.location.href = "approveList?uid=${teacherId}";
        }
    </script>

    <style>        * {
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
    }

    body {
        font-family: Arial;
        -webkit-font-smoothing: antialiased;
        background-image: url("imgs/background2.jpg");
        background-size: 1550px 750px;
    }

    h3 {
        text-align: center;
        font-size: 25px;
        letter-spacing: 1px;
        color: black;
        padding: 2px;
        margin: 10px;
    }

    /* Table Styles */

    .table-wrapper {
        margin: 10px 70px 50px;
        box-shadow: 0px 35px 50px rgba(0, 0, 0, 0.2);
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
        color: #ffffff;
        background: #4FC3A1;
    }


    .fl-table thead th:nth-child(odd) {
        color: #ffffff;
        background: #324960;
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
        border-radius: 15px;
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
        margin-bottom: 5px;
        justify-content: center;
        padding: 2px;
    }

    .form-row label {
        margin-right: 10px;
    }

    .form-row input[type="text"],
    .form-row select {
        text-align: center;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        width: 125px;
        margin-right: 10px;
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
</head>
<body>
<marquee>欢迎老师: ${username} 进入在线审批系统！\^o^/</marquee>
<hr/>
<h3>申请审批</h3>
<form class="form-row" action="queryApply" method="get" accept-charset="UTF-8">
    <div class="form-row">
        <label>查询: </label>
        <label for="query_aid"></label>
        <input type="text" name="aid" id="query_aid" placeholder="申请ID">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_studentId"></label>
        <input type="text" name="studentId" id="query_studentId" placeholder="学生ID">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_studentName"></label>
        <input type="text" name="studentName" id="query_studentName" placeholder="学生姓名">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_courseId"></label>
        <input type="text" name="courseId" id="query_courseId" placeholder="课程编号">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_courseName"></label>
        <input type="text" name="courseName" id="query_courseName" placeholder="课程名称">&nbsp;
    </div>

    <div class="form-row">
        <input type="submit" value="查询">&nbsp;&nbsp;
        <input type="button" value="重置" onclick="resetList()">
    </div>

    <label for="type"></label>
    <input type="text" value="teaApprove" name="type" id="type" hidden="hidden">

    <label for="teacherId"></label>
    <input type="text" value="${teacherId}" name="uid" id="teacherId" hidden="hidden">
</form>

<div class="func">
    <a onclick="showHistory('${uid}')">查看已处理的申请</a>
    <a onclick="logout()">退出登录</a>
</div>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th></th>
            <th>申请编号</th>
            <th>学生ID</th>
            <th>学生姓名</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>申请原因</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="apply" varStatus="status">
        <tr>
            <td>${status.index + 1 + (pageNum - 1) * 10}</td>
            <td>${apply.aid}</td>
            <td>${apply.studentId}</td>
            <td>${apply.studentName}</td>
            <td>${apply.courseId}</td>
            <td>${apply.courseName}</td>
            <td>${apply.applyReason}</td>
            <c:if test="${identity == 2}">
                <td>
                    <c:if test="${apply.reasonFilePath !=null}">
                        <a class="btn" style="text-decoration: none; font-size: 15px; background-color: #4FC3A1" href="fileDownload?filePath=${apply.reasonFilePath}">下载证明</a>&nbsp;
                    </c:if>
                    <input type="button" value="审批" class="btn"
                           onclick="applyApprove('${apply.aid}', '${teacherId}', '2')"/>
                    <input type="button" value="驳回" class="btn" onclick="applyReject('${apply.aid}', '${teacherId}')">
                </td>
            </c:if>
            <c:if test="${identity == 3}">
                <td>
                    <c:if test="${apply.reasonFilePath !=null}">
                        <a class="btn" style="text-decoration: none; font-size: 15px; background-color: #4FC3A1" href="fileDownload?filePath=${apply.reasonFilePath}">下载证明</a>&nbsp;
                    </c:if>
                    <input type="button" value="审批" class="btn"
                           onclick="applyApprove('${apply.aid}', '${teacherId}', '3')"/>
                    <input type="button" value="驳回" class="btn" onclick="applyReject('${apply.aid}', '${teacherId}')">
                </td>
            </c:if>

        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

<c:if test="${list.isEmpty() or list == null}">
    <h4 align="center">暂无相关申请信息!</h4>
</c:if>

<div class="pagination">
    <%-- 上一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasPreviousPage}">
            <a href="?aid=${aid}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&courseName=${courseName}&type=teaApprove&uid=${teacherId}&pageNum=${pageInfo.prePage}">&lt;
                上一页</a>
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
                <a href="?aid=${aid}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&courseName=${courseName}&type=teaApprove&uid=${teacherId}&pageNum=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- 下一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasNextPage}">
            <a href="?aid=${aid}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&courseName=${courseName}&type=teaApprove&uid=${teacherId}&pageNum=${pageInfo.nextPage}">下一页
                &gt;</a>
        </c:when>
        <c:otherwise>
            <span class="disabled">下一页 &gt;</span>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
