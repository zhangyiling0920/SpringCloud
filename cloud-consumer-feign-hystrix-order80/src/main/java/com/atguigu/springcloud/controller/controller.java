package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.service;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@DefaultProperties(defaultFallback = "paymentInfoGlobalHandler")//要在方法上加@HystrixCommand不要参数
public class controller {
    @Qualifier("com.atguigu.springcloud.service.service")
    @Autowired
    private service service;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentInfoOK(@PathVariable("id") Integer id) throws InterruptedException {
        //int i = 10/0; //在feign调用provider之前出错 跳到全局fallback
        String s = service.paymentInfoOK(id); //只有feign调用provider出错 则跳到解耦fallback
        //TimeUnit.MICROSECONDS.sleep(3000); //加这句话和下面这句居然没跳到fallback 正确执行
        //int i = 10/0; //在feign调用provider之后出错 也跳到全局fallback
        //为啥呢 这是不是和jvm有关啊 jvm去死吧//运行时异常
        return s;
    }

    //超时或者异常
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "10")
    })//为什么有时跳转到paymentInfoTimeOutHandler 有时跳转到正确的？
    public String paymentInfoTimeOut(@PathVariable("id") Integer id){
        //int i = 10/0;//如果添了这句就一定跳到paymentInfoTimeOutHandler
        return service.paymentInfoTimeOut(id);
    }

    //特定的fallback方法的参数和对应方法相同
    public String paymentInfoTimeOutHandler(@PathVariable("id") Integer id){
        return "consumer busy, hystrix handle, specific fallback 线程池"+Thread.currentThread();
    }

    //全局fallback方法不能有参数
    public String paymentInfoGlobalHandler(){
        return "consumer busy, hystrix handle, default fallback 线程池"+Thread.currentThread();
    }


}
