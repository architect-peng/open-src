package com.architect.peng.openserver.order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/8/31 20:49
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<com.architect.peng.designpatterns.state.order.OrderStatus, com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent> {


    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<com.architect.peng.designpatterns.state.order.OrderStatus, com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent> states) throws Exception {
        states.withStates()
                .initial(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(com.architect.peng.designpatterns.state.order.OrderStatus.class));
    }

    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<com.architect.peng.designpatterns.state.order.OrderStatus, com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent> transitions) throws Exception {

        transitions
                .withExternal().source(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_PAYMENT).target(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_DELIVERY)
                .event(com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent.PAYED)
                .and()
                .withExternal().source(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_DELIVERY).target(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_RECIEVE)
                .event(com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent.DLIVERY)
                .and()
                .withExternal().source(com.architect.peng.designpatterns.state.order.OrderStatus.WAIT_RECIEVE).source(com.architect.peng.designpatterns.state.order.OrderStatus.FINISH)
                .event(com.architect.peng.designpatterns.state.order.OrderStatusChangeEvent.RECIEVED);
    }

    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister(new StateMachinePersist<Object, Object, Order>() {
            @Override
            public void write(StateMachineContext<Object, Object> stateMachineContext, Order order) throws Exception {

            }

            @Override
            public StateMachineContext<Object, Object> read(Order order) throws Exception {
                return new DefaultStateMachineContext(order.getOrderStatus(), null, null, null);
            }
        });
    }
}
