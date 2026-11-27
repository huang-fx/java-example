package com.example.sp_demo4.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_money")
public class UserMoney {
    @TableId
    private String userId;
    private float money;
}
