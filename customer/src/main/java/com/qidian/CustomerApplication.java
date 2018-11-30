package com.qidian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@EnableDiscoveryClient // 启用服务注册与发现 （声明是个提供者）
@SpringBootApplication // 标明本类 启动类
@EnableFeignClients // 声明这个一个服务消费者。(//启用feign进行远程调用)
@EnableTransactionManagement // 开启扫描
@EnableHystrixDashboard // 开启hystrixDashboard 开启短路器
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	
	/***
	 * 实现  (断路器：Hystrix 仪表盘)
	 * 第一步  配一个servlet， （ restTemplate getServlet)
	 *  1浏览器打开, http://localhost:9091/hystrix 
	 *  2，打开页面中输入框填入   http://localhost:9091/actuator/hystrix.stream
	 *  3，点击 stream
	 * @return 
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/actuator/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
		}
}
