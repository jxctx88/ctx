package cn.memedai.springSource.system;


public class SystemDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));//java版本号
		System.out.println(System.getProperty("java.home"));//jre目录
		System.out.println(System.getProperty("os.name"));//操作系统名称
		System.out.println(System.getProperty("os.version"));//操作系统版本号
		System.out.println(System.getProperty("user.name"));//用户名
		System.out.println(System.getProperty("user.home"));//用户的主目录
		System.out.println(System.getProperty("user.dir"));//用户的当前工作目录
		System.out.println(System.getProperties().toString());
	}

}
