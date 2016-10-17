package cn.memedai.test.hemcrest;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Test;

public class Demo {
	
	@Test
	public void testHemcrest(){
		//所有匹配器都匹配,大于1，并且小于15
		assertThat(2,Matchers.allOf(Matchers.greaterThan(1),Matchers.lessThan(15)));
		//匹配其中任何一个匹配器
		assertThat(7,Matchers.anyOf(Matchers.greaterThan(16),Matchers.lessThan(8)));
		//就是
		assertThat("abc",Matchers.is("abc"));
		//不等于
		assertThat("abc1",Matchers.not("abc"));
		assertThat("abc1",Matchers.containsString("abc"));
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
