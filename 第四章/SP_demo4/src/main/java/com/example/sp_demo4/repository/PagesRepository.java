package com.example.sp_demo4.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sp_demo4.model.entity.PageModules;
import com.example.sp_demo4.model.entity.Pages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PagesRepository extends BaseMapper<Pages> {
    public List<PageModules> getPageModules(List<Integer> ids);
}
