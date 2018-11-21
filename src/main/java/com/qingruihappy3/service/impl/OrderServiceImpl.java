package com.qingruihappy3.service.impl;

import com.qingruihappy3.annotation.ExtResource;
import com.qingruihappy3.annotation.ExtService;
import com.qingruihappy3.dao.OrderDao;
import com.qingruihappy3.service.OrderService;

@ExtService
public class OrderServiceImpl implements OrderService {

	// 从Spring容器中读取bean
	@ExtResource
	private OrderDao orderDao;

	public void addOrder() {
		orderDao.addOrder();
		System.out.println("我是OrderServiceImpl，我在通过@ExtResource把orderDao取出来");
	}
}
