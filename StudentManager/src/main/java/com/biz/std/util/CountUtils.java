package com.biz.std.util;

import com.biz.std.vo.ScoreVo;
import com.biz.std.vo.Student.StudentVo;

import java.math.BigDecimal;
import java.util.List;

public class CountUtils {
    public static void setSubjectCount( List<StudentVo> studentVoList){
        for (int i = 0; i <studentVoList.size() ; i++) {
            int size = studentVoList.get(i).getScoreVoList().size();
            studentVoList.get(i).setSubjectCount(size);
        }
    }

    public static void setAvgScore(List<StudentVo> studentVoList){
        for (int i = 0; i <studentVoList.size() ; i++) {
            List<ScoreVo> scoreVoList = studentVoList.get(i).getScoreVoList();
            BigDecimal avgScore = MathUtils.avgScore(ScoreUtils.toPoAll(scoreVoList));
            studentVoList.get(i).setAvgScore(avgScore);
        }
    }
}
