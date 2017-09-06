package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.repository.GradeRepository;
import com.biz.std.util.GradeUtils;
import com.biz.std.util.PageUtils;
import com.biz.std.vo.Grade.GradeListVo;
import com.biz.std.vo.Grade.GradeVo;
import com.biz.std.vo.PageReqVo;
import com.biz.std.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public void save(GradeVo gradeVo){
        gradeRepository.save(GradeUtils.toPo(gradeVo));
    }
    public GradeListVo findAllGrade(PageReqVo reqVo) {
        Page<Grade> grades = gradeRepository.findAll(getSpecification(), new PageRequest(reqVo.getPageIndex()-1,reqVo.getPageSize()));
        PageVo pageVo = PageUtils.getPageVo(reqVo,grades);
        List<Grade> gradeList = grades.getContent();
        List<GradeVo> gradeVos = GradeUtils.toVoAll(gradeList);
        GradeListVo gradeListVo = new GradeListVo(pageVo,gradeVos);
        return gradeListVo;
    }
    private Specification<Grade> getSpecification (){
        return  (root, query, cb) -> {
            List<Predicate> predicate = new ArrayList<Predicate>();
            return cb.and(predicate.toArray(new Predicate[predicate.size()]));
        };
    }

    public List<Grade> finAll(){
        return gradeRepository.findAll();
    }

    public GradeVo findGradeById(Long id){
        Grade grade = gradeRepository.findGradeById(id);
        GradeVo gradeVo = GradeUtils.toVo(grade);
        return gradeVo;
    }

    public Grade findGradeByName(String name){
        Grade grade = gradeRepository.findGradeByName(name);
        return grade;
    }

    public void delete(Long id){
        gradeRepository.delete(id);
    }
}
