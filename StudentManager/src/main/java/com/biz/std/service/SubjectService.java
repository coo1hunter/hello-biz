package com.biz.std.service;

import com.biz.std.model.Subject;
import com.biz.std.repository.SubjectRepository;
import com.biz.std.util.PageUtils;
import com.biz.std.util.SubjectUtils;
import com.biz.std.vo.PageReqVo;
import com.biz.std.vo.PageVo;
import com.biz.std.vo.Subject.SubjectListVo;
import com.biz.std.vo.Subject.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public void save(SubjectVo subjectVo){
        subjectRepository.save(SubjectUtils.toPo(subjectVo));
    }

    public List<SubjectVo> findAll(){
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectVo> subjectVos = SubjectUtils.toVoAll(subjects);
        return subjectVos;
    }

    public SubjectListVo findAllSubject(PageReqVo reqVo) {
        Page<Subject> subjects = subjectRepository.findAll(getSpecification(), new PageRequest(reqVo.getPageIndex()-1,reqVo.getPageSize()));
        PageVo pageVo = PageUtils.getPageVo(reqVo,subjects);
        List<Subject> subjectList = subjects.getContent();
        List<SubjectVo> subjectVos = SubjectUtils.toVoAll(subjectList);
        SubjectListVo subjectListVo = new SubjectListVo(pageVo,subjectVos);
        return subjectListVo;
    }
    private Specification<Subject> getSpecification (){
        return  (root, query, cb) -> {
            List<Predicate> predicate = new ArrayList<Predicate>();
            return cb.and(predicate.toArray(new Predicate[predicate.size()]));
        };
    }

    public Subject findSubjectByName(String name){
        return  subjectRepository.findSubjectByName(name);
    }

    public SubjectVo findSubjectById(Long id){
        Subject subject = subjectRepository.findSubjectById(id);
        SubjectVo subjectVo = SubjectUtils.toVO(subject);
        return subjectVo;
    }

    public void delete(Long id) {
        subjectRepository.delete(id);
    }
}
