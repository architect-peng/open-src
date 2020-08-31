package com.architect.peng.openserver.order;

import java.util.Map;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/8/31 21:07
 */
public interface IOderService {
    Order create();
    Order pay(int id);
    Order deliver(int id);
    Order recieve(int id);
    Map<Integer,Order>  getOrders();
}
