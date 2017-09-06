package com.biz.std.util;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.vo.Grade.GradeVo;

import java.util.ArrayList;
import java.util.List;

public class GradeUtils {

    public static Grade toPo(GradeVo gradeVo){
        Grade grade = new Grade();
        grade.setId(gradeVo.getId());
        grade.setName(gradeVo.getName());
        return grade;
    }
    public static GradeVo toVo(Grade grade){
        GradeVo gradeVo = new GradeVo();
        gradeVo.setId(grade.getId());
        gradeVo.setName(grade.getName());
        gradeVo.setNum(grade.getStudents().size());
        List<Student> students = grade.getStudents();
        gradeVo.setAvgScore(MathUtils.gradeAvg(students));
        return gradeVo;
    }

    public static List<GradeVo> toVoAll(List<Grade> grades){
        List<GradeVo> gradeVos = new ArrayList<>();
        for (Grade grade:grades) {
            GradeVo gradeVo = toVo(grade);
            gradeVos.add(gradeVo);
        }
        return gradeVos;
    }
}
