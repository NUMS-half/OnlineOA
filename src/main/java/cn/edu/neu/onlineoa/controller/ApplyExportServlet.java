package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.bean.ApplyStatus;
import cn.edu.neu.onlineoa.service.ApplyService;
import cn.edu.neu.onlineoa.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(name = "ApplyExportServlet", urlPatterns = "/applyExport")
public class ApplyExportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置文件类型与文件名
        String filename = "申请审批信息导出_" + DateUtils.getLocalDateTime("yyyyMMdd_HH-mm-ss");
        String encodedFileName = URLEncoder.encode(filename, "UTF-8");
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName + ".xls");

        // 创建Excel工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("已通过的申请");

        // 获取要导出的数据
        ApplyService applyService = new ApplyService();
        List<Apply> applyList = applyService.findAllPassedApply();

        // 创建表头行
        Row headerRow = sheet.createRow(0);
        // 添加表头列
        headerRow.createCell(0).setCellValue("申请ID");
        headerRow.createCell(1).setCellValue("申请状态");
        headerRow.createCell(2).setCellValue("学生ID");
        headerRow.createCell(3).setCellValue("学生姓名");
        headerRow.createCell(4).setCellValue("课程ID");
        headerRow.createCell(5).setCellValue("课程名称");
        headerRow.createCell(6).setCellValue("申请原因");
        headerRow.createCell(7).setCellValue("申请提交时间");
        headerRow.createCell(8).setCellValue("第一审批人ID");
        headerRow.createCell(9).setCellValue("第一次审批时间");
        headerRow.createCell(10).setCellValue("第二审批人ID");
        headerRow.createCell(11).setCellValue("第二次审批时间");
        headerRow.createCell(12).setCellValue("学生是否确认");

        // 填充数据行
        int rowNum = 1;
        for ( Apply apply : applyList ) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(apply.getAid());
            dataRow.createCell(1).setCellValue(ApplyStatus.getNameByIndex(apply.getStatus()));
            dataRow.createCell(2).setCellValue(apply.getStudentId());
            dataRow.createCell(3).setCellValue(apply.getStudentName());
            dataRow.createCell(4).setCellValue(apply.getCourseId());
            dataRow.createCell(5).setCellValue(apply.getCourseName());
            dataRow.createCell(6).setCellValue(apply.getApplyReason());
            dataRow.createCell(7).setCellValue(apply.getApplyTime());
            dataRow.createCell(8).setCellValue(apply.getFirstApproveTeaId() == null ? "无第一审批人" : apply.getFirstApproveTeaId());
            dataRow.createCell(9).setCellValue(apply.getFirstApproveTime());
            dataRow.createCell(10).setCellValue(apply.getSecondApproveTeaId() == null ? "无第二审批人" : apply.getSecondApproveTeaId());
            dataRow.createCell(11).setCellValue(apply.getSecondApproveTime());
            dataRow.createCell(12).setCellValue(apply.isConfirm() ? "是" : "否");
        }

        for ( int i = 0; i < 13; i++ ){
            sheet.autoSizeColumn(i);
        }

        // 将工作簿写入响应输出流中
        OutputStream outputStream = resp.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
