package com.qidian.server.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qidian.server.hystric.DemoHystric;

/***
 * 
 * @title 
 * @author Xrt rong tao
 * @version 1.0.0
 * @since jdk1.8
 * @创建时间：2018年11月27日下午9:59:55
 * @功能描述： 这里的name 名称是提供者application.yml 中
 * application:
    name 的名称（serviceSecurity）
 */
/* value="serviceSecurity"  所要消费的提供者名称
 * fallback = DemoHystric.class
 * DemoHystric 是开启 断路器，当提供者崩溃或者连接超时则响应自定义提示
 * SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中，
 * 开启使用断路器的前提是设置（feign.hystrix.enabled=true）默认是false
 * 作用：
 
 * 当 serviceSecurity工程不可用的时候，customer-调用 serviceSecurity的API接口时，
 * 会执行快速失败，直接返回一组字符串，而不是等待响应超时，这很好的控制了容器的线程阻塞。
 * */
@FeignClient(value="serviceSecurity",fallback = DemoHystric.class)
public interface SecurityClient {

	/**
	 * server-hello 为提供者定义的访问地址
	 * @param id
	 * @return
	 */
	 @RequestMapping(value = "hello",method = RequestMethod.GET)
	 public String hello();
	 
	 
}
