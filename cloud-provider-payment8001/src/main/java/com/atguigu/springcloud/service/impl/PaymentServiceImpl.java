package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired//@Resourcejava自带的 相当于spring的@Autowired
    private PaymentDao paymentDao;

    public Payment queryById(Long id){
        return paymentDao.queryById(id);
    }

    @Override
    public Long insert(String serial) {
        return paymentDao.insert(serial);
    }

}
