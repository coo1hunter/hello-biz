package com.kingy.servlet;

import com.kingy.entity.Student;
import com.kingy.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by cool on 2017/7/19.
 */
public class AlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("param");
        String currentPage = request.getParameter("currentPage");
        StudentService studentService = new StudentService();
       if (StringUtils.isEmpty(id) || StringUtils.isEmpty(currentPage)){
           response.sendRedirect("error.jsp");
       }else {
           Student student = studentService.getStudent(id);
           request.setAttribute("student", student);
           request.setAttribute("currentPage",currentPage);
           request.getRequestDispatcher("update.jsp").forward(request, response);
       }
    }
}
