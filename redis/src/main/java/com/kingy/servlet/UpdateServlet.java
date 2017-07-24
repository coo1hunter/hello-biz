package com.kingy.servlet;

import com.kingy.entity.Student;
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
        Jedis jedis = new Jedis();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String description = request.getParameter("description");
        String avgscore = request.getParameter("avgscore");
        if ("".equals(id) || "".equals(name) || "".equals(birthday) || "".equals(description) || "".equals(avgscore)){
            if ("add".equals(request.getParameter("prejsp"))){
                request.setAttribute("message","输入信息有误");
                request.getRequestDispatcher("/add.jsp").forward(request,response);
            }else {
                Map<String, String> map = jedis.hgetAll(id);
                Student student = null;
                try {
                    student = new Student(id,map.get("name"),map.get("birthday"),
                            map.get("description"),Integer.valueOf(map.get("avgscore")));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                request.setAttribute("student",student);
                request.setAttribute("message","输入信息有误");
                request.setAttribute("id",id);
                request.getRequestDispatcher("/update.jsp").forward(request,response);
            }
        }else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            try {
                Date date = format.parse(birthday);
                Boolean isExist = jedis.exists(id);
                if (isExist && "add".equals(request.getParameter("prejsp"))){
                    request.setAttribute("message","用户已存在");
                    request.getRequestDispatcher("add.jsp").forward(request,response);
                }else {
                    jedis.hset(id, "name", name);
                    jedis.hset(id, "birthday", birthday);
                    jedis.hset(id, "description", description);
                    jedis.hset(id, "avgscore", avgscore);
                    jedis.zadd("student", Double.parseDouble(avgscore), id);
                    response.sendRedirect(request.getContextPath()+"/studentmanager");
                }
            } catch (ParseException e) {
                Map<String, String> map = jedis.hgetAll(id);
                Student student = null;
                try {
                    student = new Student(id,map.get("name"),map.get("birthday"),
                            map.get("description"),Integer.valueOf(map.get("avgscore")));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                request.setAttribute("student",student);
                request.setAttribute("message","输入信息有误");
                request.getRequestDispatcher("update.jsp").forward(request,response);
            }
        }

    }
}
