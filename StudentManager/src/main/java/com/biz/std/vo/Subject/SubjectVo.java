package com.biz.std.vo.Subject;

import java.math.BigDecimal;

public class SubjectVo {
    //学科id
    private Long id;

    private String name;

    //选该科人数
    private Integer num;

    private BigDecimal avgScore;

    public SubjectVo() {
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
