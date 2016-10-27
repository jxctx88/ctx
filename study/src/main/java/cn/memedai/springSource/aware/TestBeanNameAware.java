package cn.memedai.springSource.aware;

import org.springframework.beans.factory.BeanNameAware;

public class TestBeanNameAware implements BeanNameAware{
	public static String beanName;
	
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		
	}

}
