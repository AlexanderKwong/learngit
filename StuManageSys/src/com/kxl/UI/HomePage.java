package com.kxl.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import com.kxl.internalFrame.*;

public class HomePage extends JFrame {
	
	private JPanel contentPane;
	private JLabel bglabel;
	private JDesktopPane desktopPane;
	private Map ifs;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SubstanceLookAndFeel()); 
					JFrame.setDefaultLookAndFeelDecorated(true); 
					JDialog.setDefaultLookAndFeelDecorated(true); 
					SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
					
					SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
					SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
					SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark()); 
					SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
					SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
					SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter()); 
					
					
					HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("学生管理系统");
		
		ifs = new HashMap();

		
		bglabel = new JLabel();
		bglabel.setVerticalAlignment(SwingConstants.TOP);
		bglabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage();
		
		desktopPane = new JDesktopPane();
		desktopPane.add(bglabel,new Integer(Integer.MIN_VALUE));
		contentPane.add(desktopPane);
		
		JTabbedPane navigation = createNavigationPanel();
		contentPane.add(navigation, BorderLayout.NORTH);
		
	}
	
	
	//使用背景图片
	private void updateBackImage(){
		if(bglabel != null){
			int width = HomePage.this.getWidth();
			int height = HomePage. this.getHeight();
			bglabel.setSize(width, height);
			bglabel.setText("<html><body><image width='"+width+"'height='"+(height-110)+"'src='"+HomePage.this.getClass().getResource("welcome.jpg")+"'></img></body></html>");
			
		}
	}

	//创建选项卡导航栏
	private JTabbedPane createNavigationPanel(){
		JTabbedPane jtp = new JTabbedPane();
		jtp.setFocusable(false);
		jtp.setBackground(new Color(211,230,194));
		//为了立体效果用bevelboard
		jtp.setBorder(new BevelBorder(BevelBorder.RAISED));
		//编写各个选项卡
		JPanel CollegeInfo = new JPanel();
		CollegeInfo.setBackground(new Color(215,223,194));
		CollegeInfo.setLayout(new BoxLayout(CollegeInfo, BoxLayout.X_AXIS));
		CollegeInfo.add(createFrameButton("院系信息", "YuanXiXinXi"));
		
		JPanel StudentInfo = new JPanel();
		StudentInfo.setBackground(new Color(215,223,194));
		StudentInfo.setLayout(new BoxLayout(StudentInfo, BoxLayout.X_AXIS));
		StudentInfo.add(createFrameButton("个人信息", "GeRenXinXi"));
		StudentInfo.add(createFrameButton("学籍信息", "XueJiXinXi"));
		StudentInfo.add(createFrameButton("奖惩信息", "JiangChengXinXi"));
		
		JPanel TeacherInfo = new JPanel();
		TeacherInfo.setBackground(new Color(215,223,194));
		TeacherInfo.setLayout(new BoxLayout(TeacherInfo, BoxLayout.X_AXIS));
		TeacherInfo.add(createFrameButton("教师信息", "JiaoShiXinXi"));
		TeacherInfo.add(createFrameButton("责任相关", "ZeRenXiangGuan"));

		JPanel  CourseAndScore = new JPanel();
		 CourseAndScore.setBackground(new Color(215,223,194));
		 CourseAndScore.setLayout(new BoxLayout( CourseAndScore, BoxLayout.X_AXIS));
		CourseAndScore.add(createFrameButton("成绩查询", "ChengJiChaXun"));
		CourseAndScore.add(createFrameButton("课表查询", "KeBiaoChaXun"));
		
		JPanel SystemInfo = new JPanel();
		SystemInfo.setBackground(new Color(215,223,194));
		SystemInfo.setLayout(new BoxLayout(SystemInfo, BoxLayout.X_AXIS));
		SystemInfo.add(createFrameButton("修改密码", "XiuGaiMiMa"));
		SystemInfo.add(createFrameButton("关于系统", "GuanYuXiTong"));
		SystemInfo.add(createFrameButton("我的权限", "WoDeQuanXian"));
		
		//将所有面板放进导航面板
		jtp.addTab("    学院信息    ", null, CollegeInfo, "学院信息");
		jtp.addTab("    学生信息    ", null, StudentInfo, "学生信息");
		jtp.addTab("    教师信息    ", null, TeacherInfo, "教师信息");
		jtp.addTab("    课程及成绩    ", null, CourseAndScore, "课程及成绩");
		jtp.addTab("    系统信息    ", null, SystemInfo, "系统信息");
			return jtp;
		}
	
	//创建菜单栏导航栏
	private JMenuBar createMenuBar(){
		return null;
	}
	
	//创建按钮
	private JButton createFrameButton(String cName,String fName){
		String imgUrl = "res/ActionIcon/"+cName+"_down.png";
		String imgUrl_roll = "res/ActionIcon/"+cName+"_roll.png";
		Icon icon = null, icon_roll = null;
		if(imgUrl != null)
			icon = new ImageIcon(imgUrl);
		if(imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll);
		javax.swing.Action action = new openFrameAction(cName,fName,icon);
		JButton btn = new JButton(action);
		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.setHideActionText(true);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		if (icon_roll != null)
			btn.setRolloverIcon(icon_roll);
		if (icon != null)
			btn.setPressedIcon(icon);
		return btn;
		
	}
	//按钮响应内部类
	private class openFrameAction extends AbstractAction{
		private String frameName = null;
		public openFrameAction(String cName,String fName,Icon icon){
			this.frameName = fName;
			//设置Action名称
			putValue(javax.swing.Action.NAME, cName);
			//设置Action的提示
			putValue(javax.swing.Action.SHORT_DESCRIPTION,cName);
			//设置Action的图标
			putValue(javax.swing.Action.SMALL_ICON,icon);
			
		}
		@Override
		public void actionPerformed(final ActionEvent e){
			JInternalFrame jif = getIFrame(frameName);
			jif.addInternalFrameListener(new InternalFrameAdapter() {
				
				public void internalFrameClosed(InternalFrameEvent e)
				{
					ifs.remove(frameName);
				}
			});
			if (jif.getDesktopPane() == null)
			{
				desktopPane.add(jif);
				jif.setVisible(true);
			}
			try
			{
				jif.setSelected(true);
			}
			catch (PropertyVetoException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	//用反射的方式返回一个内部窗口
	private JInternalFrame getIFrame(String frameName)
	{
		JInternalFrame jif = null;
		if (!ifs.containsKey(frameName))
			try
			{
				Class fClass = Class.forName((new StringBuilder("com.kxl.internalFrame.")).append(frameName).toString());
				Constructor constructor = fClass.getConstructor(null);
				jif = (JInternalFrame)constructor.newInstance(null);
				ifs.put(frameName, jif);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		else
			jif = (JInternalFrame)ifs.get(frameName);
		return jif;
	}
	
}
