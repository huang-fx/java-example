package com.example.sp_demo4.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sp_demo4.model.dto.CountByDayDTO;
import com.example.sp_demo4.model.dto.CountDTO;
import com.example.sp_demo4.model.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderRepository extends BaseMapper<Orders> {
    public CountDTO getCount(LocalDateTime start, LocalDateTime end);
    public List<CountByDayDTO> getCountByDay(String start, String end);
}
