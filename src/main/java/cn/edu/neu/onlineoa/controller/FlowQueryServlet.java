package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.service.ApprovalFlowService;
import cn.edu.neu.onlineoa.service.CourseService;
import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FlowQueryServlet", urlPatterns = "/flowList")
public class FlowQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        CourseService courseService = new CourseService();
        ApprovalFlowService approvalFlowService = new ApprovalFlowService();

        req.setAttribute("teacherList", userService.findAllTeacher());
        req.setAttribute("headTeacherList", userService.findAllHeadTeacher());
        req.setAttribute("courseList", courseService.findAllCourse());
        req.setAttribute("flowList", approvalFlowService.findAllApprovalFlow());

        req.getRequestDispatcher("ApprovalFlowSet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
