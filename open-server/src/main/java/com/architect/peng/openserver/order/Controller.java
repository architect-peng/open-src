package com.architect.peng.openserver.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/8/31 21:46
 */
@RestController
public class Controller {
    @Autowired
    private IOderService iOderService;

    @RequestMapping("create")
    public Integer createOrder() {

        return iOderService.create().getId();
    }

    @RequestMapping("pay")
    public Order pay(Integer id) {
        return iOderService.pay(id);
    }

    @RequestMapping("delivery")
    public Order delivery(Integer id) {
        return iOderService.deliver(id);
    }

    @RequestMapping("recieve")
    public Order recieve(Integer id) {
        return iOderService.recieve(id);
    }
}
