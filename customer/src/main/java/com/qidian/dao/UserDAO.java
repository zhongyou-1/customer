package com.qidian.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qidian.entity.User;

@Repository
@Transactional
public class UserDAO extends BaseDAO {

	
	/**
	 * 查询 信息
	 * @param personalId
	 * @return
	 */
	public User queryCustomerPersonalInfoById(String id)
	{
		User result = manager.find(User.class, id);
		return result;
	}
	
	/**
	 * 修改用户
	 * @param User 
	 */
	@Transactional
	public boolean  update(User user) {
		try{
			//user.setModuleInfo(info);
			manager.merge(user);
			manager.flush();
			
		}catch (Exception e) {
			log.error("更改用户发生错误", e);
			return false;
		}
		return  true;
	}
	
	/**
	 * 插入
	 * @param user
	 * @return
	 */
	public boolean insert(User user){
		
		try {
			manager.persist( user);
			manager.flush();
			return true ;
		} catch (Exception e) {
			log.error("新增用户发生异常", e);
			return false;
		}
		
		
	}
	
	/***
	 * 删除 
	 * @param user
	 * @return
	 */
	public boolean remove(User user){
		
		try {
			manager.remove(user);
			manager.flush();
			return true ;
		} catch (Exception e) {
			log.error("删除用户发生异常", e);
			return false;
		}
		
	}
   
	
}
