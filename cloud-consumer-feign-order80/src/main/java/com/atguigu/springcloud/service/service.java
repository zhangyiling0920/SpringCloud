package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //忘了 是不是@enablexxx和@xxx就注入了不用@Component或者@Service了
public interface service {
    @GetMapping("/payment/lb")
    String getPaymentLB();
}
