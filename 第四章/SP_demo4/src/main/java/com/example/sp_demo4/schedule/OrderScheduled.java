package com.example.sp_demo4.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sp_demo4.model.entity.Orders;
import com.example.sp_demo4.repository.OrderRepository;
import com.example.sp_demo4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderScheduled {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderScheduled orderScheduled;
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void autoFinish() {
        // 先查出来所有的状态为未完成 并且距离创建时间已经大于七天的订单
        // select * from orders where status != FINISH and create_time <= 七天前的时间
        // 找出七天前的时间
        LocalDate sevenDayBefore = LocalDate.now().minusDays(7);
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.ne("status", "FINISH").lt("create_time", sevenDayBefore);
        orderRepository.selectList(qw).forEach((Orders orders) -> {
            orderService.finishOrder(orders.getOrderId());
        });
    }
}
