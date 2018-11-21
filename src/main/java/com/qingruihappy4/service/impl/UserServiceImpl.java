package com.qingruihappy4.service.impl;

import com.qingruihappy4.dao.UserDao;
import com.qingruihappy4.service.UserService;



//将该类注入到spring容器里面
//@Service
public class UserServiceImpl implements UserService {
	// 从Spring容器中读取bean
	//@Resource
	private UserDao userDao;

	public void add() {
		userDao.add();
		System.out.println("我是UserServiceImpl，我在通过@ExtResource把UserDao取出来");
	}
}
