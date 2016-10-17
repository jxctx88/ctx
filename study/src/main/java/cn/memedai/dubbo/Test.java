package cn.memedai.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.memedai.ess.facade.beans.params.download.DownloadParams;
import cn.memedai.ess.facade.beans.results.download.DownloadResult;
import cn.memedai.ess.facade.service.ElectorSignFacade;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ElectorSignFacade electorSignFacade = (ElectorSignFacade) context.getBean("electorSignFacade");
		DownloadParams downloadParams = new DownloadParams();
		//安存：HG1VV188ZO40OZ0RJ28O,YPMF1OO1BBJYAX11TTG1
		//上上签：1464073756536VQPU2,1464075327696HOKP2,1464076850875XE4W2
		downloadParams.setRecordNo("1465981653684AGIG2");
		downloadParams.setSignChannel("01");
		downloadParams.setStoredPath("d:\\");
		DownloadResult downloadResult = electorSignFacade.download(downloadParams);
		System.out.println(downloadResult.toString());
	}

}
