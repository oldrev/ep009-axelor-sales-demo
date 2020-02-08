package com.mingming.sales.db.repo;

import com.mingming.sales.db.Order;
import java.util.Map;

/**
 * 这个 Repo 没什么用，就是演示下怎么自定义数据访问层
 */
public class OrderRepository extends AbstractOrderRepository {

    public String hello() {
        return "Hello World";
    }
}
