package com.biz.std.util;

import com.biz.std.model.Subject;
import com.biz.std.vo.Subject.SubjectVo;

import java.util.ArrayList;
import java.util.List;

public class SubjectUtils {

    public static Subject toPo(SubjectVo subjectVo){
        Subject subject = new Subject();
        subject.setId(subjectVo.getId());
        subject.setName(subjectVo.getName());
        return subject;
    }
    public static SubjectVo toVO(Subject subject){
        SubjectVo subjectVo = new SubjectVo();
        subjectVo.setId(subject.getId());
        subjectVo.setName(subject.getName());
        subjectVo.setNum(subject.getScores().size());
        subjectVo.setAvgScore(MathUtils.avgScore(subject.getScores()));
        return subjectVo;
    }

    public static List<SubjectVo> toVoAll(List<Subject> subjects){
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        for (Subject subject: subjects) {
            subjectVos.add(toVO(subject));
        }
        return subjectVos;
    }
}
