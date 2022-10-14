package com.example.board_practice.course.controller;

import com.example.board_practice.util.PageUtil;

public class PageController {
    public String getPaperHtml(long totalCount, long pageSize, long pageIndex, String queryString) {
        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, queryString);
        return pageUtil.pager();
    }
}
