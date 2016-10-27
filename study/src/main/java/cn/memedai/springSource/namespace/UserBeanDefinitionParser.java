package cn.memedai.springSource.namespace;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends
		AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}
	
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		String userName = element.getAttribute("userName");//用户名
		String email = element.getAttribute("email");//邮箱
		//将提取的数据放入到BeanDefinitionBuilder中，待到完成所有bean的解析后统一注册到beanFactory中
		if(StringUtils.hasText(userName)){
			bean.addPropertyValue("userName", userName);
		}
		if(StringUtils.hasText(email)){
			bean.addPropertyValue("email", email);
		}
	
	}
}
