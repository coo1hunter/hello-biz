package com.biz.std.util;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.vo.ScoreVo;

import java.util.ArrayList;
import java.util.List;

public class ScoreUtils {
    public static Score toPo(ScoreVo scoreVo){
        Score score = new Score();
        score.setId(scoreVo.getId());
        score.setScore(scoreVo.getScore());
        Student student = new Student();
        student.setId(scoreVo.getStudentId());
        Subject subject = new Subject();
        subject.setId(scoreVo.getSubjectId());
        score.setStudent(student);
        score.setSubject(subject);
        return score;
    }

    public static ScoreVo toVo(Score score){
        ScoreVo scoreVo = new ScoreVo();
        scoreVo.setId(score.getId());
        scoreVo.setScore(score.getScore());
        scoreVo.setStudentId(score.getStudent().getId());
        scoreVo.setStudentName(score.getStudent().getName());
        scoreVo.setSubjectId(score.getSubject().getId());
        scoreVo.setSubjectName(score.getSubject().getName());
        return scoreVo;
    }

    public static List<ScoreVo> toVoAll(List<Score> scores){
        List<ScoreVo> scoreVos = new ArrayList<ScoreVo>();
        for (Score score: scores) {
            scoreVos.add(toVo(score));
        }
        return scoreVos;
    }
    public static List<Score> toPoAll(List<ScoreVo> scoreVos){
        List<Score> scores = new ArrayList<Score>();
        for (ScoreVo scoreVo: scoreVos) {
            scores.add(toPo(scoreVo));
        }
        return scores;
    }
}
