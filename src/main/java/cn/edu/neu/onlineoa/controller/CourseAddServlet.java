package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.ApprovalFlow;
import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.ApprovalFlowService;
import cn.edu.neu.onlineoa.service.CourseService;
import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CourseAddServlet", urlPatterns = "/addCourse")
public class CourseAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String newCid = req.getParameter("cid");
            String newCname = req.getParameter("courseName");
            float credit = Float.parseFloat(req.getParameter("credit"));
            String teacherId = req.getParameter("teacherId");
            String takeTime = req.getParameter("takeTime");
            String note = req.getParameter("note");

            UserService userService = new UserService();
            CourseService courseService = new CourseService();
            ApprovalFlowService approvalFlowService = new ApprovalFlowService();

            User teacher = userService.findUserByUid(teacherId);
            if( teacher == null ) {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("<script language=javascript>alert('系统中没有ID为" + teacherId + "的主讲教师!');history.back();</script>");
            } else if ( teacher.getIdentityId() == 3 ){
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("<script language=javascript>alert('主管老师不能安排课程!');history.back();</script>");
            } else {
                Course newCourse = new Course(newCid, newCname, credit, teacherId, teacher.getUsername(), takeTime, note);
                ApprovalFlow newFlow = new ApprovalFlow(newCid, teacherId, "00210003"); //默认审批流，第二审批人为00210003

                int result1 = courseService.addCourse(newCourse);
                int result2 = approvalFlowService.addApprovalFlow(newFlow);
                if ( result1 == 1 && result2 == 1 )
                    resp.sendRedirect("courseList?type=admin");
                else{
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("<script language=javascript>alert('新建课程失败，课程编号已存在或输入不符合要求!');history.back();</script>");
                }
            }
        } catch ( NumberFormatException e ) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('学分格式不正确，应填写一个整数或小数!');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
