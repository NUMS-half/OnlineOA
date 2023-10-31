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

@WebServlet(name = "CourseUpdateServlet", urlPatterns = "/updateCourse")
public class CourseUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        String cname = req.getParameter("cname");
        String SCredit = req.getParameter("credit");
        String teacherId = req.getParameter("teacherId");
        String takeTime = req.getParameter("takeTime");
        String note = req.getParameter("note");
        String updateState = req.getParameter("updateState");

        CourseService courseService = new CourseService();
        ApprovalFlowService approvalFlowService = new ApprovalFlowService();
        if ( updateState.equals("get") ) {
            Course course = courseService.findCourseByCid(cid);
            req.setAttribute("cid", course.getCid());
            req.setAttribute("cname", course.getCname());
            req.setAttribute("credit", course.getCredit());
            req.setAttribute("teacherId", course.getTeacherId());
            req.setAttribute("takeTime", course.getTakeTime());
            req.setAttribute("note", course.getNote());
            req.getRequestDispatcher("CourseUpdate.jsp").forward(req, resp);
        } else if ( updateState.equals("set") ) {
            UserService userService = new UserService();
            User teacher = userService.findUserByUid(teacherId);
            try {
                float credit = Float.parseFloat(SCredit);
                Course course = new Course(cid, cname, credit, teacherId, teacher.getUsername(), takeTime, note);
                ApprovalFlow flow = new ApprovalFlow(cid, teacherId, "00210003");

                int result1 = courseService.updateCourse(course);
                int result2 = approvalFlowService.updateApprovalFlow(flow);
                if ( result1 == 1 && result2 == 1 )
                    resp.sendRedirect("courseList?type=admin");
                else {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("<script language=javascript>alert('修改课程失败，请检查输入是否合法!');history.back();</script>");
                }
            } catch ( NumberFormatException e ) {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("<script language=javascript>alert('学分格式不正确，应填写一个整数或小数!');history.back();</script>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
