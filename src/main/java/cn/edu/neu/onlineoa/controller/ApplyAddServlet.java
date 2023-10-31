package cn.edu.neu.onlineoa.controller;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.service.*;
import cn.edu.neu.onlineoa.utils.DateUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ApplyAddServlet", urlPatterns = "/applyAdd")
@MultipartConfig
public class ApplyAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 判断表单是否带上传文件
        if ( !ServletFileUpload.isMultipartContent(req) )
            return;
        //2. 创建上传文件保存在服务器中的目录
        String uploadPath = "D:\\upload";
        System.out.println("上传文件根目录：" + uploadPath);
        File uploadFile = new File(uploadPath);
        if ( !uploadFile.exists() )
            uploadFile.mkdir();
        //3. 创建磁盘文件库
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //4. 获取servlet文件上传对象
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        //4.1 监控上传进度
        fileUpload.setProgressListener(new ProgressListener() {
            public void update(long l, long l1, int i) {
                System.out.println("文件总大小：" + l1 + " 已上传：" + l);
            }
        });
        //4.2 处理乱码问题
        fileUpload.setHeaderEncoding("UTF-8");
        //4.3 设置单个文件大小的最大值 10MB
        fileUpload.setFileSizeMax(1024 * 1024 * 10);
        //4.4 设置总共能上传的文件大小最大值
        fileUpload.setSizeMax(1024 * 1024 * 10);

        //5.处理上传文件
        try {
            //解析请求，获取所有表单项
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            String studentId = null, courseId = null, reason = null, userFileUploadPath = null, userFileName = null;
            //遍历表单项
            for ( FileItem item : fileItems ) {
                if ( item.isFormField() ) {
                    //处理普通表单
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("UTF-8");
                    System.out.println("name:" + fieldName + ", value: " + fieldValue);

                    switch ( fieldName ) {
                        case "sid":
                            studentId = fieldValue;
                            break;
                        case "cid":
                            courseId = fieldValue;
                            break;
                        case "reason":
                            reason = fieldValue;
                            break;
                    }
                } else {
                    String uploadFileName = item.getName();
                    if ( uploadFileName == null || uploadFileName.trim().equals("") )
                        continue;
                    String fileName = uploadFileName.substring(0, uploadFileName.lastIndexOf("."));
                    System.out.println("原文件名：" + fileName);
                    UUID uuidName = UUID.randomUUID();
                    String realUploadPath = uploadPath + "//" + uuidName;
                    System.out.println("文件保存路径：" + realUploadPath);

                    File realUploadFile = new File(realUploadPath);
                    if ( !realUploadFile.exists() )
                        realUploadFile.mkdir();

                    InputStream inputStream = item.getInputStream();
                    userFileUploadPath = realUploadPath + "//" + uploadFileName;
                    userFileName = "//" + uuidName + "//" + uploadFileName;
                    System.out.println("文件最终路径：" + userFileUploadPath);
                    FileOutputStream outputStream = new FileOutputStream(userFileUploadPath);
                    byte[] buffer = new byte[1024 * 1024];
                    int len = 0;
                    while ( (len = inputStream.read(buffer)) > 0 )
                        outputStream.write(buffer, 0, len);
                    outputStream.close();
                    inputStream.close();
                    item.delete();
                }
            }

            UserService userService = new UserService();
            CourseService courseService = new CourseService();
            ApplyService applyService = new ApplyService();
            ApplyLogService applyLogService = new ApplyLogService();

            if ( studentId != null && courseId != null && reason != null ) {
                User student = userService.findUserByUid(studentId);
                Course course = courseService.findCourseByCid(courseId);

                Apply newApply = new Apply(0, 1, student.getUid(), student.getUsername(),
                        course.getCid(), course.getCname(), reason, null, null, null, false);

                int result = applyService.addApply(newApply);
                if ( result == 1 ) {
                    Apply applyLog = new Apply();
                    applyLog.setAid(newApply.getAid());
                    applyLog.setApplyTime(DateUtils.getLocalDateTime("yyyy-MM-dd HH:mm:ss"));
                    if ( userFileName != null )
                        applyLog.setReasonFilePath(userFileName);
                    applyLogService.insertApplyLog(applyLog);
                    resp.sendRedirect("courseList?type=student");
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("<script language=javascript>alert('不能重复申请同一门课程!');history.back();</script>");
                }
            }
        } catch ( FileUploadException e ) {
            throw new RuntimeException(e);
        }
    }
}
