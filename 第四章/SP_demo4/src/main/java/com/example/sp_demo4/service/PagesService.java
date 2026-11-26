package com.example.sp_demo4.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sp_demo4.model.entity.Modules;
import com.example.sp_demo4.model.entity.Pages;
import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.model.vo.ModulesVo;
import com.example.sp_demo4.model.vo.PageDetailVo;
import com.example.sp_demo4.repository.ModulesRepository;
import com.example.sp_demo4.repository.PagesRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagesService {
    @Autowired
    ModulesRepository modulesRepository;
    @Autowired
    PagesRepository pagesRepository;
    public BaseVo<List<ModulesVo>> getList() {
        // select * from modules;
        List<Modules> mList = modulesRepository.selectList(null);
        List<ModulesVo> finalList = mList.stream().map(moduleItem -> {
            // 把每一个modules变成moduleVo 并且塞进页面
            ModulesVo modulesVo = new ModulesVo();
            modulesVo.setModuleId(moduleItem.getModuleId());
            modulesVo.setModuleName(moduleItem.getModuleName());
            // 生成modules的pages
            // select * from pages where module_id = id and parent_id is null;
            // Pages是指entity下的Pages实体 这个实体里面指定了对应哪个表
            QueryWrapper<Pages> pagesQueryWrapper = new QueryWrapper<Pages>();
            // 这里不要写小驼峰
            pagesQueryWrapper.eq("module_id", moduleItem.getModuleId()).isNull("parent_id");
            List<Pages> pagesList = pagesRepository.selectList(pagesQueryWrapper);
            modulesVo.setPage(getChildren(pagesList));
            return modulesVo;
        }).collect(Collectors.toList());
        return BaseVo.success(finalList);
    }

    private List<PageDetailVo> getChildren(List<Pages> pageList) {
        List<PageDetailVo> pageDetailVoList = pageList.stream().map(pageItem -> {
            PageDetailVo pageDetailVo = new PageDetailVo();
            pageDetailVo.setPageId(pageItem.getPageId());
            pageDetailVo.setPageName(pageItem.getPageName());
            pageDetailVo.setPagePath(pageItem.getPagePath());
            // 查找子页面并且设置children
            // select * from pages where parentId = pageItem.page_id;
            QueryWrapper<Pages> pagesQueryWrapper = new QueryWrapper<Pages>();
            pagesQueryWrapper.eq("parent_id", pageItem.getPageId());
            List<Pages> sonList = pagesRepository.selectList(pagesQueryWrapper);
            if (sonList.size() == 0) {
                // 如果sonList为空 代表没有子页面 children设置为空list
                pageDetailVo.setChildren(new ArrayList<PageDetailVo>());
            } else {
                pageDetailVo.setChildren(getChildren(sonList));
            }
            return pageDetailVo;
        }).collect(Collectors.toList());
        return pageDetailVoList;
    }
}
