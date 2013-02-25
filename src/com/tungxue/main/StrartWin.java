/**
 * 
 */
package com.tungxue.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tungxue.fileutil.SystemFile;
import com.tungxue.wincon.JFrameCon;

/**
 * @author tungxue
 * 
 */
public class StrartWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JTextField filepath_textfield = new JTextField();// 字段文件路径
	JButton findfile_button = new JButton("寻找字段文件");
	JButton ok_button = new JButton("确定");

	private JPanel createPanelNorth() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("字段文件路径: "));
		filepath_textfield.setColumns(44);
		panel.add(filepath_textfield);
		return panel;
	}

	private JPanel createPanelSouth() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JLabel label = new JLabel(
				"<html><body><p>字段文件内容格式规定：</p><br><p>1、文件名可以自定（建议为表名），但必须为txt文件。</p><br><p>2、将字段逐个输入，字段间用空格“ ”隔开。</p><body></html>");
		panel.add(label);
		ok_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!filepath_textfield.getText().endsWith(".txt")) {
					JOptionPane.showMessageDialog(null, "对不起！当前的字段文件路径不是有效的！",
							"提示", JOptionPane.WARNING_MESSAGE);			
				} else {
					StrartWin.this.setVisible(false);
					new ConfigWin(filepath_textfield.getText());
				}
			}
		});
		panel.add(ok_button);
		panel.validate();
		return panel;
	}

	private JPanel createPanelWest() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(
				"<html><body><p>使用帮助:</p><br><p>1、点击“寻找字段文件”按钮将字段文件载入系统，之后点击“确定”按钮即可。</p><br><p>2、若之前已有字段文件，则直接选择就可以了。反之，则在打开的“寻找字段</p><br><p>文件”窗口的文件名输入框输入文件名新建一个即可。</p><body></html>"));
		return panel;
	}

	private JPanel createPanelEast() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		findfile_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filepath = SystemFile.findFile("寻找字段文件");
				if (!filepath.equals("")) {
					filepath_textfield.setText(filepath);
					if (filepath.endsWith(".txt"))
						SystemFile.openFileAndDir(filepath);
					else {
						filepath_textfield.setText("对不起，该路径不是有效的！");
						JOptionPane.showMessageDialog(null,
								"对不起！该字段文件路径不是有效的！", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panel.add(findfile_button);
		panel.validate();
		return panel;
	}

	public StrartWin() {
		// TODO Auto-generated constructor stub
		this.setTitle("压力测试数据生成器v2.0");
		JFrameCon.setSizeAndCentralizeMe(this, 600, 290);
		this.getContentPane().setLayout(new BorderLayout());
		this.add(createPanelNorth(), BorderLayout.NORTH);
		this.add(createPanelSouth(), BorderLayout.SOUTH);
		this.add(createPanelWest(), BorderLayout.WEST);
		this.add(createPanelEast(), BorderLayout.EAST);
		this.validate();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击窗口右上角的关闭按钮关闭窗口并退出程序
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StrartWin();
	}

}
