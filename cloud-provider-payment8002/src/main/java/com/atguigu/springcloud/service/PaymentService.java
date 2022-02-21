package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {
    Payment queryById(Long id);
    Long insert(String serial);


}
