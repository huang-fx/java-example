package com.example.sp_demo4.model.dto;

import lombok.Data;

@Data
public class ModifyPasswordDTO {
    private String id;
    private String oldPassword;
    private String newPassword;
}
