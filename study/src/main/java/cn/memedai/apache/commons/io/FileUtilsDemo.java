package cn.memedai.apache.commons.io;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileUtilsDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(FileUtils.getTempDirectoryPath());
		System.out.println(FileUtils.getUserDirectoryPath());
		FileOutputStream fos = FileUtils.openOutputStream(new File("d:/1.txt"),Boolean.TRUE);
		fos.write("hello world".getBytes(Charset.forName("UTF-8")));
		fos.flush();
		System.out.println(IOUtils.LINE_SEPARATOR);
		System.out.println(1);
		fos.write((IOUtils.LINE_SEPARATOR+"hello world").getBytes(Charset.forName("UTF-8")));
		//fos.close();
		
		//FileUtils.touch(new File("d:/111/test"));
	}

}
