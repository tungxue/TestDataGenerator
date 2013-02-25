/**
 * 
 */
package com.tungxue.datautil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tungxue
 * 
 */
public class StringConverter {
	public static String getFixStr(String strtocon, char ch, int iLen) {
		/*
		 * 将整数转换为固定长度的字符串，头部用ch补全 第一个参数是你要转换的内容， 第二个参数是你要附加的字符， 第三个参数是你要的长度
		 */
		String target = strtocon;
		int perlen = target.length();
		for (int i = 0; i < iLen - perlen; i++)
			target = ch + target;
		return target;
	}

	public static String[] stringToArrayWithTrim(String str1) {// 将String以空格分隔成数组，并去除其中的空格。即将String以空白区域为分隔符分成数组
		String[] aa = str1.split(" ");
		List<String> tmp = new ArrayList<String>();
		for (String str : aa) {
			if (!str.endsWith(" ") && str != null && str.length() != 0) {
				tmp.add(str);
			}
		}
		aa = tmp.toArray(new String[0]);
		return aa;
	}
}
