package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.service.ApplyLogService;
import cn.edu.neu.onlineoa.service.ApplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplyDeleteServlet", urlPatterns = "/deleteApply")
public class ApplyDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int targetId = Integer.parseInt(req.getParameter("aid"));
        String studentId = req.getParameter("studentId");

        ApplyService applyService = new ApplyService();
        ApplyLogService applyLogService = new ApplyLogService();

        //需要一并删除apply与apply_log中的数据
        int result1 = applyLogService.deleteApplyLogByAid(targetId);
        int result2 = applyService.deleteApplyByAid(targetId);
        if ( result1 == 1 && result2 == 1 )
            resp.sendRedirect("applyList?studentId=" + studentId);
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('抱歉，删除失败!');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
