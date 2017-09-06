package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.service.GradeService;
import com.biz.std.vo.Grade.GradeListVo;
import com.biz.std.vo.Grade.GradeVo;
import com.biz.std.vo.PageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @RequestMapping(value = "new")
    public String addGrade(){
        return "grade/new";
    }

    @RequestMapping(value = "save")
    public ModelAndView saveGrade(GradeVo gradeVo){
        String gradeName = gradeVo.getName();
        Grade isExistGrade = gradeService.findGradeByName(gradeName);
        if (isExistGrade != null){
            ModelAndView modelAndView = new ModelAndView("grade/new");
            modelAndView.addObject("error","班级已存在");
            return modelAndView;
        }else {
            gradeService.save(gradeVo);
            return new ModelAndView("redirect:list.do");
        }
    }

    @RequestMapping(value = "list")
    public ModelAndView listGrade(PageReqVo reqVo){
        GradeListVo gradeListVo = gradeService.findAllGrade(reqVo);
        ModelAndView modelAndView = new ModelAndView("grade/list");
        modelAndView.addObject("gradeListVo",gradeListVo);
        return modelAndView;
    }

    @RequestMapping(value = "toUpdate")
    public ModelAndView toUpdate(Long id){
        GradeVo gradeVo = gradeService.findGradeById(id);
        ModelAndView modelAndView = new ModelAndView("grade/update");
        modelAndView.addObject("gradeVo",gradeVo);
        return modelAndView;
    }

    @RequestMapping(value = "update")
    public ModelAndView update(GradeVo gradeVo){
        String gradeName = gradeVo.getName();
        Grade isExistGrade = gradeService.findGradeByName(gradeName);
        if (isExistGrade != null){
            GradeVo existGradeVo = gradeService.findGradeById(gradeVo.getId());
            ModelAndView modelAndView = new ModelAndView("grade/update");
            modelAndView.addObject("updateError","班级已存在");
            modelAndView.addObject("gradeVo",existGradeVo);
            return modelAndView;
        }else {
            gradeService.save(gradeVo);
            return new ModelAndView("redirect:list.do");
        }
    }

    @RequestMapping(value = "delete")
    public String delete(Long id){
        if (id !=null) {
            gradeService.delete(id);
        }
        return "redirect:list.do";
    }
}
