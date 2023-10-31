package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDelServlet", urlPatterns = "/deleteUser")
public class UserDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");

        UserService userService = new UserService();
        int result = userService.deleteUserByUid(uid);
        if ( result == 1 ) {
            System.out.println("Delete user with uid: " + uid);
            resp.sendRedirect("userList");
        }
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('删除失败!请查询该用户的课程关系等是否解除');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
