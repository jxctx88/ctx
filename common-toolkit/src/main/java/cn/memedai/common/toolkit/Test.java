package cn.memedai.common.toolkit;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(DateUtils.addMinutes(new Date(), -2).toString());

	}

}
