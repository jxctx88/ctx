package cn.memedai.springSource.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean {

	public Object getObject() throws Exception {
		return new MyFactoryBeanImpl();  
	}

	public Class getObjectType() {
		return MyFactoryBeanImpl.class;
	}

	public boolean isSingleton() {
		 return false;  
	}

}
