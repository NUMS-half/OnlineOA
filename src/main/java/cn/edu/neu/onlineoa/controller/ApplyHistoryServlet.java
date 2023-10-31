package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.bean.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ApplyHistoryServlet", urlPatterns = "/applyHistory")
public class ApplyHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String pageNumStr = req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize");

        int pageNum = 1;
        int pageSize = 10;
        if ( pageNumStr != null && !pageNumStr.isEmpty() )
            pageNum = Integer.parseInt(pageNumStr);
        if ( pageSizeStr != null && !pageSizeStr.isEmpty() )
            pageSize = Integer.parseInt(pageSizeStr);

        int identity = -1;
        if ( uid != null && !uid.equals("") ) {
            if ( uid.equals("adminRequest") )
                identity = 0;
            else {
                UserService userService = new UserService();
                identity = userService.findUserByUid(uid).getIdentityId();
            }
        }

        ApplyService applyService = new ApplyService();
        List<Apply> list;
        switch ( identity ) {
            case 0:
                PageHelper.startPage(pageNum, pageSize);
                list = applyService.findAllApply();
                break;
            case 1:
                PageHelper.startPage(pageNum, pageSize);
                list = applyService.findAllConfirmedApply(uid);
                break;
            case 2:
                PageHelper.startPage(pageNum, pageSize);
                list = applyService.findApplyHistory(uid, 2, null);
                break;
            case 3:
                PageHelper.startPage(pageNum, pageSize);
                list = applyService.findApplyHistory(uid, 3, null);
                break;
            default:
                list = new ArrayList<>();
                break;
        }
        PageInfo<Apply> pageInfo = new PageInfo<>(list);

        req.setAttribute("uid", uid);
        req.setAttribute("list", list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("identity", identity);
        req.getRequestDispatcher("ApplyHistory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
