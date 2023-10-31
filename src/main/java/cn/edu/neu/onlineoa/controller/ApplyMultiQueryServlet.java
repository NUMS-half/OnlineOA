package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.service.ApplyService;
import cn.edu.neu.onlineoa.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ApplyMultiQueryServlet", urlPatterns = "/queryApply")
public class ApplyMultiQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aidStr = req.getParameter("aid");
        String statusStr = req.getParameter("status");
        String studentId = req.getParameter("studentId");
        String studentName = req.getParameter("studentName");
        String courseId = req.getParameter("courseId");
        String courseName = req.getParameter("courseName");

        String type = req.getParameter("type");
        String uid = req.getParameter("uid");

        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        ApplyService applyService = new ApplyService();
        UserService userService = new UserService();

        int pageNum = 1;
        int pageSize = 10;
        if ( pageNumStr != null && !pageNumStr.isEmpty() )
            pageNum = Integer.parseInt(pageNumStr);
        if ( pageSizeStr != null && !pageSizeStr.isEmpty() )
            pageSize = Integer.parseInt(pageSizeStr);

        int aid = -1;
        try {
            if ( aidStr != null && !aidStr.equals("") )
                aid = Integer.parseInt(aidStr);
        } catch ( NumberFormatException ignored ) {}

        if ( type.equals("teaApprove") && uid != null && !uid.equals("") ) {
            int identity = userService.findUserByUid(uid).getIdentityId();

            if ( aid != -1 || !studentId.equals("") || !studentName.equals("")
                    || !courseId.equals("") || !courseName.equals("") ) {
                Apply queryApply = new Apply();
                queryApply.setAid(aid);
                queryApply.setStudentId(studentId);
                queryApply.setStudentName(studentName);
                queryApply.setCourseId(courseId);
                queryApply.setCourseName(courseName);

                List<Apply> list;
                if ( identity == 2 ) {
                    queryApply.setStatus(1);
                    PageHelper.startPage(pageNum, pageSize);
                    list = applyService.findApplyWithMultiCondition(queryApply, uid, null);
                } else {
                    queryApply.setStatus(2);
                    PageHelper.startPage(pageNum, pageSize);
                    list = applyService.findApplyWithMultiCondition(queryApply, null, uid);
                }
                PageInfo<Apply> pageInfo = new PageInfo<>(list);
                req.setAttribute("aid", aid);
                req.setAttribute("studentId", studentId);
                req.setAttribute("studentName", studentName);
                req.setAttribute("courseId", courseId);
                req.setAttribute("courseName", courseName);
                req.setAttribute("list", list);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("pageInfo", pageInfo);
            }
            req.setAttribute("teacherId", uid);
            req.setAttribute("identity", identity);
            req.getRequestDispatcher("TeacherMain.jsp").forward(req, resp);
        } else if ( type.equals("teaHistory") && uid != null && !uid.equals("") ) {
            int identity = userService.findUserByUid(uid).getIdentityId();

            int status = -1;
            if ( statusStr != null && !statusStr.equals("") )
                status = Integer.parseInt(statusStr);

            if ( aid != -1 || status != -1 || !studentId.equals("")
                    || !studentName.equals("") || !courseId.equals("") ) {
                Apply queryApply = new Apply();
                queryApply.setAid(aid);
                queryApply.setStatus(status);
                queryApply.setStudentId(studentId);
                queryApply.setStudentName(studentName);
                queryApply.setCourseId(courseId);

                List<Apply> list;
                if ( identity == 2 ) {
                    PageHelper.startPage(pageNum, pageSize);
                    list = applyService.findApplyHistory(uid, 2, queryApply);
                } else {
                    PageHelper.startPage(pageNum, pageSize);
                    list = applyService.findApplyHistory(uid, 3, queryApply);
                }
                PageInfo<Apply> pageInfo = new PageInfo<>(list);

                req.setAttribute("aid", aid);
                req.setAttribute("status", status);
                req.setAttribute("studentId", studentId);
                req.setAttribute("studentName", studentName);
                req.setAttribute("courseId", courseId);
                req.setAttribute("list", list);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("pageInfo", pageInfo);
            }
            req.setAttribute("uid", uid);
            req.setAttribute("identity", identity);
            req.getRequestDispatcher("ApplyHistory.jsp").forward(req, resp);
        } else if( type.equals("adminHistory") && uid != null && uid.equals("adminRequest")) {
            int status = -1;
            if ( statusStr != null && !statusStr.equals("") )
                status = Integer.parseInt(statusStr);

            if ( aid != -1 || status != -1 || !studentId.equals("")
                    || !studentName.equals("") || !courseId.equals("") ) {
                Apply queryApply = new Apply();
                queryApply.setAid(aid);
                queryApply.setStatus(status);
                queryApply.setStudentId(studentId);
                queryApply.setStudentName(studentName);
                queryApply.setCourseId(courseId);

                PageHelper.startPage(pageNum, pageSize);
                List<Apply> list = applyService.findApplyHistory(null, 0, queryApply);
                PageInfo<Apply> pageInfo = new PageInfo<>(list);

                req.setAttribute("aid", aid);
                req.setAttribute("status", status);
                req.setAttribute("studentId", studentId);
                req.setAttribute("studentName", studentName);
                req.setAttribute("courseId", courseId);
                req.setAttribute("list", list);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("pageInfo", pageInfo);
            }
            req.setAttribute("uid", uid);
            req.setAttribute("identity", "0");
            req.getRequestDispatcher("ApplyHistory.jsp").forward(req, resp);
        } else if ( type.equals("stuHistory") && uid != null && !uid.equals("") ) {
            int status = -1;
            if ( statusStr != null && !statusStr.equals("") )
                status = Integer.parseInt(statusStr);

            if ( aid != -1 || status != -1 || !courseId.equals("") ) {
                Apply queryApply = new Apply();
                queryApply.setAid(aid);
                queryApply.setStatus(status);
                queryApply.setCourseId(courseId);

                PageHelper.startPage(pageNum, pageSize);
                List<Apply> list = applyService.findConfirmedApplyWithMultiCondition(queryApply, uid);
                PageInfo<Apply> pageInfo = new PageInfo<>(list);

                req.setAttribute("aid", aid);
                req.setAttribute("status", status);
                req.setAttribute("courseId", courseId);
                req.setAttribute("list", list);
                req.setAttribute("pageNum", pageNum);
                req.setAttribute("pageInfo", pageInfo);
            }
            req.setAttribute("uid", uid);
            req.setAttribute("identity", "1");
            req.getRequestDispatcher("ApplyHistory.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
