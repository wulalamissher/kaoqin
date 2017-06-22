package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.ljx.bean.AttendanceEntity;
import com.ljx.bean.StudentEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.dao.AttendanceEntityDAO;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.AttendanceEntityDAOImpl;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.util.Communal;
import com.ljx.util.WindowsHandler;


public class TchFuncPortalFrame extends JFrame {
	JFrame parentFrame;
	private int teacherId = 0;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 450;

	public TchFuncPortalFrame(String title, int tchId) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(title);

		// 显示位置在屏幕长度和宽度的1/3处。
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidthpx = screenSize.width;
		int screenHeightpx = screenSize.height;
		setLocation(screenWidthpx / 3, screenHeightpx / 3);
		setLocationByPlatform(false);

		// 设置窗口最小化时显示的图标
		Image img = new ImageIcon(this.getClass().getResource("/images/icon.jpg")).getImage();
		setIconImage(img);

		// 装入panel，并显示在中间（默认布局为边框布局borderLayout）
		teacherId = tchId;
		TchInfoPanel tchInfoPanel = new TchInfoPanel();
		getContentPane().add(tchInfoPanel);

		// 注册窗口监听器
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class TchInfoPanel extends JPanel {
		// 定义登录容器Panel中的控件元素。
		JLabel picLabel, tchIdLabel;
		JButton queryPersonalButton, modifyInfoButton, modifyPwdButton;
		JButton queryStudentButton, queryAttendInfoButton, editAttendInfoButton, chgAttendInfoButton;
		JPanel infoPanel, queryPanel, picPanel, queryResultPanel;
		Border infoBoder, queryBoder, picBoder;
		JScrollPane scrollPanel;

		final JLabel classQueryLabel;
		JTextField classQueryTextField;
		JButton queryButton;
		JTable attRecordTable;
		DefaultTableModel tableModel; 
		TableColumnModel columnModel;

		// 登陆容器panel构造，将所有控件元素装入容器。
		public TchInfoPanel() {
			String[] columnName = { "考勤时间", "考勤课程", "考勤结果" };
			Object data[][] = { { "2015-3-12", "数据结构", "正常" },
					{ "2015-3-15", "数据库", "迟到" },
					{ "2015-3-15", "面向对象程序设计", "病假" } };

			picLabel = new JLabel(); // 构造器参数为标签显示文本
			tchIdLabel = new JLabel("130505", JLabel.CENTER);
			tchIdLabel.setText(Integer.toString(teacherId));
			queryPersonalButton = new JButton("查看个人信息"); // 构造器参数为按钮显示文本
			modifyInfoButton = new JButton("修改个人信息");
			modifyPwdButton = new JButton("修改个人密码");
			queryStudentButton = new JButton("查看学生信息");
			queryAttendInfoButton = new JButton("查看考勤记录");
			editAttendInfoButton = new JButton("录入考勤信息");
			chgAttendInfoButton = new JButton("修改考勤记录");
			infoBoder = BorderFactory.createEtchedBorder();
			picBoder = BorderFactory.createEtchedBorder();

			classQueryLabel = new JLabel("请输入要查询考勤记录的课程编号"); // 构造器参数为标签显示文本
			classQueryTextField = new JTextField(18);
			queryButton = new JButton("查询");
			
			//create a TableModel
			tableModel = new DefaultTableModel(data,columnName);
			attRecordTable = new JTable(tableModel);
			columnModel = attRecordTable.getColumnModel();
			attRecordTable.setRowHeight(20);
			attRecordTable.setEnabled(false);
			scrollPanel = new JScrollPane(attRecordTable,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			queryBoder = BorderFactory.createEtchedBorder();
			

			queryPanel = new JPanel();
			infoPanel = new JPanel();
			picPanel = new JPanel();
			queryResultPanel = new JPanel();
			setLayout(null);

			picPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picPanel.setBorder(picBoder);
			picLabel.setSize(140, 110);
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/003.jpg"));
			image.setImage(image.getImage().getScaledInstance(
					picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_DEFAULT));
			picLabel.setIcon(image);
			picPanel.add(picLabel);
			picPanel.add(tchIdLabel);
			picPanel.setBounds(4, 4, 150, 145);
			add(picPanel);

			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			infoPanel.setBorder(infoBoder);
			infoPanel.add(queryPersonalButton);
			infoPanel.add(modifyInfoButton);
			infoPanel.add(modifyPwdButton);
			infoPanel.add(queryStudentButton);
			infoPanel.add(queryAttendInfoButton);
			infoPanel.add(editAttendInfoButton);
			infoPanel.add(chgAttendInfoButton);
			infoPanel.setBounds(4, 154, 150, 265);
			add(infoPanel);

			queryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			queryPanel.setBorder(queryBoder);
			queryPanel.add(classQueryLabel);
			queryPanel.add(classQueryTextField);
			queryPanel.add(queryButton);
			queryPanel.setBounds(160, 4, 330, 70);
			add(queryPanel);

			queryResultPanel.setLayout(new BorderLayout());
			queryResultPanel.add(scrollPanel, BorderLayout.CENTER);
			queryResultPanel.setBounds(160, 78, 330, 345);
			add(queryResultPanel);
			
			JLabel bgLabel=new JLabel();
			ImageIcon bg = new ImageIcon(this.getClass().getResource("/images/bg1.jpg"));
//			contentPane.getSize();
			bgLabel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			bg.setImage(bg.getImage().getScaledInstance(
					bgLabel.getWidth(), bgLabel.getHeight(), Image.SCALE_DEFAULT));
			bgLabel.setIcon(bg);
			add(bgLabel);
			
			// 生成事件监听器对象
			ButtonAction buttonAction = new ButtonAction();			
			//事件源注册事件监听器
			queryPersonalButton.addActionListener(buttonAction);
			modifyInfoButton.addActionListener(buttonAction);
			modifyPwdButton.addActionListener(buttonAction);
			queryStudentButton.addActionListener(buttonAction);
			queryAttendInfoButton.addActionListener(buttonAction);			
			editAttendInfoButton.addActionListener(buttonAction);			
			chgAttendInfoButton.addActionListener(buttonAction);
			queryButton.addActionListener(buttonAction); 
		}

		// 定义事件监听器类，实现事件监听器接口中的actionPerformed方法，引入一个用户具体的操作。
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(queryPersonalButton))
				{			
					final TeacherEntity teacher;
					TeacherEntityDAO stuEntity = new TeacherEntityDAOImpl();
					if ((teacher = stuEntity.queryTeacherEntityById(teacherId)) != null) {
						// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchInfoQueryFrame frame = new TchInfoQueryFrame("教师个人信息查询", teacher);
								frame.parentFrame = TchFuncPortalFrame.this;			
								TchFuncPortalFrame.this.setVisible(false);							
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"数据库中没有您的信息，请和管理员联系！", "警告", JOptionPane.ERROR_MESSAGE);	
						JOptionPane.showMessageDialog(null,"","",JOptionPane.INFORMATION_MESSAGE);	
					}
				}
				else if(e.getSource().equals(modifyInfoButton))
				{
					final TeacherEntity teacher;
					TeacherEntityDAO stuEntity = new TeacherEntityDAOImpl();
					if ((teacher = stuEntity.queryTeacherEntityById(teacherId)) != null) {
						// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchInfoModifyFrame frame = new TchInfoModifyFrame("教师个人信息修改", teacher);
								frame.parentFrame = TchFuncPortalFrame.this;			
								TchFuncPortalFrame.this.setVisible(false);							
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"数据库中没有您的信息，请和管理员联系！", "警告", JOptionPane.ERROR_MESSAGE);						
					}	
				}	
				else if(e.getSource().equals(modifyPwdButton))
				{
					// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							ModifyPwdFrame frame = new ModifyPwdFrame("修改教师密码", teacherId, ModifyPwdFrame.TEACHER_PWD);	
							frame.parentFrame = TchFuncPortalFrame.this;		
							TchFuncPortalFrame.this.setVisible(false);							
						}
					});
				}
				else if(e.getSource().equals(queryStudentButton))
				{
					// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							TchQueryStuInfoFrame frame = new TchQueryStuInfoFrame("查看学生信息");	
							frame.parentFrame = TchFuncPortalFrame.this;		
							TchFuncPortalFrame.this.setVisible(false);							
						}
					});	
				}
				else  if(e.getSource().equals(queryAttendInfoButton))
				{
					final AttendanceEntity[] attRecord;
					AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
					if ((attRecord = attEntity.queryAttendanceEntityByTeacherId(teacherId)) != null && attRecord.length > 0) {
						// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								DBInfoQueryFrame frame = new DBInfoQueryFrame("查看考勤信息", attRecord, DBInfoQueryFrame.ATTENCE_INFO_REQ);	
								frame.parentFrame = TchFuncPortalFrame.this;		
								TchFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"数据库中没有您查询的信息，请和管理员联系！", "警告", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else  if(e.getSource().equals(editAttendInfoButton))
				{
					// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							TchAttRecordFrame frame = new TchAttRecordFrame("录入学生考勤记录", teacherId);
							frame.parentFrame = TchFuncPortalFrame.this;		
							TchFuncPortalFrame.this.setVisible(false);							
						}
					});	
				}				
				else  if(e.getSource().equals(chgAttendInfoButton))
				{
					final AttendanceEntity[] attRecord;
					AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
					if ((attRecord = attEntity.queryAttendanceEntityByTeacherId(teacherId)) != null && attRecord.length > 0) {
						// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchQryMdyAttInfoFrame frame = new TchQryMdyAttInfoFrame("修改考勤信息", attRecord);	
								frame.parentFrame = TchFuncPortalFrame.this;		
								TchFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"数据库中没有您查询的信息，请和管理员联系！", "警告", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else  if(e.getSource().equals(queryButton))
				{						
					if (classQueryTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, 
								"待查询课程编号不能够为空！", "警告", JOptionPane.ERROR_MESSAGE);						
					}
					else if(Communal.isInteger(classQueryTextField.getText().trim()) == false)
					{
						JOptionPane.showMessageDialog(null, "无效课程编号，只能为数字形式！",
								"警告", JOptionPane.ERROR_MESSAGE);
						classQueryTextField.setText("");
					}else
					{
						AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
						AttendanceEntity[] queryResult;
						if ((queryResult = attEntity.queryAttendanceEntityByTchIdAndCourse(teacherId, 
								Integer.parseInt(classQueryTextField.getText().trim()))) != null 
								&& queryResult.length > 0) {
							String[] column = {"教学班号", "课程名称", "学生姓名", "考勤状态", "考勤时间" };	
							AttendanceEntity attendanceTemp = new AttendanceEntity();
							Object[][] rowData = new Object[queryResult.length][5];
							for(int i = 0; i < queryResult.length; i++){
								attendanceTemp = queryResult[i];
								rowData[i][0] = new Integer(attendanceTemp.getClass_Id()) ;
								rowData[i][1] = new String(attendanceTemp.getCourse_name());
								rowData[i][2] = new String(attendanceTemp.getStudent_name());
								rowData[i][3] = new String(attendanceTemp.getAttendance_status());	
								rowData[i][4] = new String(attendanceTemp.getAttendance_date());				
							}
							clearRequeryResultAndRepaint(column, rowData);
						} else{
							JOptionPane.showMessageDialog(null, 
									"数据库中没有您要查询的信息，请和管理员联系！", "警告", JOptionPane.ERROR_MESSAGE);						
						}
					}
				}	
				else
				{
					JOptionPane.showMessageDialog(null, 
							"该功能暂不支持！", "警告", JOptionPane.ERROR_MESSAGE);
				}
			}
		}	
		
       private void clearRequeryResultAndRepaint(String[] columnname, Object[][] rowdata){
			
			while(columnModel.getColumnCount() > 0)
			{
				columnModel.removeColumn(columnModel.getColumn(0));		
				tableModel.setColumnCount(columnModel.getColumnCount());
			};
			while(tableModel.getRowCount() > 0)
			{
				tableModel.removeRow(0);							
			};	
			
			for(int i = 0; i < columnname.length; i++)
			{
				tableModel.addColumn(columnname[i]);
			}
			
			for(int i = 0; i < rowdata.length; i++)
			{
				tableModel.addRow(rowdata[i]);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 所有swing组件必须由事件分派线程（event dispatch thread）进行配置，线程将鼠标点击和按键控制转移到用户接口组件。
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TchFuncPortalFrame("欢迎XXX登陆考勤系统", 130505);
			}
		});
	}

}
