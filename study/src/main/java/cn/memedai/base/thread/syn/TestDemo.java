package cn.memedai.base.thread.syn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String c = "3";
		String a = "3";
		String b = "3";
		System.out.println(a==b);
		/*List<String> list = new LinkedList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		ListIterator<String> lsitIterator =  list.listIterator(list.size());
		while(lsitIterator.hasPrevious()){
			System.out.println(lsitIterator.previous());
		}
		Collections.synchronizedList(new ArrayList<String>());
		Vector vector = new Vector();*/
		/*Stack<String> stack = new Stack<String>();
		stack.push("aa");
		stack.push("bb");
		stack.push("cc");
		stack.push("dd");*/
		//System.out.println(stack.pop());
		/*Iterator iterator = stack.iterator();*/
		/*while(iterator.hasNext()){
			System.out.println(iterator.next());
		}*/
		/*while(!stack.empty()){
			System.out.println(stack.pop());
		}*/
		/*for(String s:stack){
			System.out.println(s);
		}*/
		/*Map<String,String> map = new TreeMap<String,String>();
		for(int i=0;i<5;i++){
			map.put(i+"", "aa"+i);
		}
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Map.Entry<String, String> entry:set){
			System.out.println(entry.getKey()+": "+entry.getValue());
		}*/
		/*ClassLoader cl =Test1.class.getClassLoader();
		while(cl!=null){
			System.out.println(cl.getClass().getName());
			cl=cl.getParent();
		}*/
		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						outputer();
					}
				}
			}).start();
		}
		//将计划转换为同步的计划
		Collection<String> coll = new ArrayList<String>();
		Collections.synchronizedCollection(coll);
	}
	private static Integer i = 0;
	public static void outputer(){
		System.out.println("===aaa===");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(i){
		System.out.println("￥￥￥￥bbb￥￥￥￥");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("######ccc#####");
		}
		
	}
	
}
