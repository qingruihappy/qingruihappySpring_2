package com.qingruihappy4.dao;

import org.springframework.stereotype.Service;

@Service
public class OrderDao {
	public void addOrder() {
		System.out.println("我是OrderDao层，我在用@Service方法注入到容器中去。");
	}
}
