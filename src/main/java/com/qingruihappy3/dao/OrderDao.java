package com.qingruihappy3.dao;

import com.qingruihappy3.annotation.ExtService;

@ExtService
public class OrderDao {
	public void addOrder() {
		System.out.println("我是OrderDao层，我在用@ExtService方法注入到容器中去。");
	}
}
