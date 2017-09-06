package com.biz.std.util;

import com.biz.std.vo.PageReqVo;
import com.biz.std.vo.PageVo;
import org.springframework.data.domain.Page;

public class PageUtils {

    public static PageVo getPageVo(PageReqVo reqVo, Page page){
        PageVo pageVo = new PageVo();
        pageVo.setPageIndex(page.getNumber()+1);
        pageVo.setPageSize(reqVo.getPageSize());
        pageVo.setPageCount(page.getTotalPages());
        pageVo.setTotalElements(page.getTotalElements());
        pageVo.setHasPrevious(page.hasPrevious());
        pageVo.setHasNext(page.hasNext());
        return pageVo;
    }
}
