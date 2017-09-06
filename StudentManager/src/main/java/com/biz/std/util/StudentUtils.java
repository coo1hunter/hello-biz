package com.biz.std.util;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.vo.Student.StudentVo;

import java.util.ArrayList;
import java.util.List;

public class StudentUtils {

    public static Student toPo(StudentVo studentVo){
        Student student = new Student();
        student.setId(studentVo.getId());
        student.setName(studentVo.getName());
        student.setSex(studentVo.getSex());
        student.setBirthday(studentVo.getBirthday());
        Grade grade = new Grade();
        grade.setId(studentVo.getGradeId());
        student.setGrade(grade);
        student.setPicture(studentVo.getPicture());
        return student;
    }

    public static StudentVo toVO(Student student){
        StudentVo studentVo = new StudentVo();
        studentVo.setId(student.getId());
        studentVo.setName(student.getName());
        studentVo.setBirthday(student.getBirthday());
        studentVo.setGradeId(student.getId());
        studentVo.setGradeName(student.getGrade().getName());
        studentVo.setPicture(student.getPicture());
        studentVo.setSex(student.getSex());
        studentVo.setScoreVoList(ScoreUtils.toVoAll(student.getScores()));
        return studentVo;
    }

    public static List<StudentVo> toVoAll(List<Student> students){
        List<StudentVo> studentVos = new ArrayList<StudentVo>();
        for (Student student: students) {
            StudentVo studentVo = toVO(student);
            studentVos.add(studentVo);
        }
        return studentVos;
    }
}
