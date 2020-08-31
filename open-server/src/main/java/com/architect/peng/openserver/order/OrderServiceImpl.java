package com.architect.peng.openserver.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/8/31 21:08
 */
@Service
public class OrderServiceImpl implements IOderService {
    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister;

    private int id = 1;
    private Map<Integer, Order> orderDB = new ConcurrentHashMap<>();

    @Override
    public Order create() {
        Order order = new Order();

        order.setOrderStatus(OrderStatus.WAIT_PAYMENT);
        order.setId(id++);
        orderDB.put(order.getId(), order);
        return order;
    }

    @Override
    public Order pay(int id) {
        final Order order = orderDB.get(id);
        System.out.println("线程名称" + Thread.currentThread().getName() + "尝试支付订单号:" + id);

        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.PAYED).setHeader("order", order).build();
        if(!sendEvent(message,order)){
            System.out.println("线程名称" + Thread.currentThread().getName() + "支付失败，状态异常，订单号:" + id);

        }
        return order;
    }

    private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            persister.restore(orderStateMachine, order);
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            persister.persist(orderStateMachine, order);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }

    @Override
    public Order deliver(int id) {
        final Order order = orderDB.get(id);
        System.out.println("线程名称" + Thread.currentThread().getName() + "尝试发货，订单号:" + id);

        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.DLIVERY).setHeader("order", order).build();
        if(!sendEvent(message,order)){
            System.out.println("线程名称" + Thread.currentThread().getName() + "发货失败，状态异常，订单号:" + id);

        }
        return orderDB.get(id);
    }

    @Override
    public Order recieve(int id) {
        final Order order = orderDB.get(id);
        System.out.println("线程名称" + Thread.currentThread().getName() + "尝试收货，订单号:" + id);

        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.DLIVERY).setHeader("order", order).build();
        if(!sendEvent(message,order)){
            System.out.println("线程名称" + Thread.currentThread().getName() + "收货失败，状态异常，订单号:" + id);

        }
        return orderDB.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orderDB;
    }
}
