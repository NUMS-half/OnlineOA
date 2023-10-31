<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/4
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>审批记录查询</title>

    <script language="JavaScript" type="application/javascript">
        function delApply(aid, stuId) {
            const flag = confirm("确定撤销这此申请吗？(申请ID:" + aid + ")");
            if (flag) window.location.href = "deleteApply?aid=" + aid + "&studentId=" + stuId;
        }

        function updateApply(aid) {
            var reason = prompt("请重新填写课程申请原因:");
            if( reason === "" ) {
                alert("申请原因不能为空！");
            } else if( reason != null ) {
                window.location.href = "applyUpdate?aid=" + aid + "&applyReason=" + reason + "&type=applyReason";
            }
        }

        function confirmApply(aid) {
            const flag = confirm("您要对此申请进行确认吗？");
            if (flag) window.location.href = "applyUpdate?aid=" + aid + "&type=confirm";
        }

        function logout() {
            const flag = confirm("确定退出当前帐号吗？");
            if (flag) window.location.href = "logout";
        }

        function toMainList() {
            window.location.href = "courseList?type=student";
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
            padding: 15px 0;
        }

        /* Table Styles */

        .table-wrapper {
            margin: 10px 70px 70px;
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
            color: #FFD500;
            background: #005BBB;
        }


        .fl-table thead th:nth-child(odd) {
            color: #005BBB;
            background: #FFD500;
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

        /* 样式表中使用::before与::after伪元素来创建竖线 */
        a::before {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }

        a::after {
            content: "";
            display: inline-block;
            border-left: 1px solid black;
            height: 12px;
            margin: 0 10px;
            vertical-align: middle;
        }

        /* 可以通过修改a标签的样式来控制链接的外观 */
        a {
            color: #005BBB;
            text-decoration: none;
        }

        /* 鼠标悬停时，链接的下划线样式 */
        a:hover {
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
            background-color: #005BBB;
        }

        .btn-confirm {
            background: #4CAF50;
            color: #ffffff;
            border-radius: 20px;
            margin: 0 auto;
            border: 2px solid transparent;
            transition: 0.25s;
        }
        .btn-confirm:hover {
            background-color: #45a049;
        }

        .btn-delete {
            background: #ee3e3e;
            color: #ffffff;
            border-radius: 20px;
            margin: 0 auto;
            border: 2px solid transparent;
            transition: 0.25s;
        }
        .btn-delete:hover {
            background-color: #9d2929;
        }

        .btn:disabled{
            background: gray;
            color: #ffffff;
            border-radius: 20px;
            margin: 0 auto;
            border: 2px solid transparent;
            transition: 0.25s;
        }
        .btn:disabled:hover{
            background-color: dimgrey;
        }

    </style>
</head>
<body>
<h3>学生审批进度查询 (ID:${studentId})</h3>
<div align="center">
    <a onclick="toMainList()">返回</a>
    <a onclick="logout()">退出登录</a>
</div>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th></th>
            <th>申请ID</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>学分</th>
            <th>任课教师</th>
            <th>上课时间</th>
            <th>申请原因</th>
            <th>申请状态</th>
            <th>驳回原因</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applyList}" var="apply" varStatus="status1">
        <c:forEach items="${courseList}" var="course" varStatus="status2">
        <tr>
            <c:if test="${course.cid == apply.courseId}">
                <td>${status1.index + 1}</td>
                <td>${apply.aid}</td>
                <td>${course.cid}</td>
                <td>${course.cname}</td>
                <td>${course.credit}</td>
                <td>${course.teacherName}</td>
                <td>${course.takeTime}</td>
                <td>${apply.applyReason}</td>
                <c:choose>
                    <c:when test="${apply.status == 1}">
                        <td style="color: #3498DB">课程主讲教师审批中</td>
                        <td>/</td>
                        <td><input class="btn-delete" type="button" value="撤销"
                                   onclick="delApply('${apply.aid}', '${apply.studentId}')"/> &nbsp;&nbsp;
                            <input class="btn" type="button" value="修改" onclick="updateApply('${apply.aid}')"/></td>
                    </c:when>
                    <c:when test="${apply.status == 2}">
                        <td style="color: #4FC3A1">主管教师审批中</td>
                        <td>/</td>
                        <td><input class="btn-delete" type="button" value="撤销"
                                   onclick="delApply('${apply.aid}', '${apply.studentId}')"/> &nbsp;&nbsp;
                            <input class="btn" type="button" disabled value="修改" onclick="updateApply('${apply.aid}')"/></td>
                    </c:when>
                    <c:when test="${apply.status == 3}">
                        <td style="color: green">申请成功</td>
                        <td>/</td>
                        <td><input class="btn-confirm" type="button" value="确认" onclick="confirmApply('${apply.aid}')"/> &nbsp;&nbsp;
                            <input class="btn-delete" type="button" disabled value="撤销"
                                   onclick="delApply('${apply.aid}', '${apply.studentId}')"/> &nbsp;&nbsp;
                            <input class="btn" type="button" disabled value="修改" onclick="updateApply('${apply.aid}')"/></td>
                    </c:when>
                    <c:when test="${apply.status == 4}">
                        <td style="color: red">申请被驳回</td>
                        <td>${apply.rejectReason}</td>
                        <td><input class="btn-confirm" type="button" value="确认" onclick="confirmApply('${apply.aid}')"/> &nbsp;&nbsp;
                            <input class="btn-delete" type="button" value="删除"
                                   onclick="delApply('${apply.aid}', '${apply.studentId}')"/> &nbsp;&nbsp;
                            <input class="btn" type="button" disabled value="修改" onclick="updateApply('${apply.aid}')"/></td>
                    </c:when>
                </c:choose>
            </c:if>
        </tr>
        </c:forEach>
        </c:forEach>
        <tbody>
    </table>
</div>

<c:if test="${applyList.isEmpty()}">
    <h4 align="center">该学生暂无课程的申请记录!</h4>
</c:if>

</body>
</html>
