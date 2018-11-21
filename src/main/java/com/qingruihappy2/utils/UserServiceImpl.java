package com.qingruihappy2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//user 服务层
public class UserServiceImpl implements UserService {
	// 声明：@Transactional 或者XML方式
	// 方法执行开始执行前，开启提交事务
	@Transactional
	public void add() {
		System.out.println("调用到我了，说明通了");
	}

}
