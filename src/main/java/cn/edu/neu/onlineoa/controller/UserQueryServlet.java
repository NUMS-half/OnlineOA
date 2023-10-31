package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
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

@WebServlet(name = "UserQueryServlet", urlPatterns = "/userList")
public class UserQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        if (pageNumStr != null && !pageNumStr.isEmpty())
            pageNum = Integer.parseInt(pageNumStr);
        if (pageSizeStr != null && !pageSizeStr.isEmpty())
            pageSize = Integer.parseInt(pageSizeStr);

        UserService userService = new UserService();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(list);

        req.setAttribute("list", list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("UserManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
