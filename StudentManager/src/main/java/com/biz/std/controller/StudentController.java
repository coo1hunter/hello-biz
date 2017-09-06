package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.service.GradeService;
import com.biz.std.service.StudentService;
import com.biz.std.service.SubjectService;
import com.biz.std.util.CountUtils;
import com.biz.std.util.ImageUploadUtils;
import com.biz.std.vo.*;
import com.biz.std.vo.Student.StudentParamVo;
import com.biz.std.vo.Student.StudentVo;
import com.biz.std.vo.Subject.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;


    @Autowired
    private GradeService gradeService;
    @RequestMapping(value = "imageUpLoad")
    @ResponseBody
    public String imageUpLoad(HttpServletRequest request){
        List<String> fileNames = ImageUploadUtils.upload(request);
        if (fileNames !=null && fileNames.size()>0){
            return fileNames.get(0);
        }
        return null;
    }

    @RequestMapping(value = "save")
    public ModelAndView save(StudentVo studentVo){
        StudentVo isExist = studentService.findById(studentVo.getId());
        if (isExist !=null){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error","学生已存在");
            List<Grade> gradeVoList = gradeService.finAll();
            modelAndView.addObject("gradeVoList",gradeVoList);
            modelAndView.setViewName("student/new");
            return modelAndView;
        }
        studentService.save(studentVo);
        return new ModelAndView("redirect:list.do");
    }

    @RequestMapping(value = "update")
    public ModelAndView update(StudentVo studentVo){
        studentService.save(studentVo);
        return new ModelAndView("redirect:list.do");
    }

    @RequestMapping(value = "new")
    public ModelAndView addStudent(){
        ModelAndView modelAndView = new ModelAndView("student/new");
        List<Grade> gradeVoList = gradeService.finAll();
        modelAndView.addObject("gradeVoList",gradeVoList);
        return modelAndView;
    }

    @RequestMapping(value = "list")
    public  ModelAndView listStudent(PageReqVo reqVo){
        ModelAndView modelAndView = new ModelAndView("student/list");
        if (reqVo.getError() !=null){
            modelAndView.addObject("error",reqVo.getError());
        }
        StudentListVo studentListVo = studentService.findAll(reqVo);
        CountUtils.setSubjectCount(studentListVo.getStudentVoList());
        CountUtils.setAvgScore(studentListVo.getStudentVoList());
        modelAndView.addObject("studentListVo",studentListVo);
        return modelAndView;
    }

    @RequestMapping(value = "putScore")
    public ModelAndView putScore(Long id){
        if (id == null){
            return new ModelAndView("redirect:list.do");
        }else {
            StudentVo studentVo = studentService.findById(id);
            ModelAndView modelAndView = new ModelAndView("student/updateScore");
            modelAndView.addObject("studentVo",studentVo);
            return  modelAndView;
        }
    }

    @RequestMapping("updateScore")
    public String updateScore(StuSubVo subVo){
        if (subVo.getScoreVos() !=null){
            studentService.saveWithSubject(subVo);
        }
        return "redirect:list.do";
    }

    @RequestMapping(value = "ChooseSubject")
    public ModelAndView chooseSubject(Long id){
        if (id == null){
            return new ModelAndView("redirect:list.do");
        }else {
            ModelAndView modelAndView = new ModelAndView("student/chooseSubject");
            modelAndView.addObject("id",id);
            List<SubjectVo> subjectVos = subjectService.findAll();
            modelAndView.addObject("subjectVoList",subjectVos);
            return  modelAndView;
        }
    }

    @RequestMapping(value = "saveChoosedSubject")
    public ModelAndView saveChoosed(StuSubVo stuSubVo){
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        if (stuSubVo.getScoreVos() !=null){
            Integer duplicate = studentService.saveWithSubject(stuSubVo);
            if (duplicate > 0){
                modelAndView.addObject("error","存在重复选课！！其余课程已选");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "toUpdate")
    public ModelAndView toUpdate(Long id){
        ModelAndView modelAndView = new ModelAndView("student/update");
        StudentVo studentVo = studentService.findById(id);
        List<Grade> grades = gradeService.finAll();
        StudentParamVo studentParamVo = new StudentParamVo(studentVo,grades);
        modelAndView.addObject("studentParamVo",studentParamVo);
        return modelAndView;
    }

    @RequestMapping(value = "delete")
    public String delete(Long id){
        if (id != null) {
            studentService.delete(id);
        }
        return "redirect:list.do";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
