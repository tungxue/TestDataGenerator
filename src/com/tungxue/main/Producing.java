/**
 * 
 */
package com.tungxue.main;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;

import com.tungxue.datautil.StringConverter;

/**
 * @author tungxue
 * 
 */
public class Producing implements Runnable {// 生产线程
	String[] textfield_array;// 生成基数，最后第二个放的是表名,最后一个是需要生成的sql数
	int[] comboboxarrayselectedindex_array;// 被选择生成方式的序号，参见ConfigWin类中的itemarray
	JLabel completednum_label;// 已完成数提示
	String fieldpath;// 字段文件的路径

	Producing(String[] textfield_array, int[] comboboxarrayselectedindex_array,
			JLabel completednum_label, String fieldpath) {
		this.textfield_array = textfield_array;
		this.comboboxarrayselectedindex_array = comboboxarrayselectedindex_array;
		this.completednum_label = completednum_label;
		this.fieldpath = fieldpath;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Integer
				.valueOf(textfield_array[textfield_array.length - 1]); i++) {
			String str = "INSERT INTO "
					+ textfield_array[textfield_array.length - 2] + "VALUES (";
			for (int j = 0; j < comboboxarrayselectedindex_array.length; j++) {
				str = str
						+ "'"
						+ calculate_str(textfield_array[j],
								comboboxarrayselectedindex_array[j], i) + "',";
			}
			str = str.substring(0, str.length() - 1);
			str = str + ");";
			completednum_label.setText(String.valueOf(i + 1));
		}
	}

	private String calculate_str(String itemexplanation,
			int comboboxselectedindex, int reachnum) {
		String[] str_array = StringConverter
				.stringToArrayWithTrim(itemexplanation);
		String str = "";
		switch (comboboxselectedindex) {
		case ConfigWin.PREFIX_SELF:
			str = createStringInPREFIX_SELF(str_array, reachnum);
			break;
		case ConfigWin.PREFIX_DATE:
			str = createStringInPREFIX_DATE(str_array, reachnum);
			break;
		case ConfigWin.RANDOM_CONTENT:
			str = createStringInRANDOM_CONTENT(str_array);
			break;
		case ConfigWin.FIX:
			str = str_array[0];
			break;
		case ConfigWin.INC_NUM:
			str = createStringInINC_NUM(str_array, reachnum);
			break;
		case ConfigWin.DEC_NUM:
			str = createStringInDEC_NUM(str_array, reachnum);
			break;
		case ConfigWin.RANDOM_NUM:
			str = createStringInRANDOM_NUM(str_array);
			break;
		case ConfigWin.NULL_CONTENT:
			str = "";
			break;
		}
		return str;
	}

	private String createStringInPREFIX_SELF(String[] str_array, int reachnum) {
		return str_array[0]
				+ StringConverter.getFixStr(String.valueOf(Integer
						.valueOf(str_array[1]) + reachnum), '0',
						Integer.valueOf(str_array[2]) - str_array[0].length());

	}

	private String createStringInPREFIX_DATE(String[] str_array, int reachnum) {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String datetime = tempDate.format(new java.util.Date());// 委托日期
		return datetime
				+ StringConverter.getFixStr(String.valueOf(Integer
						.valueOf(str_array[0]) + reachnum), '0',
						Integer.valueOf(str_array[1]) - datetime.length());
	}

	private String createStringInRANDOM_CONTENT(String[] str_array) {
		return str_array[(int) (Math.random() * str_array.length)];
	}

	private String createStringInINC_NUM(String[] str_array, int reachnum) {
		return StringConverter.getFixStr(
				String.valueOf(Integer.valueOf(str_array[0]) + reachnum), '0',
				Integer.valueOf(str_array[1]));
	}

	private String createStringInDEC_NUM(String[] str_array, int reachnum) {
		return StringConverter.getFixStr(
				String.valueOf(reachnum - Integer.valueOf(str_array[0])), '0',
				Integer.valueOf(str_array[1]));
	}

	private String createStringInRANDOM_NUM(String[] str_array) {
		return String.valueOf(Math.random() * Integer.valueOf(str_array[0]));
	}
}