package com.kingy.service;

import com.kingy.Utils.JedisUtil;
import com.kingy.entity.Student;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.*;

public  class StudentService {

    public List<Student> findAll(Set<String> keys){
        List<Student> students = new ArrayList<Student>();
        for (String key: keys) {
            Student student = getStudent(key);
            students.add(student);
        }
        return students;
    }
    public void save(Student student){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",student.getName());
        map.put("birthday",student.getBirthday());
        map.put("description",student.getDescription());
        map.put("avgscore", String.valueOf(student.getAvgscore()));
        Jedis jedis = JedisUtil.getJedis();
        jedis.hmset(student.getId(),map);
        jedis.zadd("student", Double.parseDouble(String.valueOf(student.getAvgscore())), student.getId());
        JedisUtil.returnResource(jedis);
    }

    public  Student getStudent(String key){
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        String name = map.get("name");
        String birthday = map.get("birthday");
        String description = map.get("description");
        int avgscore = Integer.parseInt(map.get("avgscore"));
        Student student = null;
        try {
            student = new Student(key, name, birthday, description, avgscore);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JedisUtil.returnResource(jedis);
        return student;
    }

    public void delete(String id){
        Jedis jedis = JedisUtil.getJedis();
        jedis.del(id);
        jedis.zrem("student", id);
        JedisUtil.returnResource(jedis);
    }

    public Boolean isIdExist(String id){
        Jedis jedis = JedisUtil.getJedis();
        boolean isExist = jedis.exists(id);
        JedisUtil.returnResource(jedis);
        return isExist;
    }
}
