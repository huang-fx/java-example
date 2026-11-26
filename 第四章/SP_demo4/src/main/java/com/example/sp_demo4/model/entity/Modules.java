package com.example.sp_demo4.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("modules")
public class Modules {
    @TableId(type = IdType.AUTO)
    private int moduleId;
    private String moduleName;
}
