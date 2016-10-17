package cn.memedai.secure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class CertificateConvert {
	public static void main(String[] args) {
		pfx_ConvetTo_JKS();
	}
	
	
	/**
	 * pfx转换为JKS
	 */
	public static void pfx_ConvetTo_JKS(){
		String PKCS12 = "PKCS12";
	    String JKS = "JKS";
	    String PFX_KEYSTORE_FILE = "C:/Users/admin/Desktop/项目文件/渤海银行代付/生产证书/mime.pfx";
	    String KEYSTORE_PASSWORD = "***";
	    //转换为jks(java使用的私钥证书)
	    String JKS_KEYSTORE_FILE = "C:/Users/admin/Desktop/项目文件/渤海银行代付/生产证书/mime.jks";
	        try {
	            KeyStore inputKeyStore = KeyStore.getInstance(PKCS12);
	            FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE);
	            char[] nPassword = null;
	           
	            if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")){
	                nPassword = null;
	            } else {
	                nPassword = KEYSTORE_PASSWORD.toCharArray();
	            }
	            inputKeyStore.load(fis, nPassword);
	            fis.close();
	            KeyStore outputKeyStore = KeyStore.getInstance(JKS);
	            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
	            Enumeration enums = inputKeyStore.aliases();
	            while (enums.hasMoreElements()) {
	                String keyAlias = (String) enums.nextElement();
	                System.out.println("alias=[" + keyAlias + "]");
	                if (inputKeyStore.isKeyEntry(keyAlias)) {
	                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
	                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
	                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD.toCharArray(), certChain);
	                }  
	            }  
	            FileOutputStream out = new FileOutputStream(JKS_KEYSTORE_FILE);
	            outputKeyStore.store(out, nPassword);
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}


