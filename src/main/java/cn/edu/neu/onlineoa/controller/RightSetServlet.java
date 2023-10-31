package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.CourseService;
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

@WebServlet(name = "RightSetServlet", urlPatterns = "/setRight")
public class RightSetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        if (pageNumStr != null && !pageNumStr.isEmpty())
            pageNum = Integer.parseInt(pageNumStr);
        if (pageSizeStr != null && !pageSizeStr.isEmpty())
            pageSize = Integer.parseInt(pageSizeStr);

        CourseService courseService = new CourseService();
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseService.findAllCourse();
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);

        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("uid", uid);
        req.setAttribute("courseList" ,courseList);
        req.getRequestDispatcher("UserRightSet.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
