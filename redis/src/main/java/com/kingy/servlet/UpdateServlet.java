package com.kingy.servlet;

import com.kingy.Utils.JedisUtil;
import com.kingy.Utils.StudentUtil;
import com.kingy.entity.Student;
import com.kingy.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by cool on 2017/7/19.
 */
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService studentService = new StudentService();
        String id = request.getParameter("id");
        String currentPage = request.getParameter("currentPage");
        if (StudentUtil.isInfoEmpty(request)){
                if (StringUtils.isEmpty(id) || StringUtils.isEmpty(currentPage)){
                    response.sendRedirect("error.jsp");
                }else {
                    Student student = studentService.getStudent(id);
                    request.setAttribute("student", student);
                    request.setAttribute("currentPage",currentPage);
                    request.setAttribute("message", "输入信息有误");
                    request.getRequestDispatcher("/update.jsp").forward(request, response);
                }
        }else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            try {
                String birthday = request.getParameter("birthday");
                Date date = format.parse(birthday);
                Student student = StudentUtil.getStudentByParam(request);
                if (student == null){
                    student = studentService.getStudent(id);
                    request.setAttribute("student",student);
                    request.setAttribute("message","存储异常错误");
                    request.setAttribute("currentPage",currentPage);
                    request.getRequestDispatcher("update.jsp").forward(request,response);
                } else {
                    studentService.save(student);
                    request.setAttribute("currentPage",currentPage);
                    request.getRequestDispatcher("/studentmanager").forward(request,response);
                }
            } catch (ParseException e) {
                Student student = studentService.getStudent(id);
                request.setAttribute("currentPage",currentPage);
                request.setAttribute("student",student);
                request.setAttribute("message","日期格式错误");
                request.getRequestDispatcher("update.jsp").forward(request,response);
            }
        }

    }
}
