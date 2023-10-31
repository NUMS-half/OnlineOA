package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.service.ApplyLogService;
import cn.edu.neu.onlineoa.service.ApplyService;
import cn.edu.neu.onlineoa.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplyUpdateServlet", urlPatterns = "/applyUpdate")
public class ApplyUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int aid = Integer.parseInt(req.getParameter("aid"));
        String statusStr = req.getParameter("status");
        String teacherId = req.getParameter("teacherId");
        String applyReason = req.getParameter("applyReason");
        String rejectReason = req.getParameter("rejectReason");
        String type = req.getParameter("type");

        ApplyService applyService = new ApplyService();
        Apply apply = applyService.findApplyByAid(aid);
        ApplyLogService applyLogService = new ApplyLogService();
        Apply applyLog = applyLogService.findApplyLogByAid(aid);

        int result1 = 0;
        int result2 = -1;
        switch ( type ) {
            case "confirm":
                System.out.println("applyUpdate(id=" + aid + ") : update confirm");
                apply.setConfirm(true);
                break;
            case "applyReason":
                System.out.println("applyUpdate(id=" + aid + ") : update applyReason");
                apply.setApplyReason(applyReason);
                break;
            case "rejectReason":
                System.out.println("applyUpdate(id=" + aid + ") : update rejectReason");
                break;
            case "changeStatus":
                System.out.println("applyUpdate(id=" + aid + ") : update changeStatus");

                int oldStatus = apply.getStatus();
                int newStatus = Integer.parseInt(statusStr);
                //课程主讲教师进行审批
                if( oldStatus == 1 && (newStatus == 2 || newStatus == 4) ) {
                    applyLog.setFirstApproveTeaId(teacherId);
                    applyLog.setFirstApproveTime(DateUtils.getLocalDateTime("yyyy-MM-dd HH:mm:ss"));
                    result2 = applyLogService.updateApplyLog(applyLog);
                }
                //主管教师进行审批
                else if( oldStatus == 2 && (newStatus == 3 || newStatus == 4) ) {
                    applyLog.setSecondApproveTeaId(teacherId);
                    applyLog.setSecondApproveTime(DateUtils.getLocalDateTime("yyyy-MM-dd HH:mm:ss"));
                    result2 = applyLogService.updateApplyLog(applyLog);
                }
                apply.setStatus(newStatus);
                if( rejectReason != null )
                    apply.setRejectReason(rejectReason);
                break;
        }
        result1 = applyService.updateApply(apply);
        if( (result1 == 1 && result2 == -1) || (result1 == 1 && result2 == 1) ) {
            if( type.equals("confirm") || type.equals("applyReason") )
                resp.sendRedirect("applyList?studentId=" + apply.getStudentId());
            else
                resp.sendRedirect("approveList?uid=" + teacherId);
        }
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script language=javascript>alert('修改失败，请重试！');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
