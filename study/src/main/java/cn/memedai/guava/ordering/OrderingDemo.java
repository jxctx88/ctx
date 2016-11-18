package cn.memedai.guava.ordering;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

/**
 * 排序器
 * @author tongxiong.cheng
 *
 */
public class OrderingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ordering<Integer> order = Ordering.natural().nullsFirst();
		List<Integer> list = new ArrayList<Integer>(){{
			this.add(3);
			this.add(1);
			this.add(5);
		}};
		System.out.println(order.reverse().sortedCopy(list));
		System.out.println(order.sortedCopy(list));
		
		Ordering<Persion> order1 = Ordering.natural().onResultOf(new Function<Persion, String>() {
			@Override
			public String apply(Persion input) {
				return input.getName();
			}
			
		});
		
		Persion persion1 = new Persion("xiong",3);
		Persion persion2 = new Persion("tong",1);
		Persion persion3 = new Persion("cheng",5);
		List<Persion> persionList = new ArrayList<>();
		persionList.add(persion1);
		persionList.add(persion2);
		persionList.add(persion3);
		List<Persion> list1 = order1.sortedCopy(persionList);
		for(Persion per : list1){
			System.out.println(per.getName()+":" + per.getAge());
		}
	}
	
	
	

}

class Persion{
	public Persion() {}
	
	public Persion(String name ,int age) {
		this.name = name;
		this.age = age;
	}
	
	private String name ;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}