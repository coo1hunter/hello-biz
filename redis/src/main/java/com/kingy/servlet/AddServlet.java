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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cool on 2017/7/19.
 */
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService studentService = new StudentService();
        String id = request.getParameter("id");

        if (StudentUtil.isInfoEmpty(request)) {
            request.setAttribute("message", "输入信息有误");
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        }else if (studentService.isIdExist(id))
        {
            request.setAttribute("message", "用户已存在");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            try {
                String birthday = request.getParameter("birthday");
                Date date = format.parse(birthday);
                Student student = StudentUtil.getStudentByParam(request);
                if (student == null){
                    request.setAttribute("message","存储异常错误");
                    request.getRequestDispatcher("add.jsp").forward(request,response);
                }else {
                    studentService.save(student);
                    response.sendRedirect(request.getContextPath() + "/studentmanager");
                }
            } catch (ParseException e) {
                request.setAttribute("message", "日期格式错误");
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
        }

    }
}
