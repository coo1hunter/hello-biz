package com.biz.std.vo;

import com.biz.std.vo.Student.StudentVo;

import java.util.List;

public class StuSubVo {

    private StudentVo studentVo;

    private List<ScoreVo> scoreVos;

    public StudentVo getStudentVo() {
        return studentVo;
    }

    public void setStudentVo(StudentVo studentVo) {
        this.studentVo = studentVo;
    }

    public List<ScoreVo> getScoreVos() {
        return scoreVos;
    }

    public void setScoreVos(List<ScoreVo> scoreVos) {
        this.scoreVos = scoreVos;
    }
}
