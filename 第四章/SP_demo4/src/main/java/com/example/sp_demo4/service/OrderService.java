package com.example.sp_demo4.service;

import com.example.sp_demo4.model.dto.InsertOrderDTO;
import com.example.sp_demo4.model.entity.Orders;
import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ModuleFinder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public BaseVo<Object> insertOrder(InsertOrderDTO insertOrderDTO) {
        String buyId = insertOrderDTO.getBuyId();
        String saleId = insertOrderDTO.getSaleId();
        // 生成id 通过时间戳+buyId+saleId+md5加密
        String originStr = System.currentTimeMillis()+buyId+saleId;
        String id = "";
        // 进行md5加密
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(originStr.getBytes());
            // 把hashBytes里面的每一个字符都转成16进制 并且拼接成字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b: hashBytes) {
                // 将每一个字节转换成两位小写的16进制字符 x为小写 X为大写
                hexString.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException err) {
            System.out.println(err.getMessage());
        }
        Orders order = new Orders();
        order.setOrderId(id);
        order.setBuyId(buyId);
        order.setSaleId(saleId);
        order.setMoney(insertOrderDTO.getMoney());
        order.setStatus("NOT_PAY");
        int result = orderRepository.insert(order);
        if (result == 1) {
            return BaseVo.success(null);
        } else {
            return BaseVo.fail("插入失败", null);
        }
    }
}
