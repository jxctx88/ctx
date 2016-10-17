package cn.memedai.guava.check;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Preconditions.checkNotNull("","不能为空%s","33d"));
		new Demo().testObjects();
		//ComparisonChain Ordering
		ComparisonChain.start().compare("", "",Ordering.natural().nullsFirst()).result();
	}

	
	public void testObjects(){
		//toString
		System.out.println(Objects.toStringHelper(this).add("x", 1));;
	}
	
	public void testException(){
		try{
			
		}catch(RuntimeException | Error e){
			Throwables.propagateIfInstanceOf(e, RuntimeException.class);
		}
	}
}
