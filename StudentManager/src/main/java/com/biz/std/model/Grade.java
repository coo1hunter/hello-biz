package com.biz.std.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grade",indexes = {@Index(columnList = "id", unique = true)})
public class Grade extends BaseEntity{
    /**
     * 班级名
     */
    @Column(length = 20)
    private String name;
    /**
     * 班级内学生
     */
    @OneToMany(mappedBy = "grade",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<Student>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
