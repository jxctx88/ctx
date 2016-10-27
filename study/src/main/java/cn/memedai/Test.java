package cn.memedai;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.google.common.base.Splitter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "rspCd=0000|rspMsg=成功";
		String param = Splitter.on('|').splitToList(str).get(0);
		System.out.println(Splitter.on('=').splitToList(param).get(1));
		
	}

}
