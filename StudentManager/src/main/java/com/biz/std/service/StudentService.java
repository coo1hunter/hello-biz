package com.biz.std.service;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.repository.StudentRepository;
import com.biz.std.util.PageUtils;
import com.biz.std.util.ScoreUtils;
import com.biz.std.util.StudentUtils;
import com.biz.std.vo.*;
import com.biz.std.vo.Student.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreService scoreService;

    public void save(StudentVo studentVo){
        studentRepository.save(StudentUtils.toPo(studentVo));
    }

    public StudentListVo findAll(PageReqVo reqVo){
        Page<Student> students = studentRepository.findAll(new Specification<Student>() {
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },new PageRequest(reqVo.getPageIndex()-1,reqVo.getPageSize()));
        PageVo pageVo = PageUtils.getPageVo(reqVo,students);
        List<StudentVo> studentVos = StudentUtils.toVoAll(students.getContent());
        StudentListVo studentListVo = new StudentListVo(pageVo,studentVos);
        return studentListVo;
    }

    public StudentVo findById(Long id){
        Student student = studentRepository.findStudentById(id);
        if (student !=null) {
            StudentVo studentVo = StudentUtils.toVO(student);
            return studentVo;
        }
        return null;
    }

    public Integer saveWithSubject(StuSubVo stuSubVo){
        int duplicate = 0;
        Student student = studentRepository.findOne(stuSubVo.getStudentVo().getId());
        List<ScoreVo> scoreVos = stuSubVo.getScoreVos();
        List<Score> scores  = new ArrayList<Score>();
        for (ScoreVo scoreVo: scoreVos) {
            if (scoreVo.getScore() == null){
                scoreVo.setScore(new BigDecimal(0));
                if (!scoreVo.isChecked()){
                    continue;
                }
            }
            Score score = ScoreUtils.toPo(scoreVo);
            ScoreVo isExistScore = scoreService.findByStuAndSub(student.getId(),scoreVo.getSubjectId());
            if (isExistScore != null ){
                duplicate++;
                score.setId(isExistScore.getId());
            }
            score.setStudent(student);
            scores.add(score);
        }
        student.setScores(scores);
        studentRepository.save(student);
        return duplicate;
    }

    public void delete(Long id){
        studentRepository.delete(id);
    }
}
