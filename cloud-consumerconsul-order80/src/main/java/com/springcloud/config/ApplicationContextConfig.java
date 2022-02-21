package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //开启restTemplate的负载均衡能力 默认轮询 就是交替出现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
//springBoot配置类