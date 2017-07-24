package com.kingy.servlet;

import com.kingy.entity.PageBean;
import com.kingy.entity.Student;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by cool on 2017/7/18.
 */
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currPage = req.getParameter("currentPage");
        Jedis jedis = new Jedis();
        List<Student> list = new ArrayList<Student>();
        int totalPageCount = (int) (jedis.dbSize()-1);
        // 判断
        if (currPage == null || "".equals(currPage.trim())){
            currPage = "1";      // 第一次访问，设置当前页为1;
        }
        // 转换
        int currentPage = Integer.parseInt(currPage);
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(totalPageCount);
        int totalPage = pageBean.getTotalPage();
        if (currentPage == totalPage + 1){
            currentPage = 1;
        }else if (currentPage == 0 ){
            currentPage = totalPage;
        }
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        Set<String> keys = jedis.zrevrange("student",(currentPage-1)*10,9+(currentPage-1)*10);
        for (String key:keys) {
            Map<String, String> map = jedis.hgetAll(key);
            String name = map.get("name");
            String birthday = map.get("birthday");
            String description = map.get("description");
            int avgscore = Integer.parseInt(map.get("avgscore"));
            Student student = null;
            try {
                student = new Student(key,name,birthday,description,avgscore);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(student);
        }

        req.setAttribute("list",list);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
