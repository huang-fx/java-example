package com.example.sp_demo4.model.dto;

import lombok.Data;

@Data
public class UserListDTO {
    // 设置默认值
    private int pageNum = 1;
    private int pageSize = 10;
    private String id;
    private String username;
    private String startTime;
    private String endTime;
}
