package com.biz.std.vo.Grade;

import com.biz.std.vo.PageVo;

import java.util.List;

public class GradeListVo {

    private PageVo pageVo;
    private List<GradeVo> gradeVoList;

    public GradeListVo(PageVo pageVo, List<GradeVo> gradeVoList) {
        this.pageVo = pageVo;
        this.gradeVoList = gradeVoList;
    }

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo = pageVo;
    }

    public List<GradeVo> getGradeVoList() {
        return gradeVoList;
    }

    public void setGradeVoList(List<GradeVo> gradeVoList) {
        this.gradeVoList = gradeVoList;
    }
}
