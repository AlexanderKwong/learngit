package com.kxl.internalFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import keyListener.InputKeyListener;
import myDBUtil.DAOimpl;
import myDBUtil.VOClassIllegalException;
import myModel.Descipline;
import myModel.Student;

import com.kxl.internalFrame.GeRenXinXi.CXActionListener;
import com.kxl.internalFrame.GeRenXinXi.SCActionListener;
import com.kxl.internalFrame.GeRenXinXi.TJActionListener;
import com.kxl.internalFrame.GeRenXinXi.XGActionListener;

public class KeBiaoChaXun extends JInternalFrame {

	private JTextField keChengIDF;
	private JTextField keChengMingF;
	private JTextField xueShiF;
	private JTextField xueFenF;
//	private JTextField shengRiF;
//	private JTextField xingBieF;
//	private JTextField nianJiF;
//	private JTextField xueYuanHaoF;
//	private JTextField xingMingF;
//	private JTextField IDF;
	private JTable table;
	private JComboBox comboBox;
	private JButton cxButton;
	private JButton tjButton;
	private JButton xgButton;
	private JButton scButton;
	
	//private List<JTextField> TFlist;
	private List<Descipline> dclList;
	private DAOimpl dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeBiaoChaXun frame = new KeBiaoChaXun();
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
	public KeBiaoChaXun() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("学生课表查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 0, 600, 450);
		
		initPanel();
	}

	public void initPanel(){
		//TFlist = new ArrayList<JTextField>(); 
		dclList = new ArrayList<Descipline>();
		try {
			dao = new DAOimpl("myModel.Descipline");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (VOClassIllegalException e1) {
			e1.printStackTrace();
		}
		
		String[] crud = {"查询","添加","修改"};
		comboBox = new JComboBox();
		for(int i = 0;i < 3;i++)
		{
			comboBox.addItem(crud[i]);
		}
		
		
		setupComponet(comboBox, 0, 0, 1, 1, false);
		
		setupComponet(new JLabel("课程ID："), 0, 1, 1, 1, false);
		keChengIDF = new JTextField();
		keChengIDF.addKeyListener(new InputKeyListener());
		setupComponet(keChengIDF, 1, 1, 1, 160, true);
		//TFlist.add(IDF);
		
		setupComponet(new JLabel("课程名称："), 2, 1, 1, 1, false);
		keChengMingF = new JTextField();
		setupComponet(keChengMingF, 3, 1, 1, 160, true);
		//TFlist.add(xingMingF);

		setupComponet(new JLabel("学时："), 0, 2, 1, 1, false);
		xueShiF = new JTextField();
		xueShiF.addKeyListener(new InputKeyListener());
		setupComponet(xueShiF, 1, 2, 1, 0, true);
		//TFlist.add(xueYuanHaoF);

		setupComponet(new JLabel("学分："), 2, 2, 1, 1, false);
		xueFenF = new JTextField();
		xueFenF.addKeyListener(new InputKeyListener());
		setupComponet(xueFenF, 3, 2, 1, 0, true);
		//TFlist.add(nianJiF);
		
	
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 270));
		setupComponet(scrollPanel, 0, 3, 6, 1, true);
		
		cxButton = new JButton();
		cxButton.addActionListener(new CXActionListener());
		cxButton.setText("查询");
		setupComponet(cxButton, 0, 7, 1, 0, false);

		tjButton = new JButton();
		tjButton.addActionListener(new TJActionListener());
		tjButton.setText("添加");
		setupComponet(tjButton, 1, 7, 1, 0, false);
			
		xgButton = new JButton();
		xgButton.addActionListener(new XGActionListener());
		xgButton.setText("修改");
		setupComponet(xgButton, 2, 7, 1, 0, false);
			
		 scButton = new JButton();
		setupComponet(scButton, 3, 7, 1, 0, false);
		scButton.addActionListener(new SCActionListener());
		scButton.setText("删除");
		
		//为下拉状态设置监听器
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();
			//	System.out.println(i);
				setCondition(i);
			}
		});
		comboBox.setSelectedItem("查询");
		
		// 添加窗体监听器，完成初始化
		//addInternalFrameListener(new initTasks());
	}
	
	// 初始化表格
		private void initTable() {
			String[] columnNames = {"课程ID", "课程名称", "学时", "学分"};
			((DefaultTableModel) table.getModel())
					.setColumnIdentifiers(columnNames);
			table.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

					if(selectRows==1){
					  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行  
					  keChengIDF.setText(String.valueOf(dclList.get(selectedRowIndex).getDesciplineID()));
					  keChengMingF.setText(String.valueOf(dclList.get(selectedRowIndex).getDesciplineName()));
					  xueShiF.setText(String.valueOf(dclList.get(selectedRowIndex).getdTime()));
					  xueFenF.setText(String.valueOf(dclList.get(selectedRowIndex).getdCredit()));
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
				}
				
			});
		}
	
	
	
	// 设置组件位置并添加到容器中
		private void setupComponet(JComponent component, int gridx, int gridy,
				int gridwidth, int ipadx, boolean fill) {
			final GridBagConstraints gridBagConstrains = new GridBagConstraints();
			gridBagConstrains.gridx = gridx;
			gridBagConstrains.gridy = gridy;
			if (gridwidth > 1)
				gridBagConstrains.gridwidth = gridwidth;
			if (ipadx > 0)
				gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.insets = new Insets(5, 1, 3, 1);
			if (fill)
				gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
			add(component, gridBagConstrains);
		}
		
		//设置控件的状态
		private void setCondition(int index ){
			switch(index){
			case 0:
				//清空所有的TextField
				//除查询外的按键不能按
				keChengIDF.setEnabled(true);
				
				table.setEnabled(true);
				cxButton.setEnabled(true);
				tjButton.setEnabled(false);
				xgButton.setEnabled(false);
				scButton.setEnabled(false);
				
				break;
			case 1:
				//清空所有的TextField
				keChengIDF.setText("");
				keChengMingF.setText("");
				xueShiF.setText("");
				xueFenF.setText("");
				
				keChengIDF.setEnabled(false);
				
				table.setEnabled(false);
				table.clearSelection();
				cxButton.setEnabled(false);
				tjButton.setEnabled(true);
				xgButton.setEnabled(false);
				scButton.setEnabled(false);
				break;
			case 2:
				keChengIDF.setEnabled(false);
				
				//除修改与删除外的按键不能按
				table.setEnabled(true);
				cxButton.setEnabled(false);
				tjButton.setEnabled(false);
				xgButton.setEnabled(true);
				scButton.setEnabled(true);
				break;
			}
		}
	
		private void fillTable(List<Descipline> dcls){
			  DefaultTableModel tableModel = (DefaultTableModel) table
			  .getModel();
			  tableModel.setRowCount(0);// 清除原有行
			  
			  // 填充数据
			  for(Descipline dcl:dcls){
			    String[] arr=new String[4];
			    arr[0]=String.valueOf(dcl.getDesciplineID());
			    arr[1]=dcl.getDesciplineName();
			    arr[2]=String.valueOf(dcl.getdTime());
			    arr[3]=String.valueOf(dcl.getdCredit());
			   
			    		
			    
			    // 添加数据到表格
			    tableModel.addRow(arr);
			  }
			  
			  // 更新表格
			  table.invalidate();
		}
		
		//查询按键监听
		 class CXActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//如果前4个TF内无数据，查询全部
				if(keChengIDF.getText().equals("") && keChengMingF.getText().equals("") && xueShiF.getText().equals("") && xueFenF.getText().equals("")){
					try {
						dclList = dao.getAll() ;
						fillTable(dclList);
					} catch (Exception e1) {
						//e1.printStackTrace();
						System.err.println("不能获取数据");
					}
				}else{
					System.out.println("有东西在框里");
					StringBuffer sql = new StringBuffer();
					sql.append("select * from descipline where ");
					if(!keChengIDF.getText().equals(""))
						sql.append( "desciplineID=" + keChengIDF.getText()+" AND ");						
					if(!keChengMingF.getText().equals(""))
						sql.append( "desciplineName=\'" + keChengMingF.getText()+"\' AND ");	
					if(!xueShiF.getText().equals(""))
						sql.append( "dTime=" + xueShiF.getText()+" AND ");	
					if(!xueFenF.getText().equals(""))
						sql.append( "dCredit=" + xueFenF.getText()+" AND ");
					for(int i = 5;i>0;i--)
						sql.deleteCharAt(sql.length()-1);
					
					System.out.println(sql);
					
					try {
						dclList = dao.sqlQuery(sql.toString());
						fillTable(dclList);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "没有找到你需要找的那位！", "找不到！",
								JOptionPane.ERROR_MESSAGE);
						//e1.printStackTrace();
					}
					
				}
			}
			
		}
		//添加按键监听
		 class TJActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Descipline dcl = new Descipline();
					dcl.setDesciplineName(keChengMingF.getText());
					dcl.setdTime(Integer.parseInt(xueShiF.getText()));
					dcl.setdCredit(Integer.parseInt(xueFenF.getText()));
					dao.insert(dcl);
					dclList.add(dcl);
					fillTable(dclList);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "插入失败！", "失败！",
							JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
			}
			
		}
		//修改按键监听
		 class XGActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				try {
				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行  
				  Descipline dcl = dclList.get(selectedRowIndex);
				  dcl.setDesciplineName(keChengMingF.getText());
				  dcl.setdTime(Integer.parseInt(xueShiF.getText()));
				  dcl.setdCredit(Integer.parseInt(xueFenF.getText()));
				  dao.update(dcl);
				  dclList.remove(selectedRowIndex);
				  dclList.add(dcl);
				  fillTable(dclList);
				  
				  keChengIDF.setText("");
				  keChengMingF.setText("");
				  xueShiF.setText("");
				  xueFenF.setText("");
				}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "修改失败！", "失败！",
							JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
				
				
			}
			
		}
		//删除按键监听
		 class SCActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行  
				  Descipline dcl = dclList.get(selectedRowIndex);
				  try {
					dao.delete(String.valueOf(dcl.getDesciplineID()));
					dclList.remove(selectedRowIndex);
					fillTable(dclList);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "删除失败！", "失败！",
							JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
				
				
			}
			
		}
		
		
	}	
}
