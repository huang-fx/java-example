package com.example.sp_demo4.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageDetailVo {
    private int pageId;
    private String pageName;
    private String pagePath;
    private String parentId;
    private List<PageDetailVo> children;
}
