package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    //这个LoadBalance和ribbon的负载均衡又没关系
    //那开启restTemplate的负载均衡会轮询是ribbon默认是轮询的吧
    //restTemplate可以轮询的能力就是可以按key name 去找value address
    @LoadBalanced //开启restTemplate的负载均衡能力 默认轮询 就是交替出现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
//springBoot配置类