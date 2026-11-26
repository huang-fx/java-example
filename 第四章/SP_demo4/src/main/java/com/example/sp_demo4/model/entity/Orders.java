package com.example.sp_demo4.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orders")
public class Orders {
    @TableId
    private String orderId;
    private String buyId;
    private String saleId;
    private float money;
    private String status;
    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
    private Date finishTime;
}
