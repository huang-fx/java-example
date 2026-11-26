package com.example.sp_demo4.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ModulesVo {
    private int moduleId;
    private String moduleName;
    private List<PageDetailVo> page;
}
