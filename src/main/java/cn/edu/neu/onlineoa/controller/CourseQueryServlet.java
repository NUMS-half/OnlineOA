package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseQueryServlet", urlPatterns = "/courseList")
public class CourseQueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        if (pageNumStr != null && !pageNumStr.isEmpty())
            pageNum = Integer.parseInt(pageNumStr);
        if (pageSizeStr != null && !pageSizeStr.isEmpty())
            pageSize = Integer.parseInt(pageSizeStr);

        //获取所有的课程并存入List
        CourseService courseService = new CourseService();
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseService.findAllCourse();
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        //将List写入请求并转发
        req.setAttribute("list", list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageInfo", pageInfo);
        if ( type.equals("student") )
            req.getRequestDispatcher("StudentMain.jsp").forward(req, resp);
        else if ( type.equals("admin") )
            req.getRequestDispatcher("CourseManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
