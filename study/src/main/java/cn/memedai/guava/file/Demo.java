package cn.memedai.guava.file;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File newFile = new File("");
		try {
			Files.write("".getBytes(), newFile);
			Files.createTempDir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
