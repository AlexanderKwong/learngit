package com.kxl.internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class WoDeQuanXian extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WoDeQuanXian frame = new WoDeQuanXian();
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
	public WoDeQuanXian() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("我的权限查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 50, 609, 375);
		
	}

}
