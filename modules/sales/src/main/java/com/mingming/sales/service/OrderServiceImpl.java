package com.mingming.sales.service;

import com.axelor.common.ObjectUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.ValidationException;

import com.mingming.sales.db.Order;
import com.mingming.sales.db.OrderLine;
import com.mingming.sales.db.repo.OrderRepository;
import com.mingming.sales.service.OrderService;
import javax.inject.Inject;

/**
 * 销售订单的业务逻辑层接口实现
 */
public class OrderServiceImpl implements OrderService {

    @Inject private OrderRepository _repo;

    public void validate(Order order) {
        if (order != null
                && order.getConfirmTime() != null
                && order.getConfirmTime().isBefore(order.getOrderTime())) {
            throw new ValidationException("无效的销售单日期");
        }
    }

    public OrderLine calculateOrderLine(OrderLine line) {
        BigDecimal subtotal = line.getUnitPrice().multiply(line.getQuantity());
        line.setSubtotalAmount(subtotal);
        return line;
    }

    public Order calculateOrder(Order order) {
        BigDecimal amount = BigDecimal.ZERO;
        if (!ObjectUtils.isEmpty(order.getLines())) {
            for (OrderLine line : order.getLines()) {
                // 每行明细的小计等于单价乘以数量
                BigDecimal subtotal = line.getUnitPrice().multiply(line.getQuantity());
                // 累加计算总金额
                amount = amount.add(subtotal);
            }
        }

        // 四舍五入保留两位小数
        order.setTotalAmount(amount.setScale(2, RoundingMode.HALF_UP));
        return order;
    }
}
