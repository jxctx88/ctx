package cn.memedai.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;

/**
 * 不可变集合
 * @author tongxiong.cheng
 * @date 2016-10-21 下午3:24:50
 * @version 1.0
 */
public class ImmutableCollection {

	public static void main(String[] args) {
		ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red","orange","yellow","green","blue","purple");
		System.out.println(COLOR_NAMES.asList().get(0));
	
		Multiset set = HashMultiset.create();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("b");
		System.out.println(set.count("b"));
		System.out.println(set.count("a"));
		System.out.println(set.size());
		System.out.println(set.elementSet().size());
	}
	
}
