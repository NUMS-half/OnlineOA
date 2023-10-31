package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.bean.UserIdentity;
import cn.edu.neu.onlineoa.service.UserService;
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

@WebServlet(name = "UserExportServlet",urlPatterns = "/userExport")
public class UserExportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "用户信息导出_" + DateUtils.getLocalDateTime("yyyyMMdd_HH-mm-ss");
        String encodedFileName = URLEncoder.encode(filename, "UTF-8");
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName + ".xls");

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("用户");

        UserService userService = new UserService();
        List<User> userList = userService.findAllUser();

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("uid");
        headerRow.createCell(1).setCellValue("用户姓名");
        headerRow.createCell(2).setCellValue("用户密码");
        headerRow.createCell(3).setCellValue("用户身份");

        int rowNum = 1;
        for ( User user : userList ) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(user.getUid());
            dataRow.createCell(1).setCellValue(user.getUsername());
            dataRow.createCell(2).setCellValue(user.getPassword());
            dataRow.createCell(3).setCellValue(UserIdentity.getNameByIndex(user.getIdentityId()));
        }

        for ( int i = 0; i < 4; i++ ){
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
