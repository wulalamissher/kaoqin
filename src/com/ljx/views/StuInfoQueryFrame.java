package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ljx.bean.StudentEntity;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.util.WindowsHandler;

public class StuInfoQueryFrame extends JFrame {
	JFrame parentFrame;
	StudentEntity queryStudent = null;
	private static final int DEFAULT_WIDTH = 350;
	private static final int DEFAULT_HEIGHT = 350;

	public StuInfoQueryFrame(String title, StudentEntity student) {
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
		queryStudent = student;
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
		JLabel picLabel, picInfoLabel, nameLabel, IdLabel, acedmyLabel,
				subjectLabel, emailLabel;
		JButton modifyPicButton, modifyEmailButton, returnButton;
		JPanel infoPanel, buttonPanel, picPanel, stuPanel;
		JTextField nameTextField, IdTextField, acedmyTextField,
				subjectTextField, emailTextField;
		JFileChooser chooser;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public StuInfoPanel() {

			picInfoLabel = new JLabel("������Ƭ"); // ����������Ϊ��ǩ��ʾ�ı�
			picLabel = new JLabel();
			modifyPicButton = new JButton("�޸ĸ�����Ƭ");
			// �����ļ�ѡ��������
			chooser = new JFileChooser();
			// ����ļ���������������jpg��jpeg��gif��β���ļ�
			chooser.setFileFilter(new FileNameExtensionFilter(
					"image files", "jpg", "jpeg", "gif"));

			nameLabel = new JLabel("����"); // ����������Ϊ��ǩ��ʾ�ı�
			IdLabel = new JLabel("ѧ��");
			acedmyLabel = new JLabel("����ѧԺ");
			subjectLabel = new JLabel("����רҵ");
			emailLabel = new JLabel("��������");

			nameTextField = new JTextField("����", 15);
			IdTextField = new JTextField("1310122278", 15);
			acedmyTextField = new JTextField("�������ѧԺ", 15);
			subjectTextField = new JTextField("Ӣ��+���", 15);
			emailTextField = new JTextField("caopan@cqupt.edu.cn", 15);

			if (queryStudent != null) {
				nameTextField.setText(queryStudent.getStudent_name());
				IdTextField.setText(Integer.toString(queryStudent.getStudent_Id()));
				acedmyTextField.setText(queryStudent.getStudent_colleage());
				subjectTextField.setText(queryStudent.getStudent_major());
				emailTextField.setText(queryStudent.getStudent_email());
			}

			nameTextField.setEditable(false);
			IdTextField.setEditable(false);
			acedmyTextField.setEditable(false);
			subjectTextField.setEditable(false);
			emailTextField.setEditable(false);

			modifyEmailButton = new JButton("�޸ĸ�������"); // ����������Ϊ��ť��ʾ�ı� = new
														// JButton("�޸ĸ�����Ƭ"); //
														// ����������Ϊ��ť��ʾ�ı�
			returnButton = new JButton("�����ϼ��˵�"); // ����������Ϊ��ť��ʾ�ı�

			infoPanel = new JPanel();
			picPanel = new JPanel();
			buttonPanel = new JPanel();
			stuPanel = new JPanel();

			setLayout(new BorderLayout());

			buttonPanel.add(modifyEmailButton);
			buttonPanel.add(returnButton);
			add(buttonPanel, BorderLayout.SOUTH);

			picPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picPanel.add(picInfoLabel);
			picLabel.setSize(140, 110);
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/001.jpg"));
			image.setImage(image.getImage().getScaledInstance(
					picLabel.getWidth(), picLabel.getHeight(),
					Image.SCALE_DEFAULT));
			picLabel.setIcon(image);
			picPanel.add(picLabel);
			picPanel.add(modifyPicButton);
			picPanel.setBounds(200, 0, 140, 180);
			add(picPanel);

			infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			infoPanel.add(nameLabel);
			infoPanel.add(nameTextField);
			infoPanel.add(IdLabel);
			infoPanel.add(IdTextField);
			infoPanel.add(acedmyLabel);
			infoPanel.add(acedmyTextField);
			infoPanel.add(subjectLabel);
			infoPanel.add(subjectTextField);
			infoPanel.add(emailLabel);
			infoPanel.add(emailTextField);
			infoPanel.setBounds(0, 0, 160, 340);
			add(infoPanel);

			stuPanel.setLayout(null);
			stuPanel.add(infoPanel);
			stuPanel.add(picPanel);
			add(stuPanel, BorderLayout.CENTER);

			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();

			// �¼�Դע���¼�������
			modifyPicButton.addActionListener(buttonAction);
			modifyEmailButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource().equals(returnButton)) {
					StuInfoQueryFrame.this.dispose();
					parentFrame.setVisible(true);
				} else if (e.getSource().equals(modifyEmailButton)) {
					String inputVal = JOptionPane.showInputDialog(null,
							"�������������ַ", "���������ַ",
							JOptionPane.INFORMATION_MESSAGE);
					if (inputVal != null) {
						StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
						if (stuEntity.updateStudentEmail(queryStudent.getStudent_Id(), inputVal) != 0) {
							emailTextField.setText(inputVal);
						} 
						else{
							JOptionPane.showMessageDialog(null, 
									"���ݿ���Email��Ϣ����ʧ�ܣ���͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
						}
					}
				} else if (e.getSource().equals(modifyPicButton)) {
					// ���õ�ǰĿ¼
					chooser.setCurrentDirectory(new File("."));
					// ��ʾ�ļ�ѡ��Ի���
					int result = chooser.showOpenDialog(StuInfoQueryFrame.this);
					// ����ļ�ѡ�������ͼƬ��Ϣ
					if (result == JFileChooser.APPROVE_OPTION) {
						String fileName = chooser.getSelectedFile().getPath();
						ImageIcon image = new ImageIcon(fileName);
						image.setImage(image.getImage().getScaledInstance(
								picLabel.getWidth(), picLabel.getHeight(),
								Image.SCALE_DEFAULT));
						picLabel.setIcon(image);
						StuInfoQueryFrame.this.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "�ù����ݲ�֧�֣�", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StuInfoQueryFrame("ѧ��������Ϣ", null);
			}
		});
	}

}
