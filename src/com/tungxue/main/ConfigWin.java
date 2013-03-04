/**
 * 
 */
package com.tungxue.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.tungxue.fileutil.FileADRW;
import com.tungxue.fileutil.XMLADRW;
import com.tungxue.wincon.JComboBoxCon;
import com.tungxue.wincon.JFrameCon;

/**
 * @author tungxue
 * 
 */
public class ConfigWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	String[] itemarray = { "自定前缀", "日期前缀", "自定随机", "固定内容", "递增数字", "递减数字",
			"随机数字", "空" };
	String[] itemexplanation_array = { "前缀 起始数 位数", "起始数 位数", "随机内容，用空格隔开",
			"固定内容", "起始数 位数", "起始数 位数", "位数", "不用输入，留空" };
	public static final int PREFIX_SELF = 0;
	public static final int PREFIX_DATE = 1;
	public static final int RANDOM_CONTENT = 2;
	public static final int FIX = 3;
	public static final int INC_NUM = 4;
	public static final int DEC_NUM = 5;
	public static final int RANDOM_NUM = 6;
	public static final int NULL_CONTENT = 7;
	String[] columnHeadStr = { "字段名称", "生成基数",
			"                 基数格式                 ", "生成方式" };
	String filepath = "";// 字段文件位置
	String[] field_array = null;// 字段组成的数组
	JTextField[] ini_textfield = null;// 待输入信息标签组
	JTextField table_textfield = null;// 输入表名
	JTextField sqlnum_textfield = new JTextField();// 生成的sql语句数
	JLabel completednum_label = new JLabel();// 已完成数提示
	JLabel[] itemexplanation_label = null;
	@SuppressWarnings("rawtypes")
	JComboBox[] comboboxs_array = null;

	private JPanel createPanelNorth() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("表名："));
		table_textfield = new JTextField();
		table_textfield.setColumns(20);
		panel.add(table_textfield);
		panel.validate();
		return panel;
	}

	private JScrollPane createPanelCenter() {
		/*
		 * 窗口中间内容布局，设置为表格布局
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(field_array.length + 1, 4, 1, 2));// 多出一行是抬头标签用的
		JLabel[] columnHeadLab = { new JLabel(columnHeadStr[0]),
				new JLabel(columnHeadStr[1]), new JLabel(columnHeadStr[2]),
				new JLabel(columnHeadStr[3]) };// 列抬头标签
		for (int i = 0; i < columnHeadLab.length; i++) {
			columnHeadLab[i].setHorizontalAlignment(JTextField.CENTER);
			panel.add(columnHeadLab[i]);
		}
		for (int i = 0; i < field_array.length; i++) {
			JLabel jlabel = new JLabel(field_array[i] + ":" + " ");
			jlabel.setHorizontalAlignment(JTextField.RIGHT);
			panel.add(jlabel);
			ini_textfield[i] = new JTextField();
			panel.add(ini_textfield[i]);
			itemexplanation_label[i] = new JLabel("前缀 起始数 位数");
			panel.add(itemexplanation_label[i]);
			panel.add(comboboxs_array[i]);
		}
		JScrollPane scrollpane = new JScrollPane(panel);
		panel.validate();
		scrollpane.validate();
		return scrollpane;
	}

	private JPanel createPanelSouth() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel panelwest = new JPanel();
		panelwest.setLayout(new GridLayout(2, 2));
		JLabel label1 = new JLabel("生成数目： ");
		label1.setHorizontalAlignment(JLabel.RIGHT);
		panelwest.add(label1);
		panelwest.add(sqlnum_textfield);
		JLabel label2 = new JLabel("进程提示： ");
		label2.setHorizontalAlignment(JLabel.RIGHT);
		panelwest.add(label2);
		panelwest.add(completednum_label);
		panel.add(panelwest, BorderLayout.WEST);
		JButton buttoneast = new JButton("开始生产");
		buttoneast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!Pattern.compile("\\d+")
						.matcher(sqlnum_textfield.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "请注意：生成数目框必须输入一个正整数",
							"提示", JOptionPane.ERROR_MESSAGE);
				} else {
					for (int j = 0; j < field_array.length; j++) {
						if (!JComboBoxCon.textFieldCheckedByComboBox(
								comboboxs_array[j], ini_textfield[j]).equals(
								"1")) {
							JOptionPane.showMessageDialog(
									null,
									field_array[j]
											+ "处"
											+ JComboBoxCon
													.textFieldCheckedByComboBox(
															comboboxs_array[j],
															ini_textfield[j]),
									"提示", JOptionPane.ERROR_MESSAGE);
							break;
						} else if (j == field_array.length - 1) {
							String[] textfield_array = new String[field_array.length + 2];
							for (int i = 1; i <= field_array.length; i++) {
								textfield_array[i - 1] = ini_textfield[i - 1]
										.getText();
							}
							textfield_array[field_array.length] = table_textfield
									.getText();// 最后第二个放的是表名
							textfield_array[field_array.length + 1] = sqlnum_textfield
									.getText();// 最后一个放的是要生成的sql数
							new Thread(
									new Producing(
											textfield_array,
											JComboBoxCon
													.getComboBoxArraySelectedIndex(comboboxs_array),
											completednum_label, filepath))
									.start();
						}
					}
				}
			}
		});
		panel.add(buttoneast, BorderLayout.CENTER);
		JButton button = new JButton("打开新的字段文件");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ConfigWin.this.setVisible(false);
				new StrartWin();
			}
		});
		panel.add(button, BorderLayout.EAST);
		panel.validate();
		return panel;
	}

	public ConfigWin(String filepath) {
		// TODO Auto-generated constructor stub
		this.filepath = filepath;
		this.field_array = FileADRW.readFile(filepath);
		XMLADRW.createXML(
				new File(filepath).getName().substring(0,
						new File(filepath).getName().length() - 4)
						+ ".xml", "字段", field_array, itemarray);
		ini_textfield = new JTextField[field_array.length];
		itemexplanation_label = new JLabel[field_array.length];
		this.comboboxs_array = JComboBoxCon.createComboBoxArray(
				field_array.length, itemarray, itemexplanation_label,
				itemexplanation_array);
		this.setTitle("压力测试数据生成器v2.0 数据配置");
		this.getContentPane().setLayout(new BorderLayout());
		this.add(createPanelNorth(), BorderLayout.NORTH);
		this.add(createPanelCenter(), BorderLayout.CENTER);
		this.add(createPanelSouth(), BorderLayout.SOUTH);
		this.validate();
		this.setVisible(true);
		if (field_array.length <= 17) {
			JFrameCon.setSizeAndCentralizeMe(this, 0, 0);
			this.pack();
		} else {
			JFrameCon.setSizeAndCentralizeMe(this, 600, 700);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击窗口右上角的关闭按钮关闭窗口并退出程序
	}

}
