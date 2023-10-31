package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserUpdateServlet", urlPatterns = "/updateUser")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String identity = req.getParameter("identityId");
        String updateState = req.getParameter("updateState");

        UserService userService = new UserService();
        if ( updateState.equals("get") ) {
            User user = userService.findUserByUid(uid);
            req.setAttribute("uid", user.getUid());
            req.setAttribute("username", user.getUsername());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("identityId", user.getIdentityId());
            req.getRequestDispatcher("UserUpdate.jsp").forward(req, resp);
        } else if ( updateState.equals("set") ) {
            int identityId = Integer.parseInt(identity);
            User user = new User(uid, username, password, identityId);
            int result = userService.updateUser(user);
            if ( result == 1 )
                resp.sendRedirect("userList");
            else {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("<script language=javascript>alert('修改失败，请检查输入是否合法！');history.back();</script>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
