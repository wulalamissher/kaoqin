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
		teacherId = tchId;
		TchInfoPanel tchInfoPanel = new TchInfoPanel();
		getContentPane().add(tchInfoPanel);

		// ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class TchInfoPanel extends JPanel {
		// �����¼����Panel�еĿؼ�Ԫ�ء�
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

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public TchInfoPanel() {
			String[] columnName = { "����ʱ��", "���ڿγ�", "���ڽ��" };
			Object data[][] = { { "2015-3-12", "���ݽṹ", "����" },
					{ "2015-3-15", "���ݿ�", "�ٵ�" },
					{ "2015-3-15", "�������������", "����" } };

			picLabel = new JLabel(); // ����������Ϊ��ǩ��ʾ�ı�
			tchIdLabel = new JLabel("130505", JLabel.CENTER);
			tchIdLabel.setText(Integer.toString(teacherId));
			queryPersonalButton = new JButton("�鿴������Ϣ"); // ����������Ϊ��ť��ʾ�ı�
			modifyInfoButton = new JButton("�޸ĸ�����Ϣ");
			modifyPwdButton = new JButton("�޸ĸ�������");
			queryStudentButton = new JButton("�鿴ѧ����Ϣ");
			queryAttendInfoButton = new JButton("�鿴���ڼ�¼");
			editAttendInfoButton = new JButton("¼�뿼����Ϣ");
			chgAttendInfoButton = new JButton("�޸Ŀ��ڼ�¼");
			infoBoder = BorderFactory.createEtchedBorder();
			picBoder = BorderFactory.createEtchedBorder();

			classQueryLabel = new JLabel("������Ҫ��ѯ���ڼ�¼�Ŀγ̱��"); // ����������Ϊ��ǩ��ʾ�ı�
			classQueryTextField = new JTextField(18);
			queryButton = new JButton("��ѯ");
			
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
			
			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();			
			//�¼�Դע���¼�������
			queryPersonalButton.addActionListener(buttonAction);
			modifyInfoButton.addActionListener(buttonAction);
			modifyPwdButton.addActionListener(buttonAction);
			queryStudentButton.addActionListener(buttonAction);
			queryAttendInfoButton.addActionListener(buttonAction);			
			editAttendInfoButton.addActionListener(buttonAction);			
			chgAttendInfoButton.addActionListener(buttonAction);
			queryButton.addActionListener(buttonAction); 
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(queryPersonalButton))
				{			
					final TeacherEntity teacher;
					TeacherEntityDAO stuEntity = new TeacherEntityDAOImpl();
					if ((teacher = stuEntity.queryTeacherEntityById(teacherId)) != null) {
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchInfoQueryFrame frame = new TchInfoQueryFrame("��ʦ������Ϣ��ѯ", teacher);
								frame.parentFrame = TchFuncPortalFrame.this;			
								TchFuncPortalFrame.this.setVisible(false);							
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û��������Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);	
						JOptionPane.showMessageDialog(null,"","",JOptionPane.INFORMATION_MESSAGE);	
					}
				}
				else if(e.getSource().equals(modifyInfoButton))
				{
					final TeacherEntity teacher;
					TeacherEntityDAO stuEntity = new TeacherEntityDAOImpl();
					if ((teacher = stuEntity.queryTeacherEntityById(teacherId)) != null) {
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchInfoModifyFrame frame = new TchInfoModifyFrame("��ʦ������Ϣ�޸�", teacher);
								frame.parentFrame = TchFuncPortalFrame.this;			
								TchFuncPortalFrame.this.setVisible(false);							
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û��������Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}	
				}	
				else if(e.getSource().equals(modifyPwdButton))
				{
					// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							ModifyPwdFrame frame = new ModifyPwdFrame("�޸Ľ�ʦ����", teacherId, ModifyPwdFrame.TEACHER_PWD);	
							frame.parentFrame = TchFuncPortalFrame.this;		
							TchFuncPortalFrame.this.setVisible(false);							
						}
					});
				}
				else if(e.getSource().equals(queryStudentButton))
				{
					// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							TchQueryStuInfoFrame frame = new TchQueryStuInfoFrame("�鿴ѧ����Ϣ");	
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
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								DBInfoQueryFrame frame = new DBInfoQueryFrame("�鿴������Ϣ", attRecord, DBInfoQueryFrame.ATTENCE_INFO_REQ);	
								frame.parentFrame = TchFuncPortalFrame.this;		
								TchFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û������ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else  if(e.getSource().equals(editAttendInfoButton))
				{
					// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							TchAttRecordFrame frame = new TchAttRecordFrame("¼��ѧ�����ڼ�¼", teacherId);
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
						// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								TchQryMdyAttInfoFrame frame = new TchQryMdyAttInfoFrame("�޸Ŀ�����Ϣ", attRecord);	
								frame.parentFrame = TchFuncPortalFrame.this;		
								TchFuncPortalFrame.this.setVisible(false);						
							}
						});	
					} else{
						JOptionPane.showMessageDialog(null, 
								"���ݿ���û������ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}
				else  if(e.getSource().equals(queryButton))
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
						if ((queryResult = attEntity.queryAttendanceEntityByTchIdAndCourse(teacherId, 
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
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TchFuncPortalFrame("��ӭXXX��½����ϵͳ", 130505);
			}
		});
	}

}
