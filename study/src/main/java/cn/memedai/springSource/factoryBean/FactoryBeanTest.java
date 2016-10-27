package cn.memedai.springSource.factoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-FactoryBean.xml");
		/*MyFactoryBeanImpl bean = (MyFactoryBeanImpl) context.getBean("myFactoryBean", MyFactoryBeanImpl.class);    
        System.out.println("普通bean的获取："+bean);    
          
        MyFactoryBean factoryBean = (MyFactoryBean) context.getBean("&myFactoryBean", MyFactoryBean.class);   
        System.out.println("工厂bean的获取："+factoryBean);   
          
        bean = (MyFactoryBeanImpl) factoryBean.getObject();  
        System.out.println(bean);*/   
        
        
        PersonService ps = context.getBean("myLogFactoryBean", PersonService.class);    
        
        ps.sayHello();    
       
//      String name = ps.getName();    
       
//      System.out.println(name);

	}

}
