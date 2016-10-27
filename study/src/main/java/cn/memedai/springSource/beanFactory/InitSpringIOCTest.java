package cn.memedai.springSource.beanFactory;

import org.dozer.factory.XMLBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import cn.memedai.springSource.Iuser;
import cn.memedai.springSource.aware.TestBeanNameAware;
import cn.memedai.springSource.propertyEditor.UserManager;

public class InitSpringIOCTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ClassPathResource resource = new ClassPathResource("applicationContext.xml");
		/*ClassPathResource resource = new ClassPathResource("applicationContext.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		int i = reader.loadBeanDefinitions(resource);
		System.out.println("实例化bean的个数为："+i);
		//到目前为止还没有真正的创建实例，只是装配的一些bean的参数信息，放在GenericBeanDefinition中，下面的方法才是实例化bean
		Iuser user = (Iuser) factory.getBean("user");
		TestBeanNameAware testBeanNameAware = (TestBeanNameAware) factory.getBean("testAware");
		System.out.println(testBeanNameAware.beanName);
		user.hello("历史");*/
		
		ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
		Iuser user = (Iuser) application.getBean("user");
		user.hello(null);
		
		/*ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("applicationContext-propertyEditor.xml");
		UserManager userManager = (UserManager) application.getBean("userManager");
		System.out.println(userManager);*/
	
		
	/*	ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext-beanfactorypostprocessor.xml"));
		BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
		bfpp.postProcessBeanFactory(bf);
		System.out.println(bf.getBean("simpleBean"));*/
	
	}
	
	
	

}
