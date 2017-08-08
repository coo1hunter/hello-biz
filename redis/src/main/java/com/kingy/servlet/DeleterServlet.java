package com.kingy.servlet;

import com.kingy.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cool on 2017/7/19.
 */
public class DeleterServlet extends HttpServlet {
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
            studentService.delete(id);
            request.setAttribute("id", id);
            request.setAttribute("currentPage",currentPage);
            request.getRequestDispatcher("/studentmanager").forward(request, response);
        }
    }
}
