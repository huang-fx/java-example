package com.example.sp_demo4.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        // 创建一个图片验证码对象
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 为图片验证码设置相应的配置文件 如有无边框 图片验证码整体的宽高等
        Properties properties = new Properties();
        // 设置图片验证码有无边框
        properties.setProperty("kaptcha.border", "no");
        // 设置图片验证码的宽度
        properties.setProperty("kaptcha.width", "100");
        // 设置图片验证码的高度
        properties.setProperty("kaptcha.height", "50");
        // 定义一个配置文件 将上面设置的配置文件添加进去
        Config config = new Config(properties);
        // 将配置文件添加到图片验证码对象中 作为对象的配置
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
