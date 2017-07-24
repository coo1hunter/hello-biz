package com.kingy.servlet;

import com.kingy.entity.Student;
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
        Jedis jedis = new Jedis();
        String id = request.getParameter("param");
        Map<String, String> map = jedis.hgetAll(id);
        String name = map.get("name");
        String birthday = map.get("birthday");
        String description = map.get("description");
        int avgscore = Integer.parseInt(map.get("avgscore"));
        Student student = null;
        try {
            student = new Student(id,name,birthday,description,avgscore);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute("student",student);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}
