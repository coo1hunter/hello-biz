package com.biz.std.service;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.util.ScoreUtils;
import com.biz.std.vo.ScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    public ScoreVo findByStuAndSub(Long studentId, Long subjectId){
        Student student = new Student();
        student.setId(studentId);
        Subject subject = new Subject();
        subject.setId(subjectId);
        Score score = scoreRepository.findScoreByStudentAndSubject(student, subject);
        if (score !=null){
            return ScoreUtils.toVo(score);
        }
        return null;
    }
}
