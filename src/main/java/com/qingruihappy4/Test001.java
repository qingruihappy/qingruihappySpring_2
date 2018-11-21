package com.qingruihappy4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qingruihappy4.service.OrderService;
import com.qingruihappy4.service.UserService;

public class Test001 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
		//假如我们现在放到mvc的模式中，我们会发现启动的时候就会去加载spring的xml文件，去扫包。注意扫包的时候并没有把通过@Service往容器中放值
		//启动的时候就会通过另一套机制加载发现有@Service注解，面向切面的思想，就会把它放到容器中去。
		//当开始访问的时候就会通过@Resource注解，面向切面的思想就会从容器中去取对应的实例，没有就会报空指针异常
		
		UserService userService = (UserService) app.getBean("userServiceImpl");
		userService.add();
		OrderService orderService = (OrderService) app.getBean("orderServiceImpl");
		orderService.addOrder();
	}

}
