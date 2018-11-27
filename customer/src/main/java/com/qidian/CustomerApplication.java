package com.qidian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableDiscoveryClient //启用服务注册与发现 （声明是个提供者）
@SpringBootApplication  //标明本类 启动类
@EnableFeignClients //声明这个一个服务消费者。(//启用feign进行远程调用)
@EnableTransactionManagement //开启扫描
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
