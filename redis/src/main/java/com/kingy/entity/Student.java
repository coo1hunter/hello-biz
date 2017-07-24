package com.kingy.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cool on 2017/7/18.
 */
public class Student implements Serializable {
    private String id;
    private String name;
    private Date birthday;
    private String description;
    private int avgscore;

    public Student(String id, String name, String birthday, String description, int avgscore) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        this.id = id;
        this.name = name;
        this.birthday = format.parse(birthday);
        this.description = description;
        this.avgscore = avgscore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        return format.format(birthday);
    }

    public void setBirthday(String birthday) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        this.birthday = format.parse(birthday);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(int avgscore) {
        this.avgscore = avgscore;
    }
}
