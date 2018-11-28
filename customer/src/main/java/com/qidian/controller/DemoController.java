package com.qidian.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qidian.entity.User;
import com.qidian.server.client.SecurityClient;

/**
 * 
 * @title 测试类
 * @author Xrt rong tao
 * @version 1.0.0
 * @since jdk1.8
 * @创建时间：2018年11月27日下午7:40:15 @功能描述：
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	protected static Logger logger = LoggerFactory.getLogger(DemoController.class);
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private com.qidian.dao.UserDAO userDAO;
	
	@Autowired
	private  SecurityClient securityClient;
	
	@RequestMapping("/testServer")
	@ResponseBody
	public String testServer( ) {
		 
		logger.info("进来了--------------》》》》");
		long sys = System.currentTimeMillis();
		for (int i = 0; i <10000; i++) {
			System.out.println(securityClient.hello( ));
		}
		long end = System.currentTimeMillis();
		return " 1W台冲击结束,耗时："+(end-sys);
	}
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		logger.debug("访问getUserByName,Name={}");
		List<User> list = manager.createQuery("select o from User o ", User.class).getResultList();
		logger.error("----------info," + list.size());
		return "name is 大家好 ";
	}

	@RequestMapping("/initdata")
	@ResponseBody
	public String initData() {
        
        User user = new User();
        user.setName("管理员");
        user.setLoginName("admin");
        userDAO.insert(user);
        Assert.notNull(user.getId(),"用户ID不能为空！");
		return "success";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update() {
        
        User user = manager.find(User.class, Long.parseLong("15"));
        user.setName("update By");
        userDAO.update(user);
		return "success";
	}
	@RequestMapping("/remove")
	@ResponseBody
	public String remove() {
        
        User user = manager.find(User.class, Long.parseLong("16"));//指定key
       
        userDAO.remove(user);
		return "success";
	}
}
