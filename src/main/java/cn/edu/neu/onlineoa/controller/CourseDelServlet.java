package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.service.ApprovalFlowService;
import cn.edu.neu.onlineoa.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CourseDelServlet", urlPatterns = "/deleteCourse")
public class CourseDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");

        CourseService courseService = new CourseService();
        ApprovalFlowService approvalFlowService = new ApprovalFlowService();

        int result = courseService.deleteCourseByCid(cid);
        if( result == 1 ){
            approvalFlowService.deleteApprovalFlow(cid);
            resp.sendRedirect("courseList?type=admin");
        }
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('删除失败，已有学生申请此课程，不能删除！');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
