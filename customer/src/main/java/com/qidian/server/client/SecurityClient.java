package com.qidian.server.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
@FeignClient(name="serviceSecurity")//所要消费的提供者名称
public interface SecurityClient {

	/**
	 * server-hello 为提供者定义的访问地址
	 * @param id
	 * @return
	 */
	 @RequestMapping(value = "hello",method = RequestMethod.GET)
	 public String hello();
	 
	 
}
