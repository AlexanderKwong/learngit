package com.kxl.internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class JiangChengXinXi extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JiangChengXinXi frame = new JiangChengXinXi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JiangChengXinXi() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("奖惩信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 50, 609, 375);
		
	}

}
