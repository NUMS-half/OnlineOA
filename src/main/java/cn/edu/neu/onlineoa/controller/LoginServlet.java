package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        List<User> userList = userService.findAllUser();
        boolean result = false;
        for ( User user : userList ) {
            if ( uid.equals(user.getUid()) && password.equals(user.getPassword()) ) {
                result = true;
                request.getSession().setAttribute("uid", user.getUid());
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("identity", user.getIdentityId());
                request.getSession().setAttribute("login", "true");

                switch ( user.getIdentityId() ) {
                    case 0:  //系统管理员登录
                        response.sendRedirect("AdminMain.html");
                        break;
                    case 1:  //学生登录
                        response.sendRedirect("courseList?type=student");
                        break;
                    case 2:  //课程主讲教师登录
                    case 3:  //主管教师登录
                        response.sendRedirect("approveList?uid=" + user.getUid());
                        break;
                }
                break;
            }
        }
        if ( !result ){
            request.setAttribute("login", "false");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
