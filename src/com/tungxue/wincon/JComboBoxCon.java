/**
 * 
 */
package com.tungxue.wincon;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

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
	public static int[] getComboBoxArraySelectedIndex(JComboBox[] comboboxarray) {
		int[] index_array = new int[comboboxarray.length];
		for (int j = 1; j <= comboboxarray.length; j++) {
			index_array[j - 1] = comboboxarray[j - 1].getSelectedIndex();
		}
		return index_array;
	}
}
