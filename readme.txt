1 建module
    右键父工程 new module
2 改pom
3 写yml
4 主启动
5 业务类
    mapper(.xml) --> dao(interface) -->service(interface) --> serviceImpl --> controller
6 测试


一、consul
1、启动server
/Users/zyl/Downloads/consul agent -dev
2、控制面板
localhost:8500
3、pom
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
4、yaml
spring:
    application:
    name: cloud-payment-service
cloud:
    consul:
        host: localhost
        port: 8500
        discovery:
            service-name: ${spring.application.name}

二、cap
C：consistency 强一致性
A：availability 可用性
P：partition tolerance 分区容错性
cap理论关注的是数据 而不是整体系统设计的策略
cap只能三选二 而分布式系统必须满足p 所以我们有两种选择cp和ap
zookeeper cp
consul cp
eureka ap（eureka有自我保护机制 保证可用性）
ap后续可通过base理论柔性事务补充 来进行数据补充和整体一致性

三、ribbon  客户端软件的负载均衡算法和服务调用 netflix
负载均衡就是将用户的请求分摊到多个服务上 从而达到系统的ha（高可用）
ribbon是进程内的lb 本地负载均衡 会在注册中心获取注册信息服务列表后缓存到jvm本地 从而在本地实现rpc远程服务调用技术
nginx是集中式的lb 服务器负载均衡 客户端所有请求会交给nginx 由其实现请求转发到服务端 负载均衡是由服务端实现的
ribbon是一个类库 集成于消费方进程 消费方通过它来获取到服务方的地址
1、先选择eureka server 优先选择负载较少的
2、从eureka server上渠道的服务注册表中选择一个地址。策略：轮询 随机 重试 根据响应时间加权（如何替换 如何自定义）
IRule：根据特定算法负载均衡
public interface IRule {
    Server choose(Object var1);//根据key选择provider server（负载均衡）

    void setLoadBalancer(ILoadBalancer var1);

    ILoadBalancer getLoadBalancer();
}
轮询 RoundRobinRule 原理
    rest接口请求数 % 服务器集群总数=调用服务器位置下标 每次服务器重启后rest接口
    每次consumer重启后rest接口计数从1开始
轮询 RoundRobinRule 源码
手写

随机 RandomRule
重试 RetryRule

eureka的jar包中就包含了ribbon
ribbon=负载均衡+restTemplate调用

四、restTemplate
restTemplate.getForObject 返回对象为响应体中数据转化成的对象 基本上可以理解为json
restTemplate.getForEntity 返回对象为ResponseEntity对象 包含了响应中的一些重要信息 比如响应头 响应状态码 响应体等
推荐直接使用object