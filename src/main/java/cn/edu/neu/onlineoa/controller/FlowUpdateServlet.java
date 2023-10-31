package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.ApprovalFlow;
import cn.edu.neu.onlineoa.service.ApprovalFlowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FlowUpdateServlet", urlPatterns = "/updateFlow")
public class FlowUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        String firstTeacherId = req.getParameter("tea1");
        String secondTeacherId = req.getParameter("tea2");

        ApprovalFlowService approvalFlowService = new ApprovalFlowService();
        ApprovalFlow flow = new ApprovalFlow(cid, firstTeacherId, secondTeacherId);

        int result = approvalFlowService.updateApprovalFlow(flow);

        if(result == 1)
            resp.sendRedirect("flowList");
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('修改失败！');history.back();</script>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
