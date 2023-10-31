package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.service.CourseService;
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

@WebServlet(name = "CourseExportServlet", urlPatterns = "/courseExport")
public class CourseExportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "课程信息导出_" + DateUtils.getLocalDateTime("yyyyMMdd_HH-mm-ss");
        String encodedFileName = URLEncoder.encode(filename, "UTF-8");
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName + ".xls");

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("课程");

        CourseService courseService = new CourseService();
        List<Course> courseList = courseService.findAllCourse();

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("课程编号");
        headerRow.createCell(1).setCellValue("课程名称");
        headerRow.createCell(2).setCellValue("学分");
        headerRow.createCell(3).setCellValue("主讲教师ID");
        headerRow.createCell(4).setCellValue("主讲教师姓名");
        headerRow.createCell(5).setCellValue("课时安排");
        headerRow.createCell(6).setCellValue("备注");

        int rowNum = 1;
        for ( Course course : courseList ) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(course.getCid());
            dataRow.createCell(1).setCellValue(course.getCname());
            dataRow.createCell(2).setCellValue(course.getCredit());
            dataRow.createCell(3).setCellValue(course.getTeacherId());
            dataRow.createCell(4).setCellValue(course.getTeacherName());
            dataRow.createCell(5).setCellValue(course.getTakeTime());
            dataRow.createCell(6).setCellValue(course.getNote());
        }

        for ( int i = 0; i < 7; i++ ){
            sheet.autoSizeColumn(i);
        }

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
