/**
 * 
 */
package com.tungxue.main;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

import com.tungxue.datautil.BigIntegerGet;
import com.tungxue.datautil.StringConverter;
import com.tungxue.fileutil.FileADRW;

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
		int divtime = Integer
				.valueOf(textfield_array[textfield_array.length - 1]) % 1000 == 0 ? (Integer
				.valueOf(textfield_array[textfield_array.length - 1]) / 1000)
				: (Integer.valueOf(textfield_array[textfield_array.length - 1]) / 1000 + 1);// 分成几部分循环是为了加快字符串的连接速度，不然两个太长的字符串相加会很慢
		String str = "";
		for (int j = 0; j <= divtime - 1; j++) {
			String strtemp = "";
			for (int i = 1; i <= 1000
					&& j * 1000 + i <= Integer
							.valueOf(textfield_array[textfield_array.length - 1]); i++) {
				String strtem = "INSERT INTO "
						+ textfield_array[textfield_array.length - 2]
						+ " VALUES (";
				for (int k = 0; k < comboboxarrayselectedindex_array.length; k++) {
					strtem = strtem
							+ "'"
							+ calculate_str(textfield_array[k],
									comboboxarrayselectedindex_array[k], j
											* 1000 + i) + "',";
				}
				strtem = strtem.substring(0, strtem.length() - 1);
				strtem = strtem + ");" + "\r\n";
				strtemp = strtemp + strtem;
				completednum_label.setText(String.valueOf(j * 1000 + i));
			}
			str = str + strtemp;
		}
		String path = new File(fieldpath.substring(0, fieldpath.length() - 4)
				+ "_sql_" + textfield_array[textfield_array.length - 1]
				+ ".sql").getAbsolutePath();
		FileADRW.createFile(path);
		FileADRW.writeFile(str, path);
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
				+ StringConverter.getFixStr(
						BigIntegerGet.getAdd(str_array[1],
								String.valueOf(reachnum)), '0',
						Integer.valueOf(str_array[2]) - str_array[0].length());

	}

	private String createStringInPREFIX_DATE(String[] str_array, int reachnum) {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String datetime = tempDate.format(new java.util.Date());// 委托日期
		return datetime
				+ StringConverter.getFixStr(
						BigIntegerGet.getAdd(str_array[0],
								String.valueOf(reachnum)), '0',
						Integer.valueOf(str_array[1]) - datetime.length());
	}

	private String createStringInRANDOM_CONTENT(String[] str_array) {
		return str_array[(int) (Math.random() * str_array.length)];
	}

	private String createStringInINC_NUM(String[] str_array, int reachnum) {
		return StringConverter.getFixStr(
				BigIntegerGet.getAdd(str_array[0], String.valueOf(reachnum)),
				'0', Integer.valueOf(str_array[1]));
	}

	private String createStringInDEC_NUM(String[] str_array, int reachnum) {
		return StringConverter.getFixStr(
				BigIntegerGet.getSubtract(str_array[0],
						String.valueOf(reachnum)), '0',
				Integer.valueOf(str_array[1]));
	}

	private String createStringInRANDOM_NUM(String[] str_array) {
		String str = "";
		for (int i = 1; i <= Integer.valueOf(str_array[0]) / 9; i++) {
			str = str + String.valueOf((int) (Math.random() * 999999999));
		}
		str = str
				+ String.valueOf((int) (Math.random() * Integer
						.valueOf(StringConverter.getFixStr("", '9',
								Integer.valueOf(str_array[0]) % 9))));
		str = StringConverter
				.getFixStr(str, '9', Integer.valueOf(str_array[0]));
		return str;
	}
}