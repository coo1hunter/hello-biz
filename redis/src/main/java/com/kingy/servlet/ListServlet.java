package com.kingy.servlet;

import com.kingy.Utils.JedisUtil;
import com.kingy.entity.PageBean;
import com.kingy.entity.Student;
import com.kingy.service.StudentService;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by cool on 2017/7/18.
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService = new StudentService();
        String currPage = req.getParameter("currentPage");
        Jedis jedis = JedisUtil.getJedis();
        Long totalPageCount = jedis.zcard("student");
        // 判断
        if (currPage == null || "".equals(currPage.trim())){
            currPage = "1";      // 第一次访问，设置当前页为1;
        }
        // 转换
        Long currentPage = Long.valueOf(currPage);
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(totalPageCount);
        Long totalPage = pageBean.getTotalPage();
        if (currentPage == totalPage + 1){
            currentPage = 1l;
        }else if (currentPage == 0 ){
            currentPage = totalPage;
        }
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        Set<String> keys = jedis.zrevrange("student",(currentPage-1)*10,9+(currentPage-1)*10);
        List<Student> list = studentService.findAll(keys);
        req.setAttribute("list",list);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
        JedisUtil.returnResource(jedis);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
