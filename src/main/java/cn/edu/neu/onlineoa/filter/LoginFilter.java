package cn.edu.neu.onlineoa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if ( request.getSession().getAttribute("uid") != null &&
                request.getSession().getAttribute("login").equals("true") ) {
            chain.doFilter(req, resp);
        } else if ( request.getRequestURI().endsWith("/") ||
                request.getRequestURI().endsWith("/Login.jsp") ||
                request.getRequestURI().endsWith("/login") ||
                request.getRequestURI().endsWith("/LoginOverdue.html") ||
                request.getRequestURI().endsWith("/SignUp.html") ||
                request.getRequestURI().endsWith("/imgs/background.jpg") ) {
            chain.doFilter(req, resp);
        } else
            response.sendRedirect("LoginOverdue.html");
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
