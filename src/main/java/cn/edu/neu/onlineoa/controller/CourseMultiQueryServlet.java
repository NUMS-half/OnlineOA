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

@WebServlet(name = "CourseMultiQueryServlet", urlPatterns = "/queryCourse")
public class CourseMultiQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        String cname = req.getParameter("cname");
        String creditStr = req.getParameter("credit");
        String teacherId = req.getParameter("teacherId");
        String teacherName = req.getParameter("teacherName");
        String takeTime = req.getParameter("takeTime");
        String note = req.getParameter("note");

        String type = req.getParameter("type");

        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        float credit = -1;

        if ( pageNumStr != null && !pageNumStr.isEmpty() )
            pageNum = Integer.parseInt(pageNumStr);
        if ( pageSizeStr != null && !pageSizeStr.isEmpty() )
            pageSize = Integer.parseInt(pageSizeStr);

        try {
            if ( creditStr != null ){
                if ( creditStr.equals("") )
                    credit = -2;
                else
                    credit = Float.parseFloat(creditStr);
            }
        } catch ( NumberFormatException e ) {
            credit = -2;
        }

        if ( !cid.equals("") || !cname.equals("") || (credit != -1 && credit != -2 )
                || !teacherId.equals("") || !teacherName.equals("")
                || !takeTime.equals("") || !note.equals("") ) {
            Course courseQuery = new Course();
            courseQuery.setCid(cid);
            courseQuery.setCname(cname);
            courseQuery.setCredit(credit);
            courseQuery.setTeacherId(teacherId);
            courseQuery.setTeacherName(teacherName);
            courseQuery.setTakeTime(takeTime);
            courseQuery.setNote(note);

            CourseService courseService = new CourseService();
            PageHelper.startPage(pageNum, pageSize);
            List<Course> list = courseService.findCourseWithMultiCondition(courseQuery);
            PageInfo<Course> pageInfo = new PageInfo<>(list);

            req.setAttribute("cid", cid);
            req.setAttribute("cname", cname);
            req.setAttribute("credit", credit);
            req.setAttribute("teacherId", teacherId);
            req.setAttribute("teacherName", teacherName);
            req.setAttribute("takeTime", takeTime);
            req.setAttribute("note", note);
            req.setAttribute("list", list);
            req.setAttribute("pageNum", pageNum);
            req.setAttribute("pageInfo", pageInfo);
        }
        if(type.equals("student"))
            req.getRequestDispatcher("StudentMain.jsp").forward(req, resp);
        else if (type.equals("admin"))
            req.getRequestDispatcher("CourseManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
