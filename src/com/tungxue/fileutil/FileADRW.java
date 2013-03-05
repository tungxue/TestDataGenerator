/**
 * 
 */
package com.tungxue.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tungxue
 * 
 */
public class FileADRW {
	public static void createDir(String path) {
		File dirname = new File(path);
		if (dirname.exists() == false) {
			dirname.mkdirs();
		}
	}

	public static boolean createFile(String path) {
		/*
		 * 根据路径生成文件
		 */
		File filename = new File(path);
		String dirpath = path.substring(0, path.length()
				- filename.getName().length());
		File dirpathname = new File(dirpath);
		if (dirpathname.exists() == false) {
			dirpathname.mkdirs();
		}
		if (!filename.exists()) {
			try {
				filename.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return true;
		} else {
			return true;
		}
	}

	public static void writeFile(String str, String writepath) {
		/*
		 * 将字符串写入文件
		 */
		try {
			FileWriter fw = new FileWriter(writepath);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] readFile(String path) {
		String str = "";
		try {
			// BufferedReader readTxt = new BufferedReader(new FileReader(new
			// File(path)));
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					path), "GBK");
			BufferedReader readTxt = new BufferedReader(isr);
			String textLine = "";
			while ((textLine = readTxt.readLine()) != null) {
				str += textLine;
			}
			readTxt.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.split("\\.");
	}
}
