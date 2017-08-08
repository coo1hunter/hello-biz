package com.kingy.entity;

import java.io.Serializable;

/**
 * Created by cool on 2017/7/19.
 */
public class PageBean implements Serializable{
    private Long currentPage = 1l; // 当前页, 默认显示第一页
    private int pageCount = 10;   // 每页显示的行数(查询返回的行数), 默认每页显示4行
    private Long totalCount;      // 总记录数
    private Long totalPage;       // 总页数 = 总记录数 / 每页显示的行数  (+ 1)


    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        if (totalCount % pageCount == 0) {
            totalPage = totalCount / pageCount;
        } else {
            totalPage = totalCount / pageCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
