/**
 * 
 */
package com.tungxue.wincon;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author tungxue
 *
 */
public class JFrameCon {
	public static void setSizeAndCentralizeMe(JFrame frame,int width, int height) {
		/*
		 * 设置窗口大小与置中
		 */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(width!=0&&height!=0)
		frame.setSize(width, height);
		frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height
				/ 2 - height / 2);
	}
}
