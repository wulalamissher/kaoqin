package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ljx.bean.AttendanceEntity;
import com.ljx.bean.ClassEntity;
import com.ljx.bean.CollegeEntity;
import com.ljx.bean.StudentEntity;
import com.ljx.dao.AttendanceEntityDAO;
import com.ljx.dao.ClassEntityDAO;
import com.ljx.dao.CollegeEntityDAO;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.impl.AttendanceEntityDAOImpl;
import com.ljx.dao.impl.ClassEntityDAOImpl;
import com.ljx.dao.impl.CollegeEntityDAOImpl;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.util.WindowsHandler;


public class TchAttRecordFrame extends JFrame {
	JFrame parentFrame;
	private int teacherId;
	private static final int DEFAULT_WIDTH = 310;
	private static final int DEFAULT_HEIGHT = 350;

	public TchAttRecordFrame(String title,  int tchId) {
		//���ô����С
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//���ô������
		setTitle(title);

		//��ʾλ������Ļ���ȺͿ�ȵ�1/3����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidthpx = screenSize.width;
		int screenHeightpx = screenSize.height;
		setLocation(screenWidthpx / 3, screenHeightpx / 3);
		setLocationByPlatform(false);
        
		//���ô�����С��ʱ��ʾ��ͼ��
		Image img = new ImageIcon(this.getClass().getResource("/images/icon.jpg")).getImage();
		setIconImage(img);
        
		//���ô����������壬�������������е���ЧGUI�����Ϣ
		teacherId = tchId;
		StuLoginPanel loginPanel = new StuLoginPanel();
		setContentPane(loginPanel);	
		
		//���ô���������ʾ����		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		//ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
	}
	
	private class StuLoginPanel extends JPanel {
		//�����¼����Panel�еĿؼ�Ԫ�ء�
		JLabel picLabel;
		JLabel classIdLabel, courseNameLabel, stuIdLabel, stuNameLabel, attTimeLabel, attRecordLabel;
		JTextField courseNameTextField, attTimeTextField, stuNameTextField;
		JComboBox<String> classCombo, stuIdCombo, attRecordCombo;
		JButton okButton,returnButton;
		JPanel buttonPanel,infoPanel;
		ClassEntity[] classForTch;
		StudentEntity[] studentForClass;

		
		//��½����panel���죬�����пؼ�Ԫ��װ��������
		public StuLoginPanel() {
			//�������пؼ�����
			picLabel = new JLabel();	
			
			classIdLabel = new JLabel("��ѧ��ţ�"); //����������Ϊ��ǩ��ʾ�ı�			
			classCombo = new JComboBox<String>();
			classCombo.setPreferredSize(new Dimension(200, 20));
			ClassEntityDAO classEntity = new ClassEntityDAOImpl();
			if ((classForTch = classEntity.queryClassEntityByTeacherId(teacherId)) != null) {
				for (int i = 0; i < classForTch.length; i++) {
					classCombo.addItem(Integer.toString(classForTch[i].getClass_Id()));
				}
			} 				
			
			courseNameLabel = new JLabel("�γ����ƣ�"); 
			courseNameTextField = new JTextField("���ݽṹ",18);
			for (int i = 0; i < classForTch.length; i++) {
				if (classForTch[i].getClass_Id() == Integer.parseInt(classCombo
						.getItemAt(classCombo.getSelectedIndex()))) {
					courseNameTextField.setText(classForTch[i].getCourse_name());
					break;
				}
			}
			
			stuIdLabel = new JLabel("ѧ   ��  ID��"); 
			stuIdCombo = new JComboBox<String>();
			stuIdCombo.setPreferredSize(new Dimension(200, 20));
			stuIdCombo.setActionCommand("clear remove all stuID"); 
			StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
			if ((studentForClass = stuEntity.queryStudentEntityByClass(Integer.parseInt(classCombo.getItemAt(classCombo.getSelectedIndex())))) != null 
					&& studentForClass.length > 0) {
				for (int i = 0; i < studentForClass.length; i++) {
					stuIdCombo.addItem(Integer.toString(studentForClass[i].getStudent_Id()));
				}		
			}				
			
			stuNameLabel = new JLabel("ѧ��������"); 
			stuNameTextField = new JTextField("����",18);
			for (int i = 0; i < studentForClass.length; i++) {
				if (studentForClass[i].getStudent_Id() == Integer.parseInt(stuIdCombo
						.getItemAt(stuIdCombo.getSelectedIndex()))) {
					stuNameTextField.setText(studentForClass[i].getStudent_name());
					break;
				}
			}			
			
			attTimeLabel = new JLabel("����ʱ�䣺"); //�������ڸ�ʽ
			attTimeTextField = new JTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),18);
			
			attRecordLabel = new JLabel("���ڼ�¼��"); 
			attRecordCombo = new JComboBox<String>();
			attRecordCombo.addItem("����");
			attRecordCombo.addItem("�ٵ�");
			attRecordCombo.addItem("����");	
			attRecordCombo.addItem("���");	
			attRecordCombo.setPreferredSize(new Dimension(200, 20));
			
			okButton = new JButton("¼�뿼����Ϣ");  //����������Ϊ��ť��ʾ�ı�
			returnButton = new JButton("�����ϼ��˵�"); //����������Ϊ��ť��ʾ�ı�
			
			buttonPanel = new JPanel();
			infoPanel = new JPanel(); 
			
			//����StuLoginPanel����ΪBorderLayout��
			setLayout(new BorderLayout());
			
			//�������pic��login��Ϣ�ؼ������������������infoPanel���ֹ�����ΪFlowLayout��
			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picLabel.setSize(DEFAULT_WIDTH, DEFAULT_WIDTH /4);
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/bg3.jpg"));
			image.setImage(image.getImage().getScaledInstance(
					picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_DEFAULT));
			picLabel.setIcon(image);			
			infoPanel.add(picLabel);			
			infoPanel.add(classIdLabel);
			infoPanel.add(classCombo);
			infoPanel.add(courseNameLabel);
			infoPanel.add(courseNameTextField);
			infoPanel.add(stuIdLabel);
			infoPanel.add(stuIdCombo);
			infoPanel.add(stuNameLabel);
			infoPanel.add(stuNameTextField);		
			infoPanel.add(attTimeLabel);
			infoPanel.add(attTimeTextField);
			infoPanel.add(attRecordLabel);
			infoPanel.add(attRecordCombo);
			add(infoPanel,BorderLayout.CENTER);
			
			//������Ӱ�ť�ؼ������������buttonPanel���ֹ�����Ĭ�������֣�
			//����buttonPanel�ؼ���ΪStuLoginPanel�ĵ׶������				
			buttonPanel.add(okButton);
			buttonPanel.add(returnButton);			
			add(buttonPanel,BorderLayout.SOUTH);
			
			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();
			ComoboBoxAction comboBoxAction = new ComoboBoxAction();
			
			//�¼�Դע���¼�������
			okButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
			stuIdCombo.addItemListener(comboBoxAction);
			classCombo.addItemListener(comboBoxAction);
		}	
		
		
		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(returnButton))
				{
					TchAttRecordFrame.this.dispose();	
					parentFrame.setVisible(true);
				}
				else
				{
					AttendanceEntity attRecord = new AttendanceEntity();
					attRecord.setClass_Id(Integer.parseInt(classCombo.getItemAt(classCombo.getSelectedIndex())));
					attRecord.setStudent_Id(Integer.parseInt(stuIdCombo.getItemAt(stuIdCombo.getSelectedIndex())));
					attRecord.setAttendance_status(attRecordCombo.getItemAt(attRecordCombo.getSelectedIndex()));
					attRecord.setAttendance_date(attTimeTextField.getText());
					
					AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
					if ((attEntity.insertAttendanceEntity(attRecord) ) > 0) {
						JOptionPane.showMessageDialog(null, "���ڼ�¼¼��ɹ���", "��ϲ", JOptionPane.INFORMATION_MESSAGE);
					} 
					else{
						JOptionPane.showMessageDialog(null, 
								"�޷�¼�뿼�ڼ�¼����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
					}
				}
			}
		}
		
		
		// �����¼��������࣬ʵ���¼��������ӿ��е�itemStateChanged����������һ���û�����Ĳ�����
		private class ComoboBoxAction implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getSource().equals(classCombo))
				{	
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							for (int i = 0; i < classForTch.length; i++) {
								if (classForTch[i].getClass_Id() == Integer.parseInt(classCombo
										.getItemAt(classCombo.getSelectedIndex()))) {
									courseNameTextField.setText(classForTch[i].getCourse_name());
									break;
								}
							}
							
							StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
							if ((studentForClass = stuEntity.queryStudentEntityByClass(Integer.parseInt(classCombo
									.getItemAt(classCombo.getSelectedIndex())))) != null 
									&& studentForClass.length > 0) {
								stuIdCombo.setActionCommand("reset remove all stuID"); 
								stuIdCombo.removeAllItems();
								stuIdCombo.setActionCommand("clear remove all stuID"); 
								for (int i = 0; i < studentForClass.length; i++) {
									stuIdCombo.addItem(Integer.toString(studentForClass[i].getStudent_Id()));
								}		
							}
							for (int i = 0; i < studentForClass.length; i++) {
								if (studentForClass[i].getStudent_Id() == Integer.parseInt(stuIdCombo
										.getItemAt(stuIdCombo.getSelectedIndex()))) {
									stuNameTextField.setText(studentForClass[i].getStudent_name());
									break;
								}
							}
						}
					});
				}				
				else if(arg0.getSource().equals(stuIdCombo))
				{	
					if(stuIdCombo.getActionCommand().equals("clear remove all stuID")){
						for (int i = 0; i < studentForClass.length; i++) {
							if (studentForClass[i].getStudent_Id() == Integer.parseInt(stuIdCombo
									.getItemAt(stuIdCombo.getSelectedIndex()))) {
								stuNameTextField.setText(studentForClass[i].getStudent_name());
								break;
							}
						}
					}
				}
			}
		}
	}		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TchAttRecordFrame("ѧ��������Ϣ¼��", 130507);
			}
		});
	}
}
