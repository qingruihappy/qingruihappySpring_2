package com.qingruihappy2.utils;




public class Test001 {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
		System.out.println("userService"+userService);
		userService.add();
	}

}
