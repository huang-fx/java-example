package com.example.sp_demo4.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sp_demo4.model.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository extends BaseMapper<Orders> {

}
