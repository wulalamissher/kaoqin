package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.util.WindowsHandler;


public class ModifyPwdFrame extends JFrame {
	JFrame parentFrame;
	private int entityId, entityType;
	public static final int TEACHER_PWD = 0;
	public static final int STUDENT_PWD = 1;	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public ModifyPwdFrame(String title, int entityId, int entityType) {
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
		this.entityId = entityId;
		this.entityType = entityType;
		PersonPwdPanel pwdInfoPanel = new PersonPwdPanel();
		getContentPane().add(pwdInfoPanel);

		// ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class PersonPwdPanel extends JPanel {
		// �����¼����Panel�еĿؼ�Ԫ�ء�
		JLabel currPwdLabel, newPwdLabel, reNewPwdLabel;
		JButton okButton, returnButton;
		JPanel infoPanel, buttonPanel;
		JPasswordField currPwdField, newPwdField, reNewPwdField;
		Border pwdBorder, titleBorder;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public PersonPwdPanel() {			
			currPwdLabel = new JLabel("��ǰ����");  // ����������Ϊ��ǩ��ʾ�ı�
			newPwdLabel = new JLabel("������");
			reNewPwdLabel = new JLabel("ȷ������");			
			
			currPwdField = new JPasswordField(18);
			newPwdField = new JPasswordField(18);
			reNewPwdField = new JPasswordField(18);
			
			okButton = new JButton("ȷ���޸�"); // ����������Ϊ��ť��ʾ�ı� = new JButton("�޸ĸ�����Ƭ"); // ����������Ϊ��ť��ʾ�ı�
			returnButton = new JButton("�����ϼ��˵�"); // ����������Ϊ��ť��ʾ�ı�
			
			pwdBorder = BorderFactory.createEtchedBorder();
			titleBorder = BorderFactory.createTitledBorder(pwdBorder, "�޸�����");
			infoPanel = new JPanel();
			buttonPanel = new JPanel();
			
			setLayout(new BorderLayout());
			
			buttonPanel.add(okButton);
			buttonPanel.add(returnButton);
			add(buttonPanel, BorderLayout.SOUTH);
			
    		infoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    		infoPanel.setBorder(titleBorder);
			infoPanel.add(currPwdLabel);
			infoPanel.add(currPwdField);
			infoPanel.add(newPwdLabel);
			infoPanel.add(newPwdField);
			infoPanel.add(reNewPwdLabel);
			infoPanel.add(reNewPwdField);
			add(infoPanel, BorderLayout.CENTER);	
			
			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();	

			//�¼�Դע���¼�������
			okButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(returnButton))
				{
					ModifyPwdFrame.this.dispose();	
					parentFrame.setVisible(true);
				}
				else
				{
					String oldPwd = new String(currPwdField.getPassword());
					String newPwd = new String(newPwdField.getPassword());
					String reNewPwd = new String(reNewPwdField.getPassword());
					if(newPwd.compareTo(reNewPwd) == 0)					
					//if(newPwdField.getPassword().equals(reNewPwdField.getPassword()))
					{   
						int returnVal;
						
						if(entityType == ModifyPwdFrame.STUDENT_PWD)
						{
							StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
							returnVal = stuEntity.updateStudentPwd(entityId, oldPwd, reNewPwd);	
						}
						else
						{
							TeacherEntityDAO tchEntity = new TeacherEntityDAOImpl();
							returnVal = tchEntity.updateTeacherPwd(entityId, oldPwd, reNewPwd);								
						}
						// if(new String(pwdField.getPassword()).compareTo("123456") == 0)
						if (returnVal == -1) {
							JOptionPane.showMessageDialog(null, 
									"��������������������룡", "����", JOptionPane.ERROR_MESSAGE);	
							currPwdField.setText("");
							newPwdField.setText("");
							reNewPwdField.setText("");
						} 
						else if (returnVal == 1) {
							JOptionPane.showMessageDialog(null, 
									"�����޸ĳɹ���", "��ϲ", JOptionPane.INFORMATION_MESSAGE);
						} 
						else{
							JOptionPane.showMessageDialog(null, 
									"�޷��޸����ݿ�����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
						}						
					}
					else
					{
						JOptionPane.showMessageDialog(null, 
								"����������ȷ�����룡", "����", JOptionPane.ERROR_MESSAGE);	
						newPwdField.setText("");
						reNewPwdField.setText("");
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
				new ModifyPwdFrame("�޸ĸ�������", 1310001, ModifyPwdFrame.STUDENT_PWD);				
			}
		});
	}

}
