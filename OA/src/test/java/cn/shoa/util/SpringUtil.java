package cn.shoa.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	public static ApplicationContext ac;
	static {
		ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");	
	}

}
