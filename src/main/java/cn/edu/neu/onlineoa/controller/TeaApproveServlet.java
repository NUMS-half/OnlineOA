package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.service.ApplyLogService;
import cn.edu.neu.onlineoa.service.ApplyService;
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

@WebServlet(name = "TeaApproveServlet", urlPatterns = "/approveList")
public class TeaApproveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("uid");
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        if ( pageNumStr != null && !pageNumStr.isEmpty() )
            pageNum = Integer.parseInt(pageNumStr);
        if ( pageSizeStr != null && !pageSizeStr.isEmpty() )
            pageSize = Integer.parseInt(pageSizeStr);

        UserService userService = new UserService();
        int identity = userService.findUserByUid(teacherId).getIdentityId();
        String username = userService.findUserByUid(teacherId).getUsername();

        ApplyService applyService = new ApplyService();
        ApplyLogService applyLogService = new ApplyLogService();
        List<Apply> applyList = null;
        PageInfo<Apply> pageInfo = null;

        if ( identity == 2 ) {
            PageHelper.startPage(pageNum, pageSize);
            applyList = applyService.findApplyToApproveByTea1Id(teacherId);
            pageInfo = new PageInfo<>(applyList);
        } else if ( identity == 3 ) {
            PageHelper.startPage(pageNum, pageSize);
            applyList = applyService.findApplyToApproveByTea2Id(teacherId);
            pageInfo = new PageInfo<>(applyList);
        }

        if ( applyList != null ) {
            for ( Apply apply : applyList ) {
                Apply log = applyLogService.findApplyLogByAid(apply.getAid());
                if ( log.getReasonFilePath() != null )
                    apply.setReasonFilePath(log.getReasonFilePath());
            }
        }

        req.setAttribute("teacherId", teacherId);
        req.setAttribute("username", username);
        req.setAttribute("list", applyList);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("TeacherMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
