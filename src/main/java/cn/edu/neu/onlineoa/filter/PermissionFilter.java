package cn.edu.neu.onlineoa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class PermissionFilter implements Filter {
    private List<String> adminURIs;
    private List<String> teacherURIs;
    private List<String> headTeacherURIs;
    private List<String> studentURIs;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if ( request.getSession().getAttribute("identity") != null ) {
            int identity = (Integer) request.getSession().getAttribute("identity");
            String URI = request.getRequestURI();

            switch ( identity ) {
                case 0:
                    boolean result1 = false;
                    for ( String uri : adminURIs ) {
                        if ( URI.endsWith(uri) ) {
                            result1 = true;
                            break;
                        }
                    }
                    if ( result1 )
                        chain.doFilter(req, resp);
                    else if ( request.getRequestURI().endsWith("/") ||
                            request.getRequestURI().endsWith("/Login.jsp") ||
                            request.getRequestURI().endsWith("/login") ||
                            request.getRequestURI().endsWith("/LoginOverdue.html") ||
                            request.getRequestURI().endsWith("/SignUp.html") ||
                            request.getRequestURI().endsWith("/PermissionFailure.html") ) {
                        response.sendRedirect("AdminMain.html");
                    }
                    else {
                        response.setCharacterEncoding("UTF-8");
                        response.sendRedirect("PermissionFailure.html");
                    }
                    break;
                case 1:
                    boolean result2 = false;
                    for ( String uri : studentURIs ) {
                        if ( URI.endsWith(uri) ) {
                            result2 = true;
                            break;
                        }
                    }
                    if ( result2 )
                        chain.doFilter(req, resp);
                    else if ( request.getRequestURI().endsWith("/") ||
                            request.getRequestURI().endsWith("/Login.jsp") ||
                            request.getRequestURI().endsWith("/login") ||
                            request.getRequestURI().endsWith("/LoginOverdue.html") ||
                            request.getRequestURI().endsWith("/SignUp.html") ||
                            request.getRequestURI().endsWith("/PermissionFailure.html") ) {
                        response.sendRedirect("courseList?type=student");
                    }
                    else {
                        response.setCharacterEncoding("UTF-8");
                        response.sendRedirect("PermissionFailure.html");
                    }
                    break;
                case 2:
                    boolean result3 = false;
                    for ( String uri : teacherURIs ) {
                        if ( URI.endsWith(uri) ) {
                            result3 = true;
                            break;
                        }
                    }
                    if ( result3 )
                        chain.doFilter(req, resp);
                    else if ( request.getRequestURI().endsWith("/") ||
                            request.getRequestURI().endsWith("/Login.jsp") ||
                            request.getRequestURI().endsWith("/login") ||
                            request.getRequestURI().endsWith("/LoginOverdue.html") ||
                            request.getRequestURI().endsWith("/SignUp.html") ||
                            request.getRequestURI().endsWith("/PermissionFailure.html") ) {
                        response.sendRedirect("approveList?uid=" + request.getSession().getAttribute("uid"));
                    }
                    else {
                        response.setCharacterEncoding("UTF-8");
                        response.sendRedirect("PermissionFailure.html");
                    }
                    break;
                case 3:
                    boolean result4 = false;
                    for ( String uri : headTeacherURIs ) {
                        if ( URI.endsWith(uri) ) {
                            result4 = true;
                            break;
                        }
                    }
                    if ( result4 )
                        chain.doFilter(req, resp);
                    else if ( request.getRequestURI().endsWith("/") ||
                            request.getRequestURI().endsWith("/Login.jsp") ||
                            request.getRequestURI().endsWith("/login") ||
                            request.getRequestURI().endsWith("/LoginOverdue.html") ||
                            request.getRequestURI().endsWith("/SignUp.html") ||
                            request.getRequestURI().endsWith("/PermissionFailure.html") ) {
                        response.sendRedirect("approveList?uid=" + request.getSession().getAttribute("uid"));
                    }
                    else {
                        response.setCharacterEncoding("UTF-8");
                        response.sendRedirect("PermissionFailure.html");
                    }
                    break;
            }
        } else if ( request.getRequestURI().endsWith("/") ||
                request.getRequestURI().endsWith("/Login.jsp") ||
                request.getRequestURI().endsWith("/login") ||
                request.getRequestURI().endsWith("/LoginOverdue.html") ||
                request.getRequestURI().endsWith("/SignUp.html") ||
                request.getRequestURI().endsWith("/imgs/background.jpg") ||
                request.getRequestURI().endsWith("/PermissionFailure.html")) {
            chain.doFilter(req, resp);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect("PermissionFailure.html");
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminURIs = new ArrayList<>();
        teacherURIs = new ArrayList<>();
        headTeacherURIs = new ArrayList<>();
        studentURIs = new ArrayList<>();

        adminURIs.add("/applyExport");
        adminURIs.add("/applyHistory");
        adminURIs.add("/queryApply");
        adminURIs.add("/addCourse");
        adminURIs.add("/deleteCourse");
        adminURIs.add("/courseExport");
        adminURIs.add("/queryCourse");
        adminURIs.add("/courseList");
        adminURIs.add("/updateCourse");
        adminURIs.add("/flowList");
        adminURIs.add("/updateFlow");
        adminURIs.add("/addUser");
        adminURIs.add("/deleteUser");
        adminURIs.add("/userExport");
        adminURIs.add("/queryUser");
        adminURIs.add("/userList");
        adminURIs.add("/updateUser");
        adminURIs.add("/AdminMain.html");
        adminURIs.add("/ApplyHistory.jsp");
        adminURIs.add("/ApprovalFlowSet.jsp");
        adminURIs.add("/CourseAdd.html");
        adminURIs.add("/CourseManagement.jsp");
        adminURIs.add("/CourseUpdate.jsp");
        adminURIs.add("/UserAdd.html");
        adminURIs.add("/UserManagement.jsp");
        adminURIs.add("/UserUpdate.jsp");
        adminURIs.add("/setRight");
        adminURIs.add("/UserRightSet.jsp");
        adminURIs.add("/logout");

        teacherURIs.add("/applyHistory");
        teacherURIs.add("/queryApply");
        teacherURIs.add("/applyUpdate");
        teacherURIs.add("/fileDownload");
        teacherURIs.add("/approveList");
        teacherURIs.add("/ApplyHistory.jsp");
        teacherURIs.add("/TeacherMain.jsp");
        teacherURIs.add("/logout");
        teacherURIs.add("/imgs/background2.jpg");

        headTeacherURIs.add("/applyHistory");
        headTeacherURIs.add("/queryApply");
        headTeacherURIs.add("/applyUpdate");
        headTeacherURIs.add("/fileDownload");
        headTeacherURIs.add("/approveList");
        headTeacherURIs.add("/ApplyHistory.jsp");
        headTeacherURIs.add("/TeacherMain.jsp");
        headTeacherURIs.add("/logout");
        headTeacherURIs.add("/imgs/background2.jpg");

        studentURIs.add("/applyAdd");
        studentURIs.add("/deleteApply");
        studentURIs.add("/applyHistory");
        studentURIs.add("/queryApply");
        studentURIs.add("/applyUpdate");
        studentURIs.add("/queryCourse");
        studentURIs.add("/courseList");
        studentURIs.add("/applyList");
        studentURIs.add("/ApplyHistory.jsp");
        studentURIs.add("/StudentApply.jsp");
        studentURIs.add("/StudentMain.jsp");
        studentURIs.add("/logout");
    }
}
