<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2023/5/13
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>审批历史查询</title>
    <script>
        document.getElementById("query_status").selectedIndex = -1;

        function backToMain() {
            window.location.href = "AdminMain.html";
        }

        function resetList(uid) {
            window.location.href = "applyHistory?uid=" + uid;
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
        }

        h3 {
            text-align: center;
            font-size: 25px;
            /*text-transform: uppercase;*/
            letter-spacing: 1px;
            color: black;
            padding: 10px 0;
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
            color: #EBEBEB;
            background: #005BBB;
        }


        .fl-table thead th:nth-child(odd) {
            color: #EBEBEB;
            background: #005BBB;
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

        /* 可以通过修改a标签的样式来控制链接的外观 */
        .func a {
            color: #3498DB;
            text-decoration: none;
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
<h3>审批记录查询</h3>
<hr/>

<form class="form-row" action="queryApply" method="get" accept-charset="UTF-8">
    <div class="form-row">
        <label>查询: </label>
        <label for="query_aid"></label>
        <input type="text" name="aid" id="query_aid" placeholder="申请ID">&nbsp;
    </div>

    <c:if test="${identity != 1}">
        <div class="form-row">
            <label for="query_studentId"></label>
            <input type="text" name="studentId" id="query_studentId" placeholder="学生ID">&nbsp;
        </div>

        <div class="form-row">
            <label for="query_studentName"></label>
            <input type="text" name="studentName" id="query_studentName" placeholder="学生姓名">&nbsp;
        </div>
    </c:if>

    <div class="form-row">
        <label for="query_courseId"></label>
        <input type="text" name="courseId" id="query_courseId" placeholder="课程编号">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_status">审批状态</label>
        <select name="status" id="query_status">
            <option value="-1" hidden="hidden"></option>
            <option value="0">申请已提交</option>
            <option value="1">课程主讲教师审批中</option>
            <option value="2">主管教师审批中</option>
            <option value="3">申请成功</option>
            <option value="4">申请被驳回</option>
        </select>
    </div>

    <div class="form-row">
        <input type="submit" value="查询">&nbsp;&nbsp;
        <input type="button" value="重置" onclick="resetList('${uid}')">
    </div>

    <c:choose>
        <c:when test="${identity == 0}">
            <label for="type"></label>
            <input type="text" value="adminHistory" name="type" id="type" hidden="hidden">
        </c:when>
        <c:when test="${identity == 1}">
            <label for="type"></label>
            <input type="text" value="stuHistory" name="type" id="type" hidden="hidden">
        </c:when>
        <c:otherwise>
            <label for="type"></label>
            <input type="text" value="teaHistory" name="type" id="type" hidden="hidden">
        </c:otherwise>
    </c:choose>

    <label for="uid"></label>
    <input type="text" value="${uid}" name="uid" id="uid" hidden="hidden">
</form>

<div class="func">
    <c:if test="${identity == 0}">
        <a href="applyExport">导出已通过记录(.xls)</a>
        <a onclick="backToMain()">返回管理员主界面</a>
    </c:if>

    <c:if test="${identity == 1 or identity == 2 or identity == 3}">
        <a onclick="history.back()">返回</a>
    </c:if>
</div>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th></th>
            <th>编号</th>
            <th>学生ID</th>
            <th>学生姓名</th>
            <th>课程编号</th>
            <th>审批人①</th>
            <th>审批人②</th>
            <th>审批结果</th>
            <th>申请原因</th>
            <th>驳回原因</th>
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
            <td>${apply.firstApproveTeaId}</td>
            <td>${apply.secondApproveTeaId}</td>
            <c:choose>
                <c:when test="${apply.status == 1}">
                    <td style="color: #3498DB">课程主讲教师审批中</td>
                </c:when>
                <c:when test="${apply.status == 2}">
                    <td style="color: #4FC3A1">主管教师审批中</td>
                </c:when>
                <c:when test="${apply.status == 3}">
                    <td style="color: green">审批已通过</td>
                </c:when>
                <c:when test="${apply.status == 4}">
                    <td style="color: red">审批被驳回</td>
                </c:when>
            </c:choose>
            <td>${apply.applyReason}</td>
            <td>${apply.rejectReason}</td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

<c:if test="${list.isEmpty() or list == null}">
    <h4 align="center">暂无相关审批记录!</h4>
</c:if>

<c:if test="${identity == 2 or identity == 3}">
    <div class="pagination">
            <%-- 上一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasPreviousPage}">
                <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&uid=${uid}&type=teaHistory&pageNum=${pageInfo.prePage}">&lt;上一页</a>
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
                    <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&type=teaHistory&uid=${uid}&pageNum=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

            <%-- 下一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasNextPage}">
                <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&type=teaHistory&uid=${uid}&pageNum=${pageInfo.nextPage}">下一页&gt;</a>
            </c:when>
            <c:otherwise>
                <span class="disabled">下一页 &gt;</span>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<c:if test="${identity == 0}">
    <div class="pagination">
            <%-- 上一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasPreviousPage}">
                <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&uid=${uid}&type=adminHistory&pageNum=${pageInfo.prePage}">&lt;上一页</a>
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
                    <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&type=adminHistory&uid=${uid}&pageNum=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

            <%-- 下一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasNextPage}">
                <a href="?aid=${aid}&status=${status}&studentId=${studentId}&studentName=${studentName}&courseId=${courseId}&type=adminHistory&uid=${uid}&pageNum=${pageInfo.nextPage}">下一页&gt;</a>
            </c:when>
            <c:otherwise>
                <span class="disabled">下一页 &gt;</span>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<c:if test="${identity == 1}">
    <div class="pagination">
            <%-- 上一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasPreviousPage}">
                <a href="?aid=${aid}&status=${status}&courseId=${courseId}&uid=${uid}&type=stuHistory&pageNum=${pageInfo.prePage}">&lt;上一页</a>
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
                    <a href="?aid=${aid}&status=${status}&courseId=${courseId}&type=stuHistory&uid=${uid}&pageNum=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

            <%-- 下一页按钮 --%>
        <c:choose>
            <c:when test="${pageInfo.hasNextPage}">
                <a href="?aid=${aid}&status=${status}&courseId=${courseId}&type=stuHistory&uid=${uid}&pageNum=${pageInfo.nextPage}">下一页&gt;</a>
            </c:when>
            <c:otherwise>
                <span class="disabled">下一页 &gt;</span>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

</body>
</html>
