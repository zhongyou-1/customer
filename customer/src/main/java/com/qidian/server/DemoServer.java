package com.qidian.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @title 服务提供者对外暴露接口
 * @author Xrt rong tao
 * @version 1.0.0
 * @since jdk1.8
 * @创建时间：2018年11月27日下午9:00:23 @功能描述：
 */
@RestController
public class DemoServer {

	@GetMapping("server-hello")
	public String get(@PathVariable String id) {

	/***
		 * 具体业务逻辑
		 */

		return "hello----我是服务提供者 get " + id.toString();
	}

	@PostMapping("server-post")
	public String post(@PathVariable String id) {
		/***
		 * 具体业务逻辑
		 */

		return "hello----我是服务提供者 post " + id.toString();
	}
}
