package cn.memedai.guava.string;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testJoiner();
		//testSplitter();
		testCharMatcher();
		//Ints.asList(1,2,3,4,5,6);
	}
	
	
	/**
	 * 连接器
	 * http://ifeve.com/google-guava-strings/
	 */
	public static void testJoiner(){
		Joiner joiner = Joiner.on(";").skipNulls();
		System.out.println(joiner.join("Harry",null,"Ron","Hermione"));
		System.out.println(Joiner.on(";").join(Arrays.asList(1, 5, 7)));;
	}
	
	/**
	 * 拆分器
	 */
	public static void testSplitter(){
		Iterable<String> iterable = Splitter.on(',').trimResults().omitEmptyStrings().limit(2)
		.split("foo,bar,,  qux");
		List<String> list = Lists.newArrayList(iterable);
		for(String ss : list){
			System.out.println(ss);
		}
	}
	
	/**
	 * 字符串匹配器
	 */
	public static void testCharMatcher(){
		String str = " go to 183 HAO ***    woefj";
		//移除control字符
		System.out.println(CharMatcher.JAVA_ISO_CONTROL.removeFrom(str));
		//只保留数字字符
		System.out.println(CharMatcher.DIGIT.retainFrom(str));
		//去除两端的空格，并把中间连续空格替换成单个空格
		System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' '));
		//用*号代替所有数字
		System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom(str, "*"));
		//只保留数字和小写字母
		System.out.println(CharMatcher.JAVA_DIGIT.or(
				CharMatcher.JAVA_LOWER_CASE).retainFrom(str));
		str.getBytes(Charsets.UTF_8);
		//我们CaseFormat在某些时候尤其有用，比如编写代码生成器的时候。
		CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");// returns "constantName"

	}
	
	
	
	
	
	
	
	

}
