package com.qingruihappy4.dao;

import org.springframework.stereotype.Service;

@Service
public class UserDao {
	public void add() {
		System.out.println("我是UserDao层，我在用@Service方法注入到容器中去。");
	}
}
