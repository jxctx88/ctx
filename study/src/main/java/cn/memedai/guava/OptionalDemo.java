package cn.memedai.guava;

import com.google.common.base.Optional;

public class OptionalDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = null;
		Optional<String> possible = Optional.of(str);
		//Optional<String> possible = Optional.fromNullable(null);
		//Optional<Integer> possible = Optional.of(5);
		System.out.println(possible.isPresent());;
		System.out.println(possible.or("333"));;
		System.out.println(possible.get());;

	}

}
