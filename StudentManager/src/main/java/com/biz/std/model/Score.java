package com.biz.std.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "score",indexes = {@Index(columnList = "id", unique = true)})
public class Score extends BaseEntity{
    /**
     * 学生
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;

    /**
     * 学科
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     * 分数
     */
    @Column(nullable = false)
    private BigDecimal score;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
