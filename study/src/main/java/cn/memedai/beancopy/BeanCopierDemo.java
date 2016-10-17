package cn.memedai.beancopy;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import cn.memedai.easymock.IStudent;
import cn.memedai.easymock.StudentApplication;

public class BeanCopierDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanCopier beanCopier = BeanCopier.create(IStudent.class, StudentApplication.class, false);
		//beanCopier.copy(obj, obj1, null);
		//BeanMap.create(bean)
	}

}
