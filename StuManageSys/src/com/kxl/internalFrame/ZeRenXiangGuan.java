package com.kxl.internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class ZeRenXiangGuan extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZeRenXiangGuan frame = new ZeRenXiangGuan();
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
	public ZeRenXiangGuan() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("教师责任相关");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 50, 609, 375);
		
	}

}
