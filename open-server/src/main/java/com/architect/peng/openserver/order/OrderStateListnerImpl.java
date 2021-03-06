package com.architect.peng.openserver.order;

import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/8/31 21:01
 */
@Component("orderListner")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListnerImpl {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVERY")
    public boolean payTransaction(Message<OrderStatusChangeEvent> message) {
        final Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatus.WAIT_DELIVERY);
        System.out.println("支付状态机返回信息" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVERY", target = "WAIT_RECIEVE")
    public boolean deliveryTransaction(Message<OrderStatusChangeEvent> message) {
        final Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatus.WAIT_RECIEVE);
        System.out.println("支付状态机返回信息" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECIEVE", target = "FINISH")
    public boolean recieveTransaction(Message<OrderStatusChangeEvent> message) {
        final Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatus.FINISH);
        System.out.println("支付状态机返回信息" + message.getHeaders().toString());
        return true;
    }
}
