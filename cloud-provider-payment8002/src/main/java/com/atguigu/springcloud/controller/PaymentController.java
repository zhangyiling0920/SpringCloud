package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //查询用GetMapping 插入用PostMapping
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> queryById(@PathVariable("id") Long id){
        Payment p = paymentService.queryById(id);
        if (p!=null){
            return new CommonResult<>(200, "查询成功", p);
        } else{
            return new CommonResult<>(444, "查询失败");
        }
    }

    @PostMapping(value = "/payment/insert")
    //@RequestBody 在请求体中 只有Post请求会方请求体
    //主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)
    //远程调用的时候 serial是放在请求体里的
    //你这get套post纯属扯淡
    public CommonResult<Long> insert(@RequestBody String serial){
        Long id = paymentService.insert(serial);
        if (id != null){
            return new CommonResult<>(200, "成功插入", id);
        }else{
            return new CommonResult<>(444, "查询失败");
        }

    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


}
