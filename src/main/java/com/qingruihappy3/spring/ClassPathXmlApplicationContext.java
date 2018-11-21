package com.qingruihappy3.spring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.qingruihappy3.annotation.ExtResource;
import com.qingruihappy3.annotation.ExtService;
import com.qingruihappy3.utils.ClassUtil;

/**
 * 手写Spring专题 注解版本注入bean
 * 
 * @author 黄庆瑞
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClassPathXmlApplicationContext {
	// 扫包范围
	private String packageName;

	public ClassPathXmlApplicationContext(String packageName) {
		this.packageName = packageName;
	}

	// 把带有@service注解的对象放到map中去
	public ConcurrentHashMap<String, Object> setMap() throws Exception {
		// 1.使用反射机制获取该包下所有的类已经存在bean的注解类,放到一个list集合中
		List<Class> listClassesAnnotation = findClassExisService();
		if (listClassesAnnotation == null || listClassesAnnotation.isEmpty()) {
			throw new Exception("没有需要初始化的bean");
		}
		// 2.使用Java反射机制初始化对象，在放到一个map集合中，map的可以就是对象名称的首字母小写，value就是加注解的对象
		ConcurrentHashMap<String, Object> initBean = initBean(listClassesAnnotation);
		if (initBean == null || initBean.isEmpty()) {
			throw new Exception("初始化bean为空!");
		}
		return initBean;

	}

	// 使用beanID查找对象
	public Object getBean(ConcurrentHashMap<String, Object> initBean,String beanId) throws Exception {

		// 3.使用beanID查找查找对应bean对象
		Object object = initBean.get(beanId);
		// 4.使用反射读取类的属性,赋值信息,这里是有问题的。
		attriAssign(initBean,object);
		return object;
	}

	// 使用反射读取类的属性,赋值信息
	public void attriAssign(ConcurrentHashMap<String, Object> initBean,Object object) throws Exception {
		// 1.获取类的属性是否存在 获取bean注解
		Class<? extends Object> classInfo = object.getClass();
		Field[] declaredFields = classInfo.getDeclaredFields();

		for (Field field : declaredFields) {
			ExtResource annotation = field.getAnnotation(ExtResource.class);
			if (annotation != null) {
				// 属性名称
				String name = field.getName();
				// 2.使用属性名称查找bean容器赋值
				Object bean = initBean.get(name);
				if (bean != null) {
					// 私有访问允许访问
					field.setAccessible(true);
					// 给属性赋值，给object（userserviceimpl）的属性field（userdao）赋值bean（从容器中拿到到userdao）
					field.set(object, bean);
					continue;
				}
			} else {
				throw new Exception("ExtResource没有注入到" + field.getName() + "方法上去");
			}
		}

	}

	// 使用反射机制获取该包下所有的类已经存在bean的注解类
	public List<Class> findClassExisService() throws Exception {
		// 1.使用反射机制获取该包下所有的类
		if (StringUtils.isEmpty(packageName)) {
			throw new Exception("扫包地址不能为空!");
		}
		// 2.使用反射技术获取当前包下所有的类
		List<Class<?>> classesByPackageName = ClassUtil.getClasses(packageName);
		// 3.存放类上有bean注入注解
		List<Class> exisClassesAnnotation = new ArrayList<Class>();
		// 4.判断该类上属否存在注解
		for (Class classInfo : classesByPackageName) {
			// 确定就是这个注解
			ExtService extService = (ExtService) classInfo.getAnnotation(ExtService.class);
			if (extService != null) {
				exisClassesAnnotation.add(classInfo);
				continue;
			}
		}
		return exisClassesAnnotation;
	}

	// 初始化bean对象
	public ConcurrentHashMap<String, Object> initBean(List<Class> listClassesAnnotation)
			throws InstantiationException, IllegalAccessException {
		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, Object>();
		for (Class classInfo : listClassesAnnotation) {
			// 初始化对象
			Object newInstance = classInfo.newInstance();
			// 转成小写
			String beanId = toLowerCaseFirstOne(classInfo.getSimpleName());
			concurrentHashMap.put(beanId, newInstance);
		}
		return concurrentHashMap;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
