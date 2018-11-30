package com.qidian.server.hystric;

import org.springframework.stereotype.Component;

import com.qidian.server.client.SecurityClient;
@Component
public class DemoHystric  implements SecurityClient{

	
	@Override
	public String hello() {
		// TODO Auto-generated method stub
		
		// 只要执行该 方法 代表  断路器起到作用了。
		 return "sorry ,抱歉该提供者可能已经停止";
	}

}
