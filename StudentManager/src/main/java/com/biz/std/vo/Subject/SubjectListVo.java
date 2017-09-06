package com.biz.std.vo.Subject;

import com.biz.std.vo.PageVo;

import java.util.List;

public class SubjectListVo {

    private PageVo pageVo;
    private List<SubjectVo> subjectVoList;

    public SubjectListVo(PageVo pageVo, List<SubjectVo> subjectVoList) {
        this.pageVo = pageVo;
        this.subjectVoList = subjectVoList;
    }

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo = pageVo;
    }

    public List<SubjectVo> getSubjectVoList() {
        return subjectVoList;
    }

    public void setSubjectVoList(List<SubjectVo> subjectVoList) {
        this.subjectVoList = subjectVoList;
    }
}
