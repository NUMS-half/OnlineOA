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
    <title>在线课程申请</title>

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
            padding: 2px;
            margin: 10px;
        }

        hr {
            color: #333333;
            background-color: #333333;
            height: 1px;
            border: none;
            margin: 0;
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

        .func {
            text-align: center;
            color: #3498DB;
        }

        /* 可以通过修改a标签的样式来控制链接的外观 */
        .func a {
            color: #005BBB;
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
            background-color: white;
            color: #333;
        }

        .pagination a:hover {
            background-color: #cccccc;
        }

        .pagination .active {
            background-color: #2980B9;
            color: white;
        }

        .pagination .disabled {
            opacity: 0.6;
            cursor: not-allowed;
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

        .form-row input[type="text"] {
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

        /* 弹窗样式 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #FFF;
            width: 400px;
            max-width: 80%;
            padding: 20px;
            border-radius: 10px;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .modal-content h3 {
            font-size: 18px;
            color: #777;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* 输入框样式 */
        .input-container {
            margin-bottom: 15px;
        }

        .btn-container {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .input-container label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .input-container input[type="text"],
        .input-container input[type="file"] {
            width: 100%;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn-container .submit-btn {
            display: inline-block;
            padding: 8px 20px;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            cursor: pointer;
            background-color: #3498DB;
            color: white;
            border: none;
            border-radius: 8px;
            flex: 0 0 auto;
            margin: 0 20px; /* 调整按钮之间的间距 */
        }

        .btn-container .reset-btn {
            display: inline-block;
            padding: 8px 20px;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            cursor: pointer;
            background-color: #777777;
            color: white;
            border: none;
            border-radius: 8px;
            flex: 0 0 auto;
            margin: 0 5px; /* 调整按钮之间的间距 */
        }

        .btn-container .submit-btn:hover {
            background-color: #2980B9;
        }

        .btn-container .reset-btn:hover {
            background-color: dimgrey;
        }
    </style>
</head>
<body>
<marquee>欢迎学生: <%=request.getSession().getAttribute("username")%> 进入在线课程申请系统，祝您选课顺利! \^o^/</marquee>
<hr/>
<h3>在线课程申请</h3>

<form class="form-row" action="queryCourse" method="get" accept-charset="UTF-8">
    <div class="form-row">
        <label>查询: </label>
        <label for="query_cid"></label>
        <input type="text" name="cid" id="query_cid" placeholder="课程编号">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_cname"></label>
        <input type="text" name="cname" id="query_cname" placeholder="课程名称">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_credit"></label>
        <input type="text" name="credit" id="query_credit" placeholder="学分">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_teaId"></label>
        <input type="text" name="teacherId" id="query_teaId" placeholder="主讲教师ID">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_teaName"></label>
        <input type="text" name="teacherName" id="query_teaName" placeholder="教师姓名">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_time"></label>
        <input type="text" name="takeTime" id="query_time" placeholder="上课时间">&nbsp;
    </div>

    <div class="form-row">
        <label for="query_note"></label>
        <input type="text" name="note" id="query_note" placeholder="备注">&nbsp;
    </div>

    <div class="form-row">
        <input type="submit" value="查询">&nbsp;&nbsp;
        <input type="button" value="重置" onclick="resetList()">
    </div>

    <label for="type"></label>
    <input type="text" value="student" name="type" id="type" hidden="hidden">
</form>

<div class="func">
    <a onclick="showApply('${uid}')">审批进度查询</a>
    <a onclick="showHistory('${uid}')">已确认的申请记录查询</a>
    <a onclick="logout()">退出登录</a>
</div>

<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>学分</th>
            <th>主讲教师</th>
            <th>上课时间</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="course" varStatus="status">
        <tr>
            <td>${status.index + 1 + (pageNum - 1) * 10}</td>
            <td>${course.cid}</td>
            <td>${course.cname}</td>
            <td>${course.credit}</td>
            <td>${course.teacherName}</td>
            <td>${course.takeTime}</td>
            <td>${course.note}</td>
            <td><input class="btn" type="button" value="申请" onclick="openModal('${course.cid}')"/></td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

<c:if test="${list.isEmpty() or list == null}">
    <h4 align="center">暂无相关课程信息!</h4>
</c:if>

<div class="pagination">
    <%-- 上一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasPreviousPage}">
            <a href="?cid=${cid}&cname=${cname}&credit=${credit}&teacherId=${teacherId}&teacherName=${teacherName}&takeTime=${takeTime}&note=${note}&type=student&pageNum=${pageInfo.prePage}">&lt;
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
                <a href="?cid=${cid}&cname=${cname}&credit=${credit}&teacherId=${teacherId}&teacherName=${teacherName}&takeTime=${takeTime}&note=${note}&type=student&pageNum=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- 下一页按钮 --%>
    <c:choose>
        <c:when test="${pageInfo.hasNextPage}">
            <a href="?cid=${cid}&cname=${cname}&credit=${credit}&teacherId=${teacherId}&teacherName=${teacherName}&takeTime=${takeTime}&note=${note}&type=student&pageNum=${pageInfo.nextPage}">下一页
                &gt;</a>
        </c:when>
        <c:otherwise>
            <span class="disabled">下一页 &gt;</span>
        </c:otherwise>
    </c:choose>
</div>


<!-- 弹窗 -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h3>课程申请原因填写</h3>
        <form action="applyAdd" method="post" enctype="multipart/form-data">
            <label for="applyUid"></label>
            <input type="text" id="applyUid" name="sid" value="${uid}" hidden="hidden">
            <label for="applyCid"></label>
            <input type="text" id="applyCid" name="cid" hidden="hidden">

            <div class="input-container">
                <label for="applyReason">申请原因（必填）：</label>
                <input type="text" id="applyReason" name="reason" required>
            </div>
            <div class="input-container">
                <label for="file">上传证明（非必填）：</label>
                <input type="file" id="file" name="file" accept=".jpg, .jpeg, .png, .bmp, .pdf">
            </div>
            <div class="btn-container">
                <button type="submit" class="submit-btn">提交</button>&nbsp;
                <input type="reset" value="重置" class="reset-btn" onclick="resetFileInput()">
            </div>
        </form>
    </div>
</div>

<script>
    var modal = document.getElementById("myModal");
    var apply_cid = document.getElementById("applyCid");

    function openModal(cid) {
        modal.style.display = "block";
        apply_cid.value = cid;
    }

    function closeModal() {
        modal.style.display = "none";
    }

    function resetFileInput() {
        var fileInput = document.getElementById("file");
        fileInput.value = ""; // 重置文件选择
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            closeModal();
        }
    }

    function addApply(stu_id, course_id) {
        var reason = prompt("请填写课程申请原因:");
        if (reason === "") {
            alert("申请原因不能为空！");
        } else if (reason != null) {
            window.location.href = "applyAdd?sid=" + stu_id + "&cid=" + course_id + "&reason=" + reason;
        }
    }

    function showApply(stuId) {
        window.location.href = "applyList?studentId=" + stuId;
    }

    function showHistory(stuId) {
        window.location.href = "applyHistory?uid=" + stuId;
    }

    function logout() {
        const flag = confirm("确定退出当前帐号吗？");
        if (flag) window.location.href = "logout";
    }

    function resetList() {
        window.location.href = "courseList?type=student";
    }
</script>

</body>
</html>
