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
		//testCheck();
		//testObjects();
		//ComparisonChain Ordering
		//ComparisonChain比较器，对应对象比较非常有用,Ordering.natural()按照字典排序 
		//nullsFirst 使用当前排序器，但额外把null值排到最前面。
		System.out
				.println(ComparisonChain.start()
						.compare("12", "1", Ordering.natural().nullsFirst())
						.result());;
	}

	/**
	 * 前置检查
	 */
	public static void testCheck(){
		Preconditions.checkArgument("".equals("111"));
		String  checkStr =  null;
		System.out.println(Preconditions.checkNotNull(checkStr,"%s不能为空",checkStr));
	}
	
	public static void testObjects(){
		//toString
		//System.out.println(Objects.toStringHelper().add("x", 1));;
	}
	
	public void testException(){
		try{
			
		}catch(RuntimeException | Error e){
			Throwables.propagateIfInstanceOf(e, RuntimeException.class);
		}
	}
}
