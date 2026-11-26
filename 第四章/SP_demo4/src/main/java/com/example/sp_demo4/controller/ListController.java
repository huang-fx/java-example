package com.example.sp_demo4.controller;

import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.model.vo.ModulesVo;
import com.example.sp_demo4.model.vo.UserPowerVo;
import com.example.sp_demo4.service.PagesService;
import com.example.sp_demo4.service.UserSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class ListController {
    @Autowired
    PagesService pagesService;
    @Autowired
    private UserSercive userSercive;

    @GetMapping("/list")
    public BaseVo<List<ModulesVo>> list() {
        return pagesService.getList();
    }
    @GetMapping("/getPower")
    public BaseVo<UserPowerVo> getPower(@RequestParam String id) {
        return userSercive.getUserPower(id);
    }
}
