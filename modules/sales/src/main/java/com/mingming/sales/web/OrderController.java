package com.mingming.sales.web;

// 导入系统库
import com.axelor.db.JpaSupport;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

// 导入模块库
import com.mingming.sales.db.Order;
import com.mingming.sales.db.OrderStatus;
import com.mingming.sales.service.OrderService;

public class OrderController extends JpaSupport {

    @Inject private OrderService _service;

    /**
     * 此控制器动作用于按下“确认订单”按钮的操作，一般涉及业务逻辑的代码应当放入业务逻辑层中，
     * 也就是 OrderService 中
     */
    public void onConfirm(ActionRequest request, ActionResponse response) {

        Order order = request.getContext().asType(Order.class);

        response.setReadonly("orderTime", true);
        response.setReadonly("confirmTime", true);

        if (order.getConfirmTime() == null) {
            response.setValue("confirmTime", LocalDate.now());
        }

        response.setValue("status", OrderStatus.OPEN);
    }

    public void calculate(ActionRequest request, ActionResponse response) {

        Order order = request.getContext().asType(Order.class);
        order = _service.calculateOrder(order);

        //response.setValue("amount", order.getAmount());
        //response.setValue("taxAmount", order.getTaxAmount());
        response.setValue("totalAmount", order.getTotalAmount());
    }

}