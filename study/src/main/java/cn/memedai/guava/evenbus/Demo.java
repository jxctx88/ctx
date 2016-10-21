package cn.memedai.guava.evenbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBus eventBus = new EventBus();
		eventBus.register(new MessageScreen());
		eventBus.post("hello world!");

	}

}


class MessageScreen{
	@Subscribe
	public void printMessage(String message){
		System.out.println(message);
	}
}