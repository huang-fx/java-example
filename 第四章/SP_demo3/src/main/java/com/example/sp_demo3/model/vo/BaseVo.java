package com.example.sp_demo3.model.vo;

import lombok.Data;

// 请求接口后返回的数据要有一个固定的形式或者说是格式 这个文件就是在定义这个形式或格式
@Data
public class BaseVo<T> {
    private boolean success;
    private String msg;
    private T data;
    private BaseVo(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
    // 第一个<F>是在声明泛型 告诉编译器下面的这个方法里要使用一个叫做F的类型
    // 第二个<F>是在使用泛型作为返回值类型 放在方法名success的前面 表示这个方法执行完毕后 会返回一个BaseVo对象 而且这个对象的数据类型就是F
    // 第三个F 是参数的类型 表示这个方法接收一个参数 参数的名称为data 它的类型为F
    public static <F> BaseVo<F> success(F data) {
        BaseVo<F> baseVo = new BaseVo<>(true, "成功", data);
        return baseVo;
    }
    public static <F> BaseVo<F> fail(String msg, F data) {
        BaseVo<F> baseVo = new BaseVo<>(false, msg, data);
        return baseVo;
    }
}
