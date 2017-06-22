package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;

import com.ljx.bean.AttendanceEntity;
import com.ljx.bean.ClassEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.util.WindowsHandler;

public class DBInfoQueryFrame extends JFrame {
	public static final int TEACHER_INFO_REQ = 0;
	public static final int CLASS_INFO_REQ = 1;
	public static final int ATTENCE_INFO_REQ = 2;	
	JFrame parentFrame;
	private int queryType;
	private Object[] queryResult;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 380;

	public DBInfoQueryFrame(String title, Object[] object, int queryType) {
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
		queryResult = object;
		this.queryType = queryType;
		DBInfoPanel dbInfoPanel = new DBInfoPanel();
		getContentPane().add(dbInfoPanel);

		// ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class DBInfoPanel extends JPanel {
		// �����¼����Panel�еĿؼ�Ԫ�ء�
		JButton returnButton;
		JPanel queryResultPanel, buttonPanel;
		JScrollPane scrollPanel;
		JTable dbInfoTable;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public DBInfoPanel() {
			String[] columnName = { "����", "�οογ�", "����ѧԺ" };
			Object data[][] = { { "����", "���ݽṹ", "�����ѧԺ" },
					{ "2015-3-15", "���ݿ�", "���ѧԺ" }, { "2015-3-15", "�������������", "ͨ��ѧԺ" } };			
			
			if(queryResult != null && queryResult.length > 0)
			{
				if(queryType == DBInfoQueryFrame.CLASS_INFO_REQ)  //��ѧ��Ϳγ���Ϣ
				{				
					String[] columnName1 = { "��ѧ���", "�γ�����", "�Ͽ�ʱ��", "�ον�ʦ" };
					ClassEntity classTemp = new ClassEntity();
					Object[][] data1 = new Object[queryResult.length][4];
					for(int i = 0; i < queryResult.length; i++){
						classTemp = (ClassEntity)queryResult[i];
						data1[i][0] = new Integer(classTemp.getClass_Id()) ;
						data1[i][1] = new String(classTemp.getCourse_name());
						data1[i][2] = new String(classTemp.getClass_time());
						data1[i][3] = new String(classTemp.getTeacher_name());				
					}
					columnName = columnName1;
					data = data1;				
				}
				else if(queryType == DBInfoQueryFrame.TEACHER_INFO_REQ) //��ʦ��Ϣ
				{
					String[] columnName1 = { "��ʦ���", "����", "����ѧԺ", "����������","��������" };
					TeacherEntity tchTemp = new TeacherEntity();
					Object[][] data1 = new Object[queryResult.length][5];
					for(int i = 0; i < queryResult.length; i++){
						tchTemp = (TeacherEntity)queryResult[i];
						data1[i][0] = new Integer(tchTemp.getTeacher_Id()) ;
						data1[i][1] = new String(tchTemp.getTeacher_name());
						data1[i][2] = new String(tchTemp.getTeacher_colleage());
						data1[i][3] = new String(tchTemp.getTeacher_faculty());
						data1[i][4] = new String(tchTemp.getTeacher_email());					
					}
					columnName = columnName1;
					data = data1;
				}
				else //������Ϣ
				{
					String[] columnName1 = { "��ѧ���", "�γ�����", "ѧ��ID", "����", "����ʱ��", "���ڼ�¼" };
					AttendanceEntity attTemp = new AttendanceEntity();
					Object[][] data1 = new Object[queryResult.length][6];
					for(int i = 0; i < queryResult.length; i++){
						attTemp = (AttendanceEntity)queryResult[i];
						data1[i][0] = new Integer(attTemp.getClass_Id()) ;
						data1[i][1] = new String(attTemp.getCourse_name());
						data1[i][2] = new Integer(attTemp.getStudent_Id());
						data1[i][3] = new String(attTemp.getStudent_name());
						data1[i][4] = new String(attTemp.getAttendance_date());		
						data1[i][5] = new String(attTemp.getAttendance_status());	
					}
					columnName = columnName1;
					data = data1;
				}				
			}

			dbInfoTable = new JTable(data, columnName);
			dbInfoTable.setAutoCreateColumnsFromModel(true);
			dbInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dbInfoTable.setRowHeight(20);
			dbInfoTable.setEnabled(false);		
			scrollPanel = new JScrollPane(dbInfoTable,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 90)); 
			//queryBoder = BorderFactory.createEtchedBorder();

			buttonPanel = new JPanel();
			queryResultPanel = new JPanel();

			setLayout(new BorderLayout(10,10));
			queryResultPanel.add(scrollPanel);
			add(queryResultPanel, BorderLayout.CENTER);

			returnButton = new JButton("�����ϼ��˵�");
			buttonPanel.add(returnButton);
			add(buttonPanel, BorderLayout.SOUTH);

			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();
			// �¼�Դע���¼�������
			returnButton.addActionListener(buttonAction);
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBInfoQueryFrame.this.dispose();
				parentFrame.setVisible(true);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DBInfoQueryFrame("�鿴��ʦ��Ϣ", null, DBInfoQueryFrame.TEACHER_INFO_REQ);
			}
		});
	}

}
