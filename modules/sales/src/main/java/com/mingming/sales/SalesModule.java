package com.mingming.sales;

import com.axelor.app.AxelorModule;
import com.mingming.sales.service.OrderService;
import com.mingming.sales.service.OrderServiceImpl;

public class SalesModule extends AxelorModule {

    @Override
    protected  void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class);
    }
}
