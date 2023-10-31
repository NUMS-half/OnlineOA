package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserAddServlet", urlPatterns = "/addUser")
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int identityId = Integer.parseInt(req.getParameter("identityId"));

        User newUser = new User(uid, username, password, identityId);
        UserService userService = new UserService();
        int result = userService.addUser(newUser);
        if ( result == 1 )
            resp.sendRedirect("userList");
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('您输入的ID已经存在，或输入不合法，请重新输入！');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
