package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.service.ApplyService;
import cn.edu.neu.onlineoa.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StuApplyServlet", urlPatterns = "/applyList")
public class StuApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");

        ApplyService applyService = new ApplyService();
        CourseService courseService = new CourseService();

        List<Apply> allApply = applyService.findApplyByStuId(studentId);
        List<Apply> applyList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();

        for ( Apply apply: allApply ) {
            if( !apply.isConfirm() ) {
                applyList.add(apply);
                courseList.add(courseService.findCourseByCid(apply.getCourseId()));
            }
        }
        req.setAttribute("studentId", studentId);
        req.setAttribute("applyList", applyList);
        req.setAttribute("courseList", courseList);
        req.getRequestDispatcher("StudentApply.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
