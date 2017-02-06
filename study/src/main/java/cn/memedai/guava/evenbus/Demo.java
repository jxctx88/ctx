package cn.memedai.guava.evenbus;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBus eventBus = new EventBus();
		eventBus.register(new MessageScreen());
		for(int i=0;i<10;i++){
			eventBus.post("hello world!");
		}
		//多线程异步事件总线
		AsyncEventBus  asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
		asyncEventBus.register(new MessageScreen());
		for(int i=0;i<10;i++){
			asyncEventBus.post("hello world!");
		}
	}

}


class MessageScreen{
	@Subscribe
	public void printMessage(String message){
		System.out.println(Thread.currentThread().getName()+";"+message);
	}
	
	/**
	 * 你可以注册一个处理方法专门处理DeadEvent类型的事件。
	 * 每当EventBus收到没有对应处理方法的事件，它都会将其转化为DeadEvent，
	 * 并且传递给你注册的DeadEvent处理方法——你可以选择记录或修复该事件。
	 * @param deadEvent
	 */
	@Subscribe
	public void dealDeadEven(DeadEvent deadEvent){
		System.out.println(deadEvent.getEvent()+":"+deadEvent.getSource());
	}
}