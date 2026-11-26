package com.example.sp_demo4.controller;

import com.example.sp_demo4.model.dto.InsertOrderDTO;
import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/insert")
    public BaseVo<Object> insert(@RequestBody InsertOrderDTO insertOrderDTO) {
        return orderService.insertOrder(insertOrderDTO);
    }
}
