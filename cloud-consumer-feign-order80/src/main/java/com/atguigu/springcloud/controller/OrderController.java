package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private service service;

    @GetMapping("/consumer/payment/lb")
    public String get(){
        return service.getPaymentLB();
    }

}
