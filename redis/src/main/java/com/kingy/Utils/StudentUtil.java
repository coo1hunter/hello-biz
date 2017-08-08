package com.kingy.Utils;

import com.kingy.entity.Student;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class StudentUtil {
    public static boolean isInfoEmpty(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String description = request.getParameter("description");
        String avgscore = request.getParameter("avgscore");
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(name) || StringUtils.isEmpty(birthday) ||
                StringUtils.isEmpty(description) || StringUtils.isEmpty(avgscore)){
            return true;
        }
        else {
            return false;
        }
    }

    public static Student getStudentByParam(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String description = request.getParameter("description");
        String avgscore = request.getParameter("avgscore");
        try {
            Student student = new Student(id,name,birthday,description,Integer.valueOf(avgscore));
            return student;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
