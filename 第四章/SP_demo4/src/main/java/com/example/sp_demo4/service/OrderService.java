package com.example.sp_demo4.service;

import com.example.sp_demo4.model.dto.InsertOrderDTO;
import com.example.sp_demo4.model.entity.Orders;
import com.example.sp_demo4.model.entity.UserMoney;
import com.example.sp_demo4.model.vo.BaseVo;
import com.example.sp_demo4.repository.OrderRepository;
import com.example.sp_demo4.repository.UserMoneyRepository;
import com.example.sp_demo4.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ModuleFinder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserMoneyRepository userMoneyRepository;
    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public BaseVo<Object> finishOrder(String orderId) {
        // 找出id对应的订单
        Orders order = orderRepository.selectById(orderId);
        String buyId = order.getBuyId();
        String saleId = order.getSaleId();
        float orderMoney = order.getMoney();
        UserMoney buyer = userMoneyRepository.selectById(buyId);
        UserMoney saler = userMoneyRepository.selectById(saleId);
        if (buyer == null || saler == null) {
            throw new RuntimeException("用户不存在");
        }
        if (buyer.getMoney() < orderMoney) {
            throw new RuntimeException("购买者钱不够了");
        }
        buyer.setMoney(buyer.getMoney() - orderMoney);
        saler.setMoney(saler.getMoney() + orderMoney);
        if (userMoneyRepository.updateById(buyer) != 1) {
            throw new RuntimeException("扣款失败");
        }
        if (userMoneyRepository.updateById(saler) != 1) {
            throw new RuntimeException("打款失败");
        }
        if (order.getStatus().equals("FINISH")) {
            throw new RuntimeException("订单状态错误");
        } else {
            order.setStatus("FINISH");
            order.setFinishTime(new Date());
            if (orderRepository.updateById(order) != 1) {
                throw new RuntimeException("订单完成失败");
            } else {
                return BaseVo.success(null);
            }
        }
    }
}
