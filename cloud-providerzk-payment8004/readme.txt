zookeeper:
1. pom
        <!-- SpringBoot整合zookeeper客户端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper3.5.3 就是说zookeeper-discovery包中的zookeeper版本和电脑上安装的zookeeper版本不一样-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本 即你自己服务器上安装的zookeeper版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.9</version>
        </dependency>

2. yaml
spring:
  application:
    name: cloud-provider-payment # 服务别名----注册zookeeper到注册中心名称
  cloud:
    zookeeper:
      connect-string: {zookeeper server ip}:{zookeeper server port}
#在zookeeper是临时节点

其余的无差别