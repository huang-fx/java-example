package com.example.sp_demo4.controller;

import com.example.sp_demo4.config.KaptchaConfig;
import com.example.sp_demo4.model.dto.ModifyPasswordDTO;
import com.example.sp_demo4.model.dto.RegisterDTO;
import com.example.sp_demo4.model.dto.UserIdDTO;
import com.example.sp_demo4.model.dto.UserListDTO;
import com.example.sp_demo4.model.entity.User;
import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.model.vo.PageVo;
import com.example.sp_demo4.model.vo.UserVo;
import com.example.sp_demo4.service.UserSercive;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.InjectableValues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserSercive userSercive;
    @Autowired
    KaptchaConfig kaptchaConfig;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/register")
    public BaseVo<Object> registerUser(@RequestBody User form) {
        BaseVo<Object> result = userSercive.registerUser(form);
        return result;
    }
    @PostMapping("/login")
    public BaseVo<Optional<UserVo>> login(@RequestBody RegisterDTO form, HttpSession session) {
        return userSercive.loginUser(form, session);
    }
    @PostMapping("/modifyPassword")
    public BaseVo<Object> modifyPassword(@RequestBody ModifyPasswordDTO entity) {
        return userSercive.modifyUserPassword(entity);
    }
    @PostMapping("/deleteUser")
    public BaseVo<Object> deleteUser(@RequestBody UserIdDTO userId) {
        return userSercive.deleteUserById(userId);
    }
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
    @GetMapping("getCode")
    public  String getCode(HttpSession session) throws Exception {
        // 创建验证码文本
        String text = defaultKaptcha.createText();
        // 将上面创建的文本设置为session的文本 也就是图片验证码的文本
        session.setAttribute("code", text);
        // 将验证码文本放在图片中 用图片展示出来
        BufferedImage image = defaultKaptcha.createImage(text);
        // 图片是要放置在前端代码中的 前端代码在img标签中需要base64格式的字符数组作为url参数 所以需要将上面的图片转换成base64格式的字符数组
        // 利用ByteArrayOutputStream将一些文件输出成内存上的字符数组
        // 先创建一个字节数组输出流 将想要转成base64格式的文件最为这个对象的参数传递进去
        ByteArrayOutputStream boutput = new ByteArrayOutputStream();
        // 现在可以利用boutput自身的write方法将图片文件写入到boutput变量中 但是这个文件只能接收byte数组作为对象
        // 所以还要将上面的图片转换为byte数组才能利用boutput的write方法写入到内存中
        // 调用ImageIO里的write()方法 就可以将图片写入到字节输出流中
        // 第一个参数为想转换成字节输出流的图片变量 第二个参数为输出的图片格式 第三个参数为字节数组输出流变量 也就是转换之后存在哪个变量中
        ImageIO.write(image, "jpg", boutput);
        byte[] imageByteArr = boutput.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageByteArr);
        // 前端在加载图片的时候需要拼接相关的前缀 所以这里我们在返回参数的时候直接将这个前缀给它拼接好 这样前端就可以直接使用了
        return "data:image/jpeg;base64,"+base64Image;
    }
    // 测试base64的编码接口
//    @GetMapping("/testBase64")
//    public String testBase64() {
//        String str = "hello world";
//        // 将上面的字符串编码为Base64 转成base64格式的时候需要的是byte格式的数组 所以要利用getBytes()方法将字符串转换为byte格式的数组
//        String baseStr = Base64.getEncoder().encodeToString(str.getBytes());
//        return baseStr;
//    }
//    // 测试base64的解码接口
//    @GetMapping("/decodeBase64")
//    public String decodeBase64(@RequestParam String base) {
//        byte[] baseStr = Base64.getDecoder().decode(base);
//        return new String(baseStr);
//    }

    @GetMapping("/list")
    public BaseVo<PageVo<User>> getList(@ModelAttribute UserListDTO userListDTO) {
        return userSercive.getList(userListDTO);
    }
}
