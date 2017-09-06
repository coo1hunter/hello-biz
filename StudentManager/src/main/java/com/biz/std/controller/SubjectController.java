package com.biz.std.controller;

import com.biz.std.model.Subject;
import com.biz.std.service.SubjectService;
import com.biz.std.vo.PageReqVo;
import com.biz.std.vo.Subject.SubjectListVo;
import com.biz.std.vo.Subject.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @RequestMapping(value = "new")
    public String addCourse(){
        return "subject/new";
    }

    @RequestMapping(value = "save")
    public ModelAndView saveGrade(SubjectVo subjectVo){
        String subjectName = subjectVo.getName();
        Subject isExistSubject = subjectService.findSubjectByName(subjectName);
        if (isExistSubject != null){
            ModelAndView modelAndView = new ModelAndView("subject/new");
            modelAndView.addObject("error","学科已存在");
            return modelAndView;
        }else {
            subjectService.save(subjectVo);
            return new ModelAndView("redirect:list.do");
        }
    }

    @RequestMapping(value = "list")
    public ModelAndView listSubject(PageReqVo reqVo){
        SubjectListVo subjectListVo = subjectService.findAllSubject(reqVo);
        ModelAndView modelAndView = new ModelAndView("subject/list");
        modelAndView.addObject("subjectListVo",subjectListVo);
        return modelAndView;
    }
    @RequestMapping(value = "toUpdate")
    public ModelAndView toUpdate(Long id){
        SubjectVo subjectVo = subjectService.findSubjectById(id);
        ModelAndView modelAndView = new ModelAndView("subject/update");
        modelAndView.addObject("subjectVo",subjectVo);
        return modelAndView;
    }
    @RequestMapping(value = "update")
    public ModelAndView update(SubjectVo subjectVo){
        String subjectName = subjectVo.getName();
        Subject isExistSubject = subjectService.findSubjectByName(subjectName);
        if (isExistSubject != null){
            SubjectVo existSubjectVo = subjectService.findSubjectById(subjectVo.getId());
            ModelAndView modelAndView = new ModelAndView("subject/update");
            modelAndView.addObject("updateError","学科已存在");
            modelAndView.addObject("subjectVo",existSubjectVo);
            return modelAndView;
        }else {
            subjectService.save(subjectVo);
            return new ModelAndView("redirect:list.do");
        }
    }

    @RequestMapping(value = "delete")
    public String delete(Long id){
        if (id !=null) {
            subjectService.delete(id);
        }
        return "redirect:list.do";
    }
}
