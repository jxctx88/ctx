package cn.memedai.reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.stereotype.Controller;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Reflections reflections = new Reflections("org.springframework.stereotype");
		Set<Class<? extends Annotation>> set = reflections.getSubTypesOf(Annotation.class);
		for(Class<? extends Annotation> cls : set ){
			System.out.println(cls.getCanonicalName());
		}*/
		
		Reflections reflections1 = new Reflections(new ConfigurationBuilder().
				setUrls(ClasspathHelper.forPackage("org.springframework.stereotype"))
				.setScanners(new SubTypesScanner(),
						new TypeAnnotationsScanner().filterResultsBy(
								new FilterBuilder().excludePackage("org.springframework.stereotype"))));
		int i =0;
		for(Class<?> cls : reflections1.getSubTypesOf(Annotation.class) ){
			System.out.println(i++ + "" + cls.getCanonicalName());
		}
		
		
	}

}




























