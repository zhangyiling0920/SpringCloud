package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.service;
import org.springframework.stereotype.Component;

//模拟provider宕机(或者provider出现错误 如10/0)场景
//在service接口上的feign标注 应该是指如果feign不能使用了 即consumer不能连接到provider了
//即provider出错了
//如果没有这个实现类 宕机时使用全局fallback或者指定fallback
//如果没有使用全局fallback或者指定fallback 宕机时使用这个类 即feignfallback
@Component
public class serviceimpl implements service{

    @Override
    public String paymentInfoOK(Integer id) {
        return "consumer 解耦的fallback处理 ok";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "consumer 解耦的fallback处理 timeout";
    }
}

