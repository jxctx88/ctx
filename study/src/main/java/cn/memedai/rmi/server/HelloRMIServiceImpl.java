package cn.memedai.rmi.server;

public class HelloRMIServiceImpl implements HelloRMIService {

	@Override
	public int add(int a, int b) {
		System.out.println("调用服务方法begin。。。");
		return a+b;
	}

}
