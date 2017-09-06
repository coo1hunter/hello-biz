package com.biz.std.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student",indexes = {@Index(columnList = "id", unique = true)})
public class Student implements Serializable{
    /**
     * 学号，主键
     */

    @Id
    @Column(nullable = false)
    private Long id;

    /**
     * 姓名
     */
    @Column(length = 20,nullable = false)
    private String name;

    /**
     * 照片
     */
    @Column(length = 255,nullable = false)
    private String picture;

    /**
     * 性别
     */
    @Column(length = 1,nullable = false)
    private Integer sex;

    /**
     * 生日
     */
    @Column(nullable = false)
    private Date birthday;

    /**
     * 所在班级
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gradeId",nullable = false)
    private Grade grade;

    /**
     * 分数
     */
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Score> scores = new ArrayList<Score>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
