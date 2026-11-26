package com.example.sp_demo4.model.dto;

import lombok.Data;

@Data
public class InsertOrderDTO {
    private String buyId;
    private String saleId;
    private float money;
}
