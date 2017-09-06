package com.biz.std.vo.Student;

import com.biz.std.model.Grade;

import java.util.List;

public class StudentParamVo {
    private StudentVo studentVo;

    private List<Grade> grades;

    public StudentParamVo(StudentVo studentVo, List<Grade> grades) {
        this.studentVo = studentVo;
        this.grades = grades;
    }

    public StudentVo getStudentVo() {
        return studentVo;
    }

    public void setStudentVo(StudentVo studentVo) {
        this.studentVo = studentVo;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
