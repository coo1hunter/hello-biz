package com.biz.std.vo.Grade;

import java.math.BigDecimal;

public class GradeVo {

    public Long id;

    public String name;

    //班级人数
    public Integer num;

    //平均分
    public BigDecimal avgScore;

    public GradeVo() {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }
}
