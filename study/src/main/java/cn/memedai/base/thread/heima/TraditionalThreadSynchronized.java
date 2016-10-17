package cn.memedai.base.thread.heima;

public class TraditionalThreadSynchronized {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
		

	}
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			public void run(){
				while(true){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outputer.output2("zhangxiaoxiang");
			}
			}
		}).start();
		new Thread(new Runnable(){
			public void run(){
				while(true){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outputer.output2("lihuoming");
			}
			}
		}).start();
		
	}
	static class Outputer{
		
		String xxx="";
		public void output(String name){
			int len = name.length();
//			synchronized (this) {
//				
//			}
			synchronized (Outputer.class) {
				
		
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		}
		public void output2(String name){
			int len = name.length();

		
				
		
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		
		public static synchronized void output3(String name){
			int len = name.length();

			
				
		
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		
		
		
	}

}
