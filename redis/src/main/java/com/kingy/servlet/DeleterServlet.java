package com.kingy.servlet;

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
        Jedis jedis = new Jedis();
        jedis.del(id);
        jedis.zrem("student",id);
        request.setAttribute("id",id);
        request.getRequestDispatcher("/studentmanager").forward(request,response);
    }
}
