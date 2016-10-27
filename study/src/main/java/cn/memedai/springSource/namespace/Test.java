package cn.memedai.springSource.namespace;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import cn.memedai.springSource.Iuser;

public class Test {
	public static void main(String[] args) {
		/*ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-namespace.xml");
		User user = (User) context.getBean("user");
		System.out.println(user.getUserName()+","+user.getEmail());*/
		
		ClassPathResource resource = new ClassPathResource("applicationContext-namespace.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		int i = reader.loadBeanDefinitions(resource);
		System.out.println("实例化bean的个数为："+i);
		//到目前为止还没有真正的创建实例，只是装配的一些bean的参数信息，放在GenericBeanDefinition中，下面的方法才是实例化bean
		User user = (User) factory.getBean("user");
		System.out.println(user.getUserName()+","+user.getEmail());
	}
}
