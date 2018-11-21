package com.qingruihappy3.dao;

import com.qingruihappy3.annotation.ExtService;

@ExtService
public class UserDao {
	public void add() {
		System.out.println("我是UserDao层，我在用@ExtService方法注入到容器中去。");
	}
}
