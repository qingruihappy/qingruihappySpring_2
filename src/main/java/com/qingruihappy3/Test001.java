package com.qingruihappy3;

import com.qingruihappy3.service.UserService;
import com.qingruihappy3.spring.ClassPathXmlApplicationContext;

import java.util.concurrent.ConcurrentHashMap;

import com.qingruihappy3.service.OrderService;

public class Test001 {

	public static void main(String[] args) throws Exception {
		//1,扫包
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("com.qingruihappy3");
		//2,把有service注解的对象放到map容器中来，（其实这一步可以一并放在步骤3中实现的，但是为了好理解一下，单独拆出来了）
		ConcurrentHashMap<String, Object> map = app.setMap();
		//3,从容器中取出值来（注意这个时候不仅要取userServiceImpl对象，还要取userdao对象的），并且给它的带有resource注解的属性值（userdao）赋值
		UserService userService = (UserService) app.getBean(map,"userServiceImpl");
		userService.add();
		OrderService orderService = (OrderService) app.getBean(map,"orderServiceImpl");
		orderService.addOrder();
	}

}
