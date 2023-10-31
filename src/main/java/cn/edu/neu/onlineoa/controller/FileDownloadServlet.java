package cn.edu.neu.onlineoa.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet(name = "FileDownloadServlet" ,urlPatterns = "/fileDownload")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getParameter("filePath");

        String filename = filePath.substring(filePath.lastIndexOf("//") + 2);
        resp.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(filename, "UTF-8"));
        FileInputStream fileInputStream = new FileInputStream("D:\\upload" + filePath);
        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        while(fileInputStream.read(buffer) > 0){
            outputStream.write(buffer);
        }
        fileInputStream.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
