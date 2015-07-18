package com.kxl.internalFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import myDBUtil.DAOimpl;
import myDBUtil.VO;
import myDBUtil.VOClassIllegalException;
import myModel.Student;
import keyListener.InputKeyListener;

public class GeRenXinXi extends JInternalFrame {
	
	private JTextField dianHuaF;
	private JTextField jiGuanF;
	private JTextField banHaoF;
	private JTextField zhuanYeF;
	private JTextField shengRiF;
	private JTextField xingBieF;
	private JTextField nianJiF;
	private JTextField xueYuanHaoF;
	private JTextField xingMingF;
	private JTextField IDF;
	private JTable table;
	private JComboBox comboBox;
	private JButton cxButton;
	private JButton tjButton;
	private JButton xgButton;
	private JButton scButton;
	
	private List<JTextField> TFlist;
	private List<Student> stuList;
	private DAOimpl dao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeRenXinXi frame = new GeRenXinXi();
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
	public GeRenXinXi() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("个人信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 0, 600, 450);
		
		initPanel();
	}

	public void initPanel(){
		TFlist = new ArrayList<JTextField>(); 
		stuList = new ArrayList<Student>();
		try {
			dao = new DAOimpl("myModel.Student");
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
		
		setupComponet(new JLabel("ID："), 0, 1, 1, 1, false);
		IDF = new JTextField();
		IDF.addKeyListener(new InputKeyListener());
		setupComponet(IDF, 1, 1, 1, 160, true);
		TFlist.add(IDF);
		
		setupComponet(new JLabel("姓名："), 2, 1, 1, 1, false);
		xingMingF = new JTextField();
		setupComponet(xingMingF, 3, 1, 1, 160, true);
		TFlist.add(xingMingF);

		setupComponet(new JLabel("学院号："), 0, 2, 1, 1, false);
		xueYuanHaoF = new JTextField();
		xueYuanHaoF.addKeyListener(new InputKeyListener());
		setupComponet(xueYuanHaoF, 1, 2, 1, 0, true);
		TFlist.add(xueYuanHaoF);

		setupComponet(new JLabel("年级："), 2, 2, 1, 1, false);
		nianJiF = new JTextField();
		nianJiF.addKeyListener(new InputKeyListener());
		setupComponet(nianJiF, 3, 2, 1, 0, true);
		TFlist.add(nianJiF);
		
	
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
	//	table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 170));
		setupComponet(scrollPanel, 0, 3, 6, 1, true);
		
		setupComponet(new JLabel("性别："), 0, 4, 1, 1, false);
		xingBieF = new JTextField();
		setupComponet(xingBieF, 1, 4, 1, 0, true);
		TFlist.add(xingBieF);
		
		setupComponet(new JLabel("生日："), 2, 4, 1, 1, false);
		shengRiF = new JTextField();
		//shengRiF.addKeyListener(new InputKeyListener());
		setupComponet(shengRiF, 3, 4, 1, 0, true);
		TFlist.add(shengRiF);
		
		setupComponet(new JLabel("专业："), 0, 5, 1, 1, false);
		zhuanYeF = new JTextField();
		setupComponet(zhuanYeF, 1, 5, 1, 0, true);
		TFlist.add(zhuanYeF);
		
		setupComponet(new JLabel("班号："), 2, 5, 1, 1, false);
		banHaoF = new JTextField();
		banHaoF.addKeyListener(new InputKeyListener());
		setupComponet(banHaoF, 3, 5, 1, 0, true);
		TFlist.add(banHaoF);
		
		setupComponet(new JLabel("籍贯："), 0, 6, 1, 1, false);
		jiGuanF = new JTextField();
		setupComponet(jiGuanF, 1, 6, 1, 0, true);
		TFlist.add(jiGuanF);
		
		setupComponet(new JLabel("联系方式："), 2, 6, 1, 1, false);
		dianHuaF = new JTextField();
		dianHuaF.addKeyListener(new InputKeyListener());
		setupComponet(dianHuaF, 3, 6, 1, 0, true);
		TFlist.add(dianHuaF);
		
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
			String[] columnNames = {"ID", "姓名", "性别", "生日", "学院号", "年级", "专业",
					"班号", "籍贯"};
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
					  IDF.setText(String.valueOf(stuList.get(selectedRowIndex).getStudentID()));
					  xingMingF.setText(String.valueOf(stuList.get(selectedRowIndex).getStudentName()));
					  xingBieF.setText("女");
					  if(stuList.get(selectedRowIndex).getsSex())
						  xingBieF.setText("男");
					  shengRiF.setText(String.valueOf(stuList.get(selectedRowIndex).getsBirthday()));
					  xueYuanHaoF.setText(String.valueOf(stuList.get(selectedRowIndex).getsCno()));
					  nianJiF.setText(String.valueOf(stuList.get(selectedRowIndex).getsGrade()));
					  zhuanYeF.setText(String.valueOf(stuList.get(selectedRowIndex).getsMajor()));
					  banHaoF.setText(String.valueOf(stuList.get(selectedRowIndex).getsClass()));
					  jiGuanF.setText(String.valueOf(stuList.get(selectedRowIndex).getsWhere()));
					
					
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
				for (JTextField jTextField : TFlist) {
					jTextField.setText("");
				}//表格以下的输入框禁止获取焦点
				for(int i = 4; i < TFlist.size();i++){
					TFlist.get(i).setEnabled(false);
				}//除查询外的按键不能按
				table.setEnabled(true);
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
				for(int i = 0; i < TFlist.size();i++){
					TFlist.get(i).setEnabled(true);
				}//除添加外的按键不能按
				TFlist.get(0).setEnabled(false);
				table.setEnabled(false);
				table.clearSelection();
				cxButton.setEnabled(false);
				tjButton.setEnabled(true);
				xgButton.setEnabled(false);
				scButton.setEnabled(false);
				break;
			case 2:
				//表格以下的输入框禁获取焦点
				for(int i = 0; i < TFlist.size();i++){
					TFlist.get(i).setEnabled(true);
				}//除修改与删除外的按键不能按
				TFlist.get(0).setEnabled(false);
				table.setEnabled(true);
				cxButton.setEnabled(false);
				tjButton.setEnabled(false);
				xgButton.setEnabled(true);
				scButton.setEnabled(true);
				break;
			}
		}
		
		private void fillTable(List<Student> stus){
			  DefaultTableModel tableModel = (DefaultTableModel) table
			  .getModel();
			  tableModel.setRowCount(0);// 清除原有行
			  
			  // 填充数据
			  for(Student stu:stus){
			    String[] arr=new String[9];
			    arr[0]=String.valueOf(stu.getStudentID());
			    arr[1]=stu.getStudentName();
			    arr[2]="女";
			    if(stu.getsSex())
			    	arr[2]="男";
			    arr[3]=String.valueOf(stu.getsBirthday());
			    arr[4]=String.valueOf(stu.getsCno());
			    arr[5]=String.valueOf(stu.getsGrade());
			    arr[6]=stu.getsMajor();
			 	arr[7]=String.valueOf(stu.getsClass());	
			    arr[8]=stu.getsWhere();
			    		
			    
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
				if(IDF.getText().equals("") && xingMingF.getText().equals("") && xueYuanHaoF.getText().equals("") && nianJiF.getText().equals("")){
					try {
						stuList = dao.getAll() ;
						fillTable(stuList);
					} catch (Exception e1) {
						//e1.printStackTrace();
						System.err.println("不能获取数据");
					}
				}else{
					System.out.println("有东西在框里");
					int Id=0,nianJi=0,xueYuanHao=0;
					String xingMing=null;
					StringBuffer sql = new StringBuffer();
					sql.append("select * from student where ");
					if(!IDF.getText().equals(""))
						sql.append( "studentID=" + IDF.getText()+" AND ");						
					if(!nianJiF.getText().equals(""))
						sql.append( "sGrade=" + nianJiF.getText()+" AND ");	
					if(!xueYuanHaoF.getText().equals(""))
						sql.append( "sCno=" + xueYuanHaoF.getText()+" AND ");	
					if(!xingMingF.getText().equals(""))
						sql.append( "studentName=\'" + xingMingF.getText()+"\' AND ");
					for(int i = 5;i>0;i--)
						sql.deleteCharAt(sql.length()-1);
					
					System.out.println(sql);
					
					try {
						stuList = dao.sqlQuery(sql.toString());
						fillTable(stuList);
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
				 Student stu = new Student();
				  stu.setStudentName(xingMingF.getText());
				  if(xingBieF.getText().equals("男"))
					  stu.setsSex(true);
				  else stu.setsSex(false);
				  String[] birthday = shengRiF.getText().split("-");
				  Date date = new Date(Integer.parseInt(birthday[0])-1900, Integer.parseInt(birthday[1])-1,Integer.parseInt(birthday[2]) );
				  stu.setsBirthday(date);
				  stu.setsCno(Integer.parseInt(xueYuanHaoF.getText()));
				  stu.setsGrade(Integer.parseInt(nianJiF.getText()));
				  stu.setsMajor(zhuanYeF.getText());
				  stu.setsClass(Integer.parseInt(banHaoF.getText()));
				  stu.setsWhere(jiGuanF.getText());
					dao.insert(stu);
					stuList.add(stu);
					fillTable(stuList);
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
				  Student stu = stuList.get(selectedRowIndex);
				  stu.setStudentName(xingMingF.getText());
				  if(xingBieF.getText().equals("男"))
					  stu.setsSex(true);
				  else stu.setsSex(false);
				  String[] birthday = shengRiF.getText().split("-");
				  Date date = new Date(Integer.parseInt(birthday[0])-1900, Integer.parseInt(birthday[1])-1,Integer.parseInt(birthday[2]) );
				  stu.setsBirthday(date);
				  stu.setsCno(Integer.parseInt(xueYuanHaoF.getText()));
				  stu.setsGrade(Integer.parseInt(nianJiF.getText()));
				  stu.setsMajor(zhuanYeF.getText());
				  stu.setsClass(Integer.parseInt(banHaoF.getText()));
				  stu.setsWhere(jiGuanF.getText());
				  dao.update(stu);
				  stuList.remove(selectedRowIndex);
				  stuList.add(stu);
				  fillTable(stuList);
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
				  Student stu = stuList.get(selectedRowIndex);
				  try {
					dao.delete(String.valueOf(stu.getStudentID()));
					stuList.remove(selectedRowIndex);
					fillTable(stuList);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "删除失败！", "失败！",
							JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}
				
				
			}
			
		}
		
		
	}	
}
