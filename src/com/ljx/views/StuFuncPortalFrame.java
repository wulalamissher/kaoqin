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
import com.ljx.bean.ClassEntity;
import com.ljx.bean.StudentEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.dao.AttendanceEntityDAO;
import com.ljx.dao.ClassEntityDAO;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.AttendanceEntityDAOImpl;
import com.ljx.dao.impl.ClassEntityDAOImpl;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.util.Communal;
import com.ljx.util.WindowsHandler;


public class StuFuncPortalFrame extends JFrame {
	JFrame parentFrame;
	private int studentId = 0;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 450;

	public StuFuncPortalFrame(String title, int stuId) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(title);

		// ��ʾλ������Ļ���ȺͿ�ȵ�1/3����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidthpx = screenSize.width;
		int screenHeightpx = screenSize.height;
		setLocation(screenWidthpx / 3, screenHeightpx / 3);
		setLocationByPlatform(false);

		// ���ô�����С��ʱ��ʾ��ͼ��
		Image img = new ImageIcon(this.getClass().getResource("/images/icon.jpg")).getImage();
		setIconImage(img);

		// װ��panel������ʾ���м䣨Ĭ�ϲ���Ϊ�߿򲼾�borderLayout��
		studentId = stuId;
		StuInfoPanel stuInfoPanel = new StuInfoPanel();
		getContentPane().add(stuInfoPanel);

		// ע�ᴰ�ڼ�����
        addWindowListener(new WindowsHandler());
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class StuInfoPanel extends JPanel {
		// �����¼����Panel�еĿؼ�Ԫ�ء�
		JLabel picLabel, stuIdLabel;
		JButton quereyPersonalButton, modifyInfoButton;
		JButton modifyPwdButton, quereyTeacherButton, quereyClassButton;
		JPanel infoPanel, queryPanel, picPanel, QueryResultPanel;
		Border infoBoder, QueryBoder, picBoder;
		JScrollPane scrollPanel;

		final JLabel classQueryLabel;
		JTextField classQueryTextField;
		JButton quereyButton;
		JTable attRecordTable;
		
		DefaultTableModel tableModel; 
		TableColumnModel columnModel;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public StuInfoPanel() {			
			String[] columnName = { "����ʱ��", "���ڿγ�", "���ڽ��" };
			Object data[][] = { { "2015-3-12", "���ݽṹ", "����" },
					{ "2015-3-15", "���ݿ�", "�ٵ�" },
					{ "2015-3-15", "�������������", "����" } };

			picLabel = new JLabel(); // ����������Ϊ��ǩ��ʾ�ı�
			stuIdLabel = new JLabel("13113088", JLabel.CENTER);
			stuIdLabel.setText(Integer.toString(studentId));
			quereyPersonalButton = new JButton("�鿴������Ϣ"); // ����������Ϊ��ť��ʾ�ı�
			modifyInfoButton = new JButton("�޸ĸ�����Ϣ");
			modifyPwdButton = new JButton("�޸ĸ�������");
			quereyTeacherButton = new JButton("�鿴��ʦ��Ϣ");
			quereyClassButton = new JButton("�鿴�γ���Ϣ");
			infoBoder = BorderFactory.createEtchedBorder();
			picBoder = BorderFactory.createEtchedBorder();

			classQueryLabel = new JLabel("������Ҫ��ѯ���ڼ�¼�Ŀγ̱��"); // ����������Ϊ��ǩ��ʾ�ı�
			classQueryTextField = new JTextField(18);	
			quereyButton = new JButton("��ѯ");
		
			//create a TableModel
			tableModel = new DefaultTableModel(data,columnName);
			attRecordTable = new JTable(tableModel);
			columnModel = attRecordTable.getColumnModel();
			attRecordTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			attRecordTable.setRowHeight(20);
			attRecordTable.setEnabled(false);	
			scrollPanel = new JScrollPane(attRecordTable,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			QueryBoder = BorderFactory.createEtchedBorder();			

			queryPanel = new JPanel();
			infoPanel = new JPanel();
			picPanel = new JPanel();
			QueryResultPanel = new JPanel();
			setLayout(null);

			picPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picPanel.setBorder(picBoder);
			picLabel.setSize(140, 110);
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/001.jpg"));
			image.setImage(image.getImage().getScaledInstance(
					picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_DEFAULT));
			picLabel.setIcon(image);
			picPanel.add(picLabel);
			picPanel.add(stuIdLabel);
			picPanel.setBounds(0, 0, 150, 145);
			add(picPanel);

			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			infoPanel.setBorder(infoBoder);
			infoPanel.add(quereyPersonalButton);
			infoPanel.add(modifyInfoButton);
			infoPanel.add(modifyPwdButton);
			infoPanel.add(quereyTeacherButton);
			infoPanel.add(quereyClassButton);
			infoPanel.setBounds(0, 150, 150, 265);
			add(infoPanel);

			queryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			queryPanel.setBorder(QueryBoder);
			queryPanel.add(classQueryLabel);
			queryPanel.add(classQueryTextField);
			queryPanel.add(quereyButton);
			queryPanel.setBounds(160, 0, 340, 70);
			add(queryPanel);

			QueryResultPanel.setLayout(new BorderLayout());
			QueryResultPanel.add(scrollPanel, BorderLayout.CENTER);
			QueryResultPanel.setBounds(160, 70, 330, 345);
			add(QueryResultPanel);
			
			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();			
			//�¼�Դע���¼�������
			quereyPersonalButton.addActionListener(buttonAction);
			modifyInfoButton.addActionListener(buttonAction);
			modifyPwdButton.addActionListener(buttonAction);
			quereyTeacherButton.addActionListener(buttonAction);
			quereyClassButton.addActionListener(buttonAction);
			quereyButton.addActionListener(buttonAction); 
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(quereyPersonalButton))
				{
					final StudentEntity student;
					StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
					// if(new String(pwdField.getPassword()).compareTo("123456") == 0)
					if ((student = stuEntity.queryStudentEntityById(studentId)) != null) {
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								StuInfoQueryFrame frame = new StuInfoQueryFrame("ѧ��������Ϣ��ѯ", student);
								frame.parentFrame = StuFuncPortalFrame.this;		
								StuFuncPortalFrame.this.setVisible(false);							
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û��������Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else if(e.getSource().equals(modifyInfoButton))
				{
					JOptionPane.showMessageDialog(null, 
							"ֻ�й���Ա�ſ����޸�ѧ����Ϣ��", "����", JOptionPane.ERROR_MESSAGE);
				}	
				else if(e.getSource().equals(modifyPwdButton))
				{
					// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							ModifyPwdFrame frame = new ModifyPwdFrame("�޸�ѧ������", studentId, ModifyPwdFrame.STUDENT_PWD);	
							frame.parentFrame = StuFuncPortalFrame.this;			
							StuFuncPortalFrame.this.setVisible(false);							
						}
					});
				}
				else if(e.getSource().equals(quereyTeacherButton))
				{					
					final TeacherEntity[] teacher;
					TeacherEntityDAO tchEntity = new TeacherEntityDAOImpl();
					// if(new String(pwdField.getPassword()).compareTo("123456") == 0)
					if ((teacher = tchEntity.queryTeacherEntityAll()) != null) {
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								DBInfoQueryFrame frame = new DBInfoQueryFrame("�鿴��ʦ��Ϣ", teacher, DBInfoQueryFrame.TEACHER_INFO_REQ);	
								frame.parentFrame = StuFuncPortalFrame.this;		
								StuFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û������ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else  if(e.getSource().equals(quereyClassButton))
				{					
					final ClassEntity[] classInfo;
					ClassEntityDAO classEntity = new ClassEntityDAOImpl();
					if ((classInfo = classEntity.queryClassEntityByStudentId(studentId)) != null) {
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								DBInfoQueryFrame frame = new DBInfoQueryFrame("�鿴�γ���Ϣ", classInfo, DBInfoQueryFrame.CLASS_INFO_REQ);	
								frame.parentFrame = StuFuncPortalFrame.this;		
								StuFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û������ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}		
				else  if(e.getSource().equals(quereyButton))
				{						
					if (classQueryTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, 
								"����ѯ�γ̱�Ų��ܹ�Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);						
					}
					else if(Communal.isInteger(classQueryTextField.getText().trim()) == false)
					{
						JOptionPane.showMessageDialog(null, "��Ч�γ̱�ţ�ֻ��Ϊ������ʽ��",
								"����", JOptionPane.ERROR_MESSAGE);
						classQueryTextField.setText("");
					}else
					{
						AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
						AttendanceEntity[] queryResult;
						if ((queryResult = attEntity.queryAttendanceEntityByStuIdAndCourse(studentId, 
								Integer.parseInt(classQueryTextField.getText().trim()))) != null 
								&& queryResult.length > 0) {
							String[] column = {"��ѧ���", "�γ�����", "ѧ������", "����״̬", "����ʱ��" };	
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
									"���ݿ���û����Ҫ��ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
						}
					} 

				}	
				else
				{
					JOptionPane.showMessageDialog(null, 
							"�ù����ݲ�֧�֣�", "����", JOptionPane.ERROR_MESSAGE);
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
			
			for(int i = 0; i < columnModel.getColumnCount(); i++)
			{
				columnModel.getColumn(i).setPreferredWidth(90);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StuFuncPortalFrame("ѧ������ϵͳ", 1310002);
			}
		});
	}

}
