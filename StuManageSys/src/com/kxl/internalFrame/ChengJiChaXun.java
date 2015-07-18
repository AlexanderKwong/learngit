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

import com.kxl.internalFrame.GeRenXinXi.CXActionListener;
import com.kxl.internalFrame.GeRenXinXi.SCActionListener;
import com.kxl.internalFrame.GeRenXinXi.TJActionListener;
import com.kxl.internalFrame.GeRenXinXi.XGActionListener;

import myDBUtil.DAOimpl;
import myDBUtil.VOClassIllegalException;
import myModel.Learning;
import myModel.LearningView;
import myModel.Student;

public class ChengJiChaXun extends JInternalFrame {

	private JTextField zuiGaoFenF;
	private JTextField zongFenF;
	private JTextField pingJunFenF;
	private JTextField keChengIDF;
	private JTextField chengJiF;
	//private JTextField xingBieF;
	//private JTextField nianJiF;
	//private JTextField xueYuanHaoF;
	//private JTextField xingMingF;
	private JTextField xueShengIDF;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox2;
	private JButton cxButton;
	private JButton tjButton;
	private JButton xgButton;
	private JButton scButton;
	
	private List<JTextField> TFlist;
	private List<LearningView> lnList;
	private DAOimpl dao;//仅用于查询显示
	private DAOimpl dao1;//用于插入和更新
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChengJiChaXun frame = new ChengJiChaXun();
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
	public ChengJiChaXun() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("考试成绩查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(50, 0, 700, 450);
		
		initPanel();
	}

	public void initPanel(){
		TFlist = new ArrayList<JTextField>(); 
		lnList = new ArrayList<LearningView>();
		try {
			dao = new DAOimpl("myModel.LearningView");
			dao1 = new DAOimpl("myModel.Learning");
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
		
		setupComponet(new JLabel("学生ID："), 0, 1, 1, 1, false);
		xueShengIDF = new JTextField();
		xueShengIDF.addKeyListener(new InputKeyListener());
		setupComponet(xueShengIDF, 1, 1, 1, 120, true);
		TFlist.add(xueShengIDF);
		
		setupComponet(new JLabel("课程ID："), 2, 1, 1, 1, false);
		keChengIDF = new JTextField();
		setupComponet(keChengIDF, 3, 1, 1, 120, true);
		TFlist.add(keChengIDF);

		setupComponet(new JLabel("成绩："), 4, 1, 1, 1, false);
		chengJiF = new JTextField();
		chengJiF.addKeyListener(new InputKeyListener());
		setupComponet(chengJiF, 5, 1, 1, 120, true);
		TFlist.add(chengJiF);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		//	table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 210));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
		
		String[] sMode = {"个人","班级（单）","班级（总）","单科","全体"};
		comboBox2 = new JComboBox();
		for(int i = 0;i < 5;i++)
		{
			comboBox2.addItem(sMode[i]);
		}
		setupComponet(comboBox2, 0, 3, 1, 1, false);
		
		
		setupComponet(new JLabel("平均分："), 0, 4, 1, 1, false);
		pingJunFenF = new JTextField();
		pingJunFenF.setEnabled(false);
		//pingJunFenF.addKeyListener(new InputKeyListener());
		setupComponet(pingJunFenF, 1, 4, 1, 0, true);
		TFlist.add(pingJunFenF);
		
		setupComponet(new JLabel("总分："), 2, 4, 1, 1, false);
		zongFenF = new JTextField();
		zongFenF.setEnabled(false);
		setupComponet(zongFenF, 3, 4, 1, 0, true);
		TFlist.add(zongFenF);
		
		setupComponet(new JLabel("最高分个人："), 4, 4, 1, 1, false);
		zuiGaoFenF = new JTextField();
		zuiGaoFenF.setEnabled(false);
	//	zuiGaoFenF.addKeyListener(new InputKeyListener());
		setupComponet(zuiGaoFenF, 5, 4, 1, 0, true);
		TFlist.add(zuiGaoFenF);
		
		cxButton = new JButton();
		cxButton.addActionListener(new CXActionListener());
		cxButton.setText("查询");
		setupComponet(cxButton, 0, 6, 1, 0, false);

		tjButton = new JButton();
		tjButton.addActionListener(new TJActionListener());
		tjButton.setText("添加");
		setupComponet(tjButton, 1, 6, 1, 0, false);
			
		xgButton = new JButton();
		xgButton.addActionListener(new XGActionListener());
		xgButton.setText("修改");
		setupComponet(xgButton, 2, 6, 1, 0, false);
			
		 scButton = new JButton();
		setupComponet(scButton, 3, 6, 1, 0, false);
		scButton.addActionListener(new SCActionListener());
		scButton.setText("删除");
		
		//为下拉状态设置监听器
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();
				//System.out.println(i);
				setCondition(i);
			}
		});
		comboBox.setSelectedItem("查询");
		
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBox2.getSelectedIndex();
				showScore(i);
			}
		});
		comboBox2.setSelectedItem("个人");
		
		// 添加窗体监听器，完成初始化
		//addInternalFrameListener(new initTasks());
	}
	
	// 初始化表格
		private void initTable() {
			String[] columnNames = {"学生ID", "姓名", "课程ID", "课程名", "年级","专业","班级","成绩"};
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
					
					int i = comboBox2.getSelectedIndex();
					showScore(i);
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
				for (JTextField jTextField : TFlist) {
					jTextField.setText("");
				}//表格以下的输入框禁止获取焦点
				for(int i = 2; i < TFlist.size();i++){
					TFlist.get(i).setEnabled(false);
				}//除查询外的按键不能按
				TFlist.get(0).setEnabled(true);
				TFlist.get(1).setEnabled(true);
				table.setEnabled(true);
				comboBox2.setEditable(true);
			//	chengJiF.setEnabled(true);
				cxButton.setEnabled(true);
				tjButton.setEnabled(false);
				xgButton.setEnabled(false);
				scButton.setEnabled(false);
				
				break;
			case 1:
				//清空所有的TextField
				for (JTextField jTextField : TFlist) {
					jTextField.setText("");
				}//表格以下的输入框禁获取焦点
				for(int i = 0; i < 3;i++){
					TFlist.get(i).setEnabled(true);
				}//除添加外的按键不能按
				table.setEnabled(false);
				table.clearSelection();
				comboBox2.setEditable(false);
				cxButton.setEnabled(false);
				tjButton.setEnabled(true);
				xgButton.setEnabled(false);
				scButton.setEnabled(false);
				break;
			case 2:
				
				//除修改与删除外的按键不能按
				TFlist.get(0).setEnabled(false);
				TFlist.get(1).setEnabled(false);
				TFlist.get(2).setEnabled(true);
				table.setEnabled(true);
				comboBox2.setEditable(true);
				cxButton.setEnabled(false);
				tjButton.setEnabled(false);
				xgButton.setEnabled(true);
				scButton.setEnabled(true);
				break;
			}
		}
		
		private void showScore(int index){
			float avg=0,sum=0;//平均分，总分
			int count=0;//记录条目
			float fenShuMax=0;
			String sNameOfMax=null;
			
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

			if(selectRows==1){
				int selectedRowIndex = table.getSelectedRow();
				
				int sID = lnList.get(selectedRowIndex).getStudentID();
				int dID = lnList.get(selectedRowIndex).getDesciplineID();
				int sGrade = lnList.get(selectedRowIndex).getsGrade();
				String sMajor = lnList.get(selectedRowIndex).getsMajor();
				int sClass = lnList.get(selectedRowIndex).getsClass();

				xueShengIDF.setText(String.valueOf(sID));
				keChengIDF.setText(String.valueOf(dID));
				chengJiF.setText(String.valueOf(lnList.get(selectedRowIndex).getScore()));
				switch(index){
				case 0://计算个人平均分、总分、并显示
					
					for (LearningView lv : lnList) {
						if(lv.getStudentID() == sID ){
							sum = sum +lv.getScore();
							count++;
						}
					}
					avg = sum/count;
					pingJunFenF.setText(String.valueOf(avg));
					zongFenF.setText(String.valueOf(sum));
					zuiGaoFenF.setText("");
					break;
				case 1://计算班级单科平均分、总分、最高分个人，并显示
					for (LearningView lv : lnList) {
						if(lv.getDesciplineID() == dID && lv.getsGrade() == sGrade && lv.getsMajor().equals(sMajor) && lv.getsClass() == sClass){
							sum = sum +lv.getScore();
							count++;
							if(lv.getScore() >fenShuMax) {
								fenShuMax = lv.getScore();
								sNameOfMax = lv.getStudentName();
							}
						}
					}
					avg = sum/count;
					pingJunFenF.setText(String.valueOf(avg));
					zongFenF.setText(String.valueOf(sum));
					zuiGaoFenF.setText(sNameOfMax);
					break;
				case 2://计算班级全部科目平均分、总分、最高分个人，并显示
					for (LearningView lv : lnList) {
						if( lv.getsGrade() == sGrade && lv.getsMajor().equals(sMajor) && lv.getsClass() == sClass){
							sum = sum +lv.getScore();
							count++;
							if(lv.getScore() >fenShuMax) {
								fenShuMax = lv.getScore();
								sNameOfMax = lv.getStudentName();
							}
						}
					}
					avg = sum/count;
					pingJunFenF.setText(String.valueOf(avg));
					zongFenF.setText(String.valueOf(sum));
					zuiGaoFenF.setText(sNameOfMax);
					break;
					
				case 3://计算全级单科平均分、总分、最高分个人，并显示
					for (LearningView lv : lnList) {
						if(lv.getDesciplineID() == dID && lv.getsGrade() == sGrade ){
							sum = sum +lv.getScore();
							count++;
							if(lv.getScore() >fenShuMax) {
								fenShuMax = lv.getScore();
								sNameOfMax = lv.getStudentName();
							}
						}
					}
					avg = sum/count;
					pingJunFenF.setText(String.valueOf(avg));
					zongFenF.setText(String.valueOf(sum));
					zuiGaoFenF.setText(sNameOfMax);
					break;
					
				case 4://计算全级全部科平均分、总分、最高分个人，并显示
					
					for (LearningView lv : lnList) {
						if(lv.getsGrade() == sGrade ){
							sum = sum +lv.getScore();
							count++;
							if(lv.getScore() >fenShuMax) {
								fenShuMax = lv.getScore();
								sNameOfMax = lv.getStudentName();
							}
						}
					}
					avg = sum/count;
					pingJunFenF.setText(String.valueOf(avg));
					zongFenF.setText(String.valueOf(sum));
					zuiGaoFenF.setText(sNameOfMax);
					break;
				}
			}
		}
		
		private void fillTable(List<LearningView> lvs){
			  DefaultTableModel tableModel = (DefaultTableModel) table
			  .getModel();
			  tableModel.setRowCount(0);// 清除原有行
			  
			  // 填充数据
			  for(LearningView lv:lvs){
			    String[] arr=new String[8];
			    arr[0]=String.valueOf(lv.getStudentID());
			    arr[1]=lv.getStudentName();
			    arr[2]=String.valueOf(lv.getDesciplineID());
			    arr[3]=lv.getDesciplineName();
			    arr[4]=String.valueOf(lv.getsGrade());
			    arr[5]=String.valueOf(lv.getsMajor());
			    arr[6]=String.valueOf(lv.getsClass());
			 	arr[7]=String.valueOf(lv.getScore());	
			    		
			    
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
				if(xueShengIDF.getText().equals("") && keChengIDF.getText().equals("") ){
					try {
						lnList = dao.getAll() ;
						fillTable(lnList);
					} catch (Exception e1) {
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "没有找到你需要找的！", "找不到！",
								JOptionPane.ERROR_MESSAGE);
						System.err.println("不能获取数据");
					}
				}else{
					//System.out.println("有东西在框里");
					ziDingYiCX();
					
				}
			}
			
		}
		 
		 private void ziDingYiCX(){
				StringBuffer sql = new StringBuffer();
				sql.append("select * from learningView where ");
				if(!xueShengIDF.getText().equals(""))
					sql.append( "studentID=" + xueShengIDF.getText()+" AND ");						
				if(!keChengIDF.getText().equals(""))
					sql.append( "desciplineID=" + keChengIDF.getText()+" AND ");
				for(int i = 5;i>0;i--)
					sql.deleteCharAt(sql.length()-1);
				
				//System.out.println(sql);
				
				try {
					lnList = dao.sqlQuery(sql.toString());
					fillTable(lnList);
				} catch (Exception e1) {
					
					//e1.printStackTrace();
				}
		 }
		//添加按键监听
		 class TJActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Learning ln = new Learning();
					ln.setStudentID(Integer.parseInt(xueShengIDF.getText()));
					ln.setDesciplineID(Integer.parseInt(keChengIDF.getText()));
					ln.setScore(Integer.parseInt(chengJiF.getText()));
					dao1.insert(ln);
					ziDingYiCX();
					JOptionPane.showMessageDialog(null, "插入成功！", "成功！",
							JOptionPane.ERROR_MESSAGE);
					
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
				//DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				try {
				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行  
				  LearningView lnV = lnList.get(selectedRowIndex);
				  String sid = String.valueOf(lnV.getStudentID());
				  String did = String.valueOf(lnV.getDesciplineID());
				  String score = String.valueOf(chengJiF.getText());
				  String sql = "update learning set score=" + score + " where studentID=" +sid +" AND " + "desciplineID=" + did;
				 // System.out.println(sql);
				  dao.sqlUpdate(sql);;
				  ziDingYiCX();
				  JOptionPane.showMessageDialog(null, "修改成功！", "成功！",
							JOptionPane.ERROR_MESSAGE);
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
				//DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

				try {
				if(selectRows==1){
				  int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行  
				  LearningView lnV = lnList.get(selectedRowIndex);
				  String sid = String.valueOf(lnV.getStudentID());
				  String did = String.valueOf(lnV.getDesciplineID());
				  String sql = "delete from learning where studentID=" +sid +" AND " + "desciplineID=" + did;
				  
				  System.out.println(sql);
					dao.sqlUpdate(sql);
					lnList.remove(selectedRowIndex);
					fillTable(lnList);
				}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "删除失败！", "失败！",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			
			
		}
		
		
	}	
}
