package cn.memedai.guava.range;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class Demo {

	/**
	 * @param args
	 * 区间
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Range.closed(3, 4).contains(4));
		System.out.println(Range.openClosed(3, 4).contains(4));
		System.out.println(Range.closedOpen(3, 4).contains(4));
		System.out.println(Range.lessThan(4).contains(4));
		System.out.println(Range.closed(1,4).containsAll(Ints.asList(1,2,3)));
	}

}
