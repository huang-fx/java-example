package com.example.sp_demo4.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserPowerVo {
    private String username;
    private String id;
    private String roleName;
    private List<ModulesVo> powerList;
}
