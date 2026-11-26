package com.example.sp_demo4.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sp_demo4.model.dto.ModifyPasswordDTO;
import com.example.sp_demo4.model.dto.RegisterDTO;
import com.example.sp_demo4.model.dto.UserIdDTO;
import com.example.sp_demo4.model.dto.UserListDTO;
import com.example.sp_demo4.model.entity.PageModules;
import com.example.sp_demo4.model.entity.Pages;
import com.example.sp_demo4.model.entity.User;
import com.example.sp_demo4.model.entity.UserPage;
import com.example.sp_demo4.model.vo.*;
import com.example.sp_demo4.repository.PagesRepository;
import com.example.sp_demo4.repository.UserRepository;
import com.example.sp_demo4.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserSercive {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PagesRepository pagesRepository;
//    mybatis版本
//    public BaseVo<Object> registerUser(RegisterDTO form) {
//        Optional<User> opUser = userRepository.searchUserByUsername(form.getUsername());
//        if(opUser.isPresent()) {
//            return BaseVo.fail("用户已存在", null);
//        } else {
//            int result = userRepository.insertUser(form);
//            if(result==1) {
//                return BaseVo.success(null);
//            } else {
//                return BaseVo.fail("注册失败", null);
//            }
//        }
//    }
//    public BaseVo<Optional<UserVo>> loginUser(RegisterDTO form) {
//        Optional<User> opUser = userRepository.searchUserByUsername(form.getUsername());
//        if(opUser.isEmpty()) {
//            return BaseVo.fail("用户不存在", null);
//        } else {
//            User user = opUser.get();
//            if(user.getPassword().equals(form.getPassword())) {
//                UserVo userVo = new UserVo();
//                userVo.setId(user.getId());
//                userVo.setUsername(user.getUsername());
//                return BaseVo.success(Optional.of(userVo));
//            } else {
//                return BaseVo.fail("密码错误", null);
//            }
//        }
//    }
//    public BaseVo<Object> modifyUserPassword(ModifyPasswordDTO entity) {
//        String id = entity.getId();
//        Optional<User> opUser = userRepository.getUserById(id);
//        if(opUser.isEmpty()) {
//            return BaseVo.fail("用户不存在，修改失败", null);
//        } else {
//            User user = opUser.get();
//            if(user.getPassword().equals(entity.getOldPassword())) {
//                userRepository.updateUserPassword(id, entity.getNewPassword());
//                return BaseVo.success(null);
//            } else {
//                return BaseVo.fail("老密码不正确，修改失败", null);
//            }
//        }
//    }
//    public BaseVo<Object> deleteUserById(UserIdDTO userId) {
//        String id = userId.getId();
//        Optional<User> opUser = userRepository.getUserById(id);
//        if(opUser.isEmpty()) {
//            return BaseVo.fail("用户不存在，删除失败", null);
//        } else {
//            int result = userRepository.deleteUserById(id);
//            if(result == 1) {
//                return BaseVo.success(null);
//            } else {
//                return BaseVo.fail("删除失败", null);
//            }
//        }
//    }
    // mybatis-plus版本
    public BaseVo<Object> registerUser(User form) {
        String username = form.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);   // 相当于where username = #{username}
        User user = userRepository.selectOne(queryWrapper);
        if (user == null) {
            int result = userRepository.insert(form);
            if (result == 1) {
                return BaseVo.success(null);
            } else {
                return BaseVo.fail("注册失败", null);
            }
        } else {
            return BaseVo.fail("用户已存在", null);
        }
    }
    public BaseVo<Optional<UserVo>> loginUser(RegisterDTO form, HttpSession session) {
        if (form.getCode().equals(session.getAttribute("code"))) {
            // 不再区分是用户名不存在还是密码不对 必须用户名和密码同时匹配上才行
            // 筛选sql变成了 select * from user where username=#{username} and password=#{password}
            String username = form.getUsername();
            String password = form.getPassword();
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("username", username).eq("password", password);
            User user = userRepository.selectOne(queryWrapper);
            if (user == null) {
                return BaseVo.fail("账户名或密码错误", null);
            } else {
                UserVo userVo = new UserVo();
                userVo.setId(user.getId());
                userVo.setUsername(user.getUsername());
                userVo.setToken(jwtUtils.getToken(userVo.getId()));
                return BaseVo.success(Optional.of(userVo));
            }
        } else {
            return BaseVo.fail("验证码错误", null);
        }
    }
    public BaseVo<Object> modifyUserPassword(ModifyPasswordDTO entity) {
        String id = entity.getId();
        User user = userRepository.selectById(id);
        if (user == null) {
            return BaseVo.fail("用户不存在", null);
        } else {
            if (user.getPassword().equals(entity.getOldPassword())) {
                user.setPassword(entity.getNewPassword());
                userRepository.updateById(user);
                return BaseVo.success(null);
            } else {
                return BaseVo.fail("老密码错误", null);
            }
        }
    }
    public BaseVo<Object> deleteUserById(UserIdDTO userId) {
        // 直接拿id删除 如果删除结果是0 则代表没有用户被删除 删除失败
        String id = userId.getId();
        int result = userRepository.deleteById(id);
        if (result == 1) {
            return BaseVo.success(null);
        } else {
            return BaseVo.fail("删除失败", null);
        }
    }
    public BaseVo<PageVo<User>> getList(UserListDTO userListDTO) {
        int pageNum = userListDTO.getPageNum();
        int pageSize = userListDTO.getPageSize();
        int offset = (pageNum - 1) * pageSize;
        List<User> userList = userRepository.getUserList(userListDTO, offset);
        int total = userRepository.getCountUser();
        boolean hasMore = pageNum * pageSize < total;
        return BaseVo.success(PageVo.getPageVo(total, userList, pageNum, pageSize, hasMore));
    }
    public BaseVo<UserPowerVo> getUserPower(String id) {
        // 第一步 根据id查出用户能访问哪些页面以及角色名称
        List<UserPage> userPageList = userRepository.getUserPageIdList(id);
        System.out.println("userPageList" + userPageList);
        List<Integer> idList = new ArrayList<>();
        UserPowerVo userPowerVo = new UserPowerVo();
        userPowerVo.setUsername(userPageList.get(0).getUserName());
        userPowerVo.setId(userPageList.get(0).getId());
        userPowerVo.setRoleName(userPageList.get(0).getRoleName());
        userPageList.forEach(item -> {
            idList.add(item.getPageId());
        });
        List<PageModules> pageModules = pagesRepository.getPageModules(idList);
        List<ModulesVo> listModuleVo = pageModules.stream().map(pageModuleItem -> {
            // 把pageModuleItem转成ModulesVo
            ModulesVo modulesVo = new ModulesVo();
            modulesVo.setModuleId(pageModuleItem.getModuleId());
            modulesVo.setModuleName(pageModuleItem.getModuleName());
            modulesVo.setPage(renderPageTree(pageModuleItem.getPageIds()));
            return  modulesVo;
        }).collect(Collectors.toList());
        userPowerVo.setPowerList(listModuleVo);
        return BaseVo.success(userPowerVo);
    }
    private List<PageDetailVo> renderPageTree(String ids) {
        // 收到的该模块能访问的页面id拼接成的字符串 转换成页面树List<PageDetailVo>
        // 1.先分割字符串 得到id组成的list
        List<String> idList = Arrays.asList(ids.split(","));
        List<PageDetailVo> voList = new ArrayList<>();
        Map<String, PageDetailVo> voMap = new HashMap<>();
        List<PageDetailVo> finalList = new ArrayList<>();
        // 2.循环idList→查数据库→转pageDetailVo→加list→map
        idList.forEach(id -> {
            Pages pages = pagesRepository.selectById(id);
            PageDetailVo pageDetailVo = new PageDetailVo();
            pageDetailVo.setPageId(pages.getPageId());
            pageDetailVo.setPageName(pages.getPageName());
            pageDetailVo.setPagePath(pages.getPagePath());
            pageDetailVo.setParentId(pages.getParentId());
            pageDetailVo.setChildren(new ArrayList<PageDetailVo>());
            voList.add(pageDetailVo);
            voMap.put(pageDetailVo.getPageId()+"", pageDetailVo);
        });
        voList.forEach(voItem -> {
            String parentId = voItem.getParentId();
            if (parentId == null) {
                finalList.add(voItem);
            } else {
                voMap.get(parentId).getChildren().add(voItem);
            }
        });
        return finalList;
    }
}
