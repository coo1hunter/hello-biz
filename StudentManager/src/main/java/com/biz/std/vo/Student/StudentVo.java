package com.biz.std.vo.Student;

import com.biz.std.vo.ScoreVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StudentVo {


    private Long id;

    private String name;

    private String picture;

    private Integer sex;

    private Date birthday;

    private String gradeName;

    private Integer subjectCount;

    private BigDecimal avgScore;

    private Long gradeId;

    //科目及分数等信息集合
    private List<ScoreVo> scoreVoList;

    public void setSubjectCount(Integer subjectCount) {
        this.subjectCount = subjectCount;
    }

    public Integer getSubjectCount() {
        return subjectCount;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

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

    public List<ScoreVo> getScoreVoList() {
        return scoreVoList;
    }

    public void setScoreVoList(List<ScoreVo> scoreVoList) {
        this.scoreVoList = scoreVoList;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }
}
