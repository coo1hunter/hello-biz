package com.biz.std.vo;

import com.biz.std.vo.Student.StudentVo;

import java.util.List;

public class StudentListVo {

    /**
     * 分页信息VO
     */
    private PageVo pageVo;

    /**
     * 学生信息列表VO
     */
    private List<StudentVo> studentVoList;

    public StudentListVo(){
        super();
    }

    public StudentListVo(PageVo pageVo, List<StudentVo> studentVoList) {
        this();
        this.pageVo = pageVo;
        this.studentVoList = studentVoList;
    }

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo = pageVo;
    }

    public List<StudentVo> getStudentVoList() {
        return studentVoList;
    }

    public void setStudentVoList(List<StudentVo> studentVoList) {
        this.studentVoList = studentVoList;
    }
}
