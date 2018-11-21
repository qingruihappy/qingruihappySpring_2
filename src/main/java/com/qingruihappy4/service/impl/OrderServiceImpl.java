package com.qingruihappy4.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qingruihappy4.dao.OrderDao;
import com.qingruihappy4.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	// 从Spring容器中读取bean
	@Resource
	private OrderDao orderDao;

	public void addOrder() {
		orderDao.addOrder();
		System.out.println("我是OrderServiceImpl，我在通过@Resource把orderDao取出来");
	}
}
