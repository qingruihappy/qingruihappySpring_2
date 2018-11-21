package com.qingruihappy3.service.impl;

import com.qingruihappy3.annotation.ExtResource;
import com.qingruihappy3.annotation.ExtService;
import com.qingruihappy3.dao.UserDao;
import com.qingruihappy3.service.OrderService;
import com.qingruihappy3.service.UserService;



//将该类注入到spring容器里面
@ExtService
public class UserServiceImpl implements UserService {
	// 从Spring容器中读取bean
	@ExtResource
	private UserDao userDao;

	public void add() {
		userDao.add();
		System.out.println("我是UserServiceImpl，我在通过@ExtResource把UserDao取出来");
	}

}
