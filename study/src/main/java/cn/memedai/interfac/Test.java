package cn.memedai.interfac;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Persion persion = new YoungMan();
		Man man = null;
		if(persion instanceof Man){
			man = ((Man)persion);
		}
		man.run();
	}

}
