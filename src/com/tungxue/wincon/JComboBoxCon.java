/**
 * 
 */
package com.tungxue.wincon;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.tungxue.datautil.StringConverter;

/**
 * @author tungxue
 * 
 */
public class JComboBoxCon {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox[] createComboBoxArray(int num, String[] strarray,
			final JLabel[] label_array, final String[] itemexplanation_array) {// 生成num个带有strarray选项的下拉菜单，并对它们添加监听器，当某个菜单的选项改变时在相应的Label显示相应的说明
		JComboBox[] comboBox_array = new JComboBox[num];
		for (int i = 1; i <= num; i++) {
			final int j = i - 1;
			comboBox_array[i - 1] = new JComboBox(strarray);
			comboBox_array[i - 1].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.SELECTED) {
						JComboBox jcb = (JComboBox) e.getSource();
						label_array[j].setText(itemexplanation_array[jcb
								.getSelectedIndex()]);
					}
				}
			});
		}
		return comboBox_array;
	}

	@SuppressWarnings("rawtypes")
	public static String textFieldCheckedByComboBox(JComboBox comboboxarray,
			JTextField textfieldarray) {
		switch (comboboxarray.getSelectedIndex()) {
		case 0:
			if (StringConverter.stringToArrayWithTrim(textfieldarray.getText()).length != 3) {
				return "请注意自定前缀的基数格式为：前缀 起始数 位数(数字为正整数)";
			} else {
				boolean b1 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[1]).matches();
				boolean b2 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[2]).matches();
				if (b1 && b2) {
					return "1";
				} else {
					return "请注意自定前缀的基数格式为：前缀 起始数 位数(数字为正整数)";
				}
			}
		case 1:
			if (StringConverter.stringToArrayWithTrim(textfieldarray.getText()).length != 2) {
				return "请注意日期前缀的基数格式为：起始数 位数(数字为正整数)";
			} else {
				boolean b3 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[0]).matches();
				boolean b4 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[1]).matches();
				if (b3 && b4) {
					return "1";
				} else {
					return "请注意日期前缀的基数格式为：起始数 位数(数字为正整数)";
				}
			}
		case 2:
			return "1";
		case 3:
			return "1";
		case 4:
			if (StringConverter.stringToArrayWithTrim(textfieldarray.getText()).length != 2) {
				return "请注意递增数字的基数格式为：起始数 位数(数字为正整数)";
			} else {
				boolean b5 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[0]).matches();
				boolean b6 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[1]).matches();
				if (b5 && b6) {
					return "1";
				} else {
					return "请注意递增数字的基数格式为：起始数 位数(数字为正整数)";
				}
			}
		case 5:
			if (StringConverter.stringToArrayWithTrim(textfieldarray.getText()).length != 2) {
				return "请注意递减数字的基数格式为：起始数 位数(数字为正整数)";
			} else {
				boolean b7 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[0]).matches();
				boolean b8 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[1]).matches();
				if (b7 && b8) {
					return "1";
				} else {
					return "请注意递减数字的基数格式为：起始数 位数(数字为正整数)";
				}
			}
		case 6:
			if (StringConverter.stringToArrayWithTrim(textfieldarray.getText()).length != 1) {
				return "请注意随机数字的基数格式为：位数(数字为正整数)";
			} else {
				boolean b9 = Pattern
						.compile("\\d+")
						.matcher(
								StringConverter
										.stringToArrayWithTrim(textfieldarray
												.getText())[0]).matches();
				if (b9) {
					return "1";
				} else {
					return "请注意随机数字的基数格式为：位数(数字为正整数)";
				}
			}
		case 7:
			return "1";
		default:
			return "1";
		}
	}

	@SuppressWarnings("rawtypes")
	public static int[] getComboBoxArraySelectedIndex(JComboBox[] comboboxarray) {
		int[] index_array = new int[comboboxarray.length];
		for (int j = 1; j <= comboboxarray.length; j++) {
			index_array[j - 1] = comboboxarray[j - 1].getSelectedIndex();
		}
		return index_array;
	}
}
