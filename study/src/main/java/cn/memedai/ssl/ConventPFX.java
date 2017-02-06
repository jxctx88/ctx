package cn.memedai.ssl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * 将PFX格式转换为jks格式
 * @author tongxiong.cheng
 * @date 2017-1-4 上午9:14:23
 * @version 1.0
 */
public class ConventPFX {
	public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "D:\\81231007392205090.pfx";
    public static final String KEYSTORE_PASSWORD = "vpos123";
    //转换为jks(java使用的私钥证书)
    public static final String JKS_KEYSTORE_FILE = "D:\\81231007392205090.jks";
    public static void coverTokeyStore() {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE);
            char[] nPassword = null;
           
            if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")){
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }
            inputKeyStore.load(fis, nPassword);
            fis.close();
            KeyStore outputKeyStore = KeyStore.getInstance("JKS");
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
    public static void main(String[] args) {
        coverTokeyStore();
    }
}
