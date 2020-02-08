package com.mingming.sales.service;

import com.axelor.common.ObjectUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.ValidationException;

import com.mingming.sales.db.Order;
import com.mingming.sales.db.OrderLine;

/**
 * 销售订单的业务逻辑层接口
 */
public interface OrderService {
    void validate(Order order);
    OrderLine calculateOrderLine(OrderLine line);
    Order calculateOrder(Order order);
}

