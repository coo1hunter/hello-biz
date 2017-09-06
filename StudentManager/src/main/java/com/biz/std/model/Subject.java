package com.biz.std.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject",indexes = {@Index(columnList = "id",unique = true)})
public class Subject extends BaseEntity{
    /**
     * 学科名
     */
    @Column(length = 20,nullable = false)
    private String name;
    /**
     * 分数
     */
    @OneToMany(mappedBy = "subject",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Score> scores = new ArrayList<Score>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
