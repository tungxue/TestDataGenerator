/**
 * 
 */
package com.tungxue.fileutil;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * @author tungxue
 * 
 */
public class SystemFile {
	public static void newfile(String filename) {// 寻找目录后将指定文件名以txt存入
		JFileChooser jf = new JFileChooser();
		jf.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.isDirectory()) {
					return true;
				}
				if (f.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "*.txt";
			}
		});
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		String f = fi.getAbsolutePath() + "\\" + filename + ".txt";
		FileADRW.createFile(f);
	}

	public static void openFileAndDir(String path) {
		File file = new File(path);
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException ev) {
				// TODO Auto-generated catch block
				ev.printStackTrace();
			}
		}
	}

	public static String findFile(String title) {
		// 首先是创建JFileChooser 对象，里面带个参数，表示默认打开的目录，这里是默认打开当前文件所在的目录。
		JFileChooser file = new JFileChooser(
				javax.swing.filechooser.FileSystemView.getFileSystemView()
						.getHomeDirectory());// 默认显示桌面目录
		file.setSelectedFile(new File("fielddata.txt"));// 默认选择文件名
		file.setDialogTitle(title);
		file.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.isDirectory()) {
					return true;
				}
				if (f.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "*.txt";
			}
		});
		/*
		 * 使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
		 * 整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口
		 */
		int result = file.showOpenDialog(null);
		// JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
		if (result == JFileChooser.APPROVE_OPTION) {
			// 获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。
			FileADRW.createFile(file.getSelectedFile().getAbsolutePath());
			return file.getSelectedFile().getAbsolutePath();
		} else {
			return "";
		}
	}
}
