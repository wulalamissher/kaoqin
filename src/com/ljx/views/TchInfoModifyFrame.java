package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.io.File;
//import java.io.FilenameFilter;

import javax.swing.*;
//import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ljx.bean.CollegeEntity;
import com.ljx.bean.FacultyEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.dao.CollegeEntityDAO;
import com.ljx.dao.FacultyEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.CollegeEntityDAOImpl;
import com.ljx.dao.impl.FacultyEntityDAOImpl;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.util.WindowsHandler;
//import com.ljx.views.TchInfoModifyFrame;
//import com.ljx.views.TchInfoModifyFrame.TchInfoPanel.ButtonAction;

public class TchInfoModifyFrame extends JFrame {
	JFrame parentFrame;
	TeacherEntity queryTeacher = null;
	private static final int DEFAULT_WIDTH = 350;
	private static final int DEFAULT_HEIGHT = 350;

	public TchInfoModifyFrame(String title, TeacherEntity tcherEntity) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(title);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidthpx = screenSize.width;
		int screenHeightpx = screenSize.height;
		setLocation(screenWidthpx / 3, screenHeightpx / 3);
		setLocationByPlatform(false);

		
		Image img = new ImageIcon(this.getClass().getResource("/images/icon.jpg")).getImage();
		setIconImage(img);

		
		queryTeacher = tcherEntity;
		TchInfoPanel tchInfoPanel = new TchInfoPanel();
		getContentPane().add(tchInfoPanel);

		
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class TchInfoPanel extends JPanel {
		
		JLabel picLabel, picInfoLabel, nameLabel, IdLabel, collegeLabel,
				facultyLabel, emailLabel;
		JButton modifyPicButton, okButton, returnButton;
		JPanel infoPanel, buttonPanel, picPanel, stuPanel;
		JTextField nameTextField, IdTextField, emailTextField;
		JFileChooser chooser;
		JComboBox<String> collegeCombo, facultyCombo;

		
		public TchInfoPanel() {

			picInfoLabel = new JLabel("个人照片"); 
			picInfoLabel = new JLabel("个人照片"); 
			picLabel = new JLabel();
			modifyPicButton = new JButton("修改个人照片");
			
			chooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"image files", "jpg", "jpeg", "gif");
			chooser.setFileFilter(filter);

			nameLabel = new JLabel("姓名"); 
			IdLabel = new JLabel("教师编号");
			collegeLabel = new JLabel("所属学院");
			facultyLabel = new JLabel("所属教研室");
			emailLabel = new JLabel("电子邮箱");

			nameTextField = new JTextField("李老师˼", 15);
			nameTextField.setEditable(false);			
			IdTextField = new JTextField("00001", 15);
			IdTextField.setEditable(false);
			emailTextField = new JTextField("caopan@cqupt.edu.cn", 15);
			collegeCombo = new JComboBox<String>();
			facultyCombo = new JComboBox<String>();
			collegeCombo.addItem("软件工程学院");
			collegeCombo.addItem("计算机学院");
			collegeCombo.addItem("ͨ国际学院");
			facultyCombo.addItem("软件实验中心");
			facultyCombo.addItem("信息安全中心");
			facultyCombo.addItem("国际研究中心");

			if (queryTeacher != null) {
				nameTextField.setText(queryTeacher.getTeacher_name());
				IdTextField.setText(Integer.toString(queryTeacher.getTeacher_Id()));
				emailTextField.setText(queryTeacher.getTeacher_email());
				CollegeEntity[] college;
				CollegeEntityDAO collegeEntity = new CollegeEntityDAOImpl();
				if ((college = collegeEntity.queryCollegeEntityAll()) != null) {
					collegeCombo.removeAllItems();
					for (int i = 0; i < college.length; i++) {
						collegeCombo.addItem(college[i].getCollege_name());
					}
				} 
				
				FacultyEntity[] faculty;
				FacultyEntityDAO facultyEntity = new FacultyEntityDAOImpl();
				if ((faculty = facultyEntity.queryFacultyEntityAll()) != null) {
					facultyCombo.removeAllItems();
					for (int i = 0; i < faculty.length; i++) {
						facultyCombo.addItem(faculty[i].getFaculty_name());
					}
				}
			}

			okButton = new JButton("确认修改");  
			returnButton = new JButton("返回上一级"); 

			infoPanel = new JPanel();
			picPanel = new JPanel();
			buttonPanel = new JPanel();
			stuPanel = new JPanel();

			setLayout(new BorderLayout());

			buttonPanel.add(okButton);
			buttonPanel.add(returnButton);
			add(buttonPanel, BorderLayout.SOUTH);

			picPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picPanel.add(picInfoLabel);
			picLabel.setSize(140, 110);
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/003.jpg"));
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
			infoPanel.add(collegeLabel);
			infoPanel.add(collegeCombo);
			infoPanel.add(facultyLabel);
			infoPanel.add(facultyCombo);
			infoPanel.add(emailLabel);
			infoPanel.add(emailTextField);
			infoPanel.setBounds(0, 0, 160, 340);
			add(infoPanel);

			stuPanel.setLayout(null);
			stuPanel.add(infoPanel);
			stuPanel.add(picPanel);
			add(stuPanel, BorderLayout.CENTER);

			
			ButtonAction buttonAction = new ButtonAction();

			
			modifyPicButton.addActionListener(buttonAction);
			okButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
		}

		
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource().equals(returnButton)) {
					TchInfoModifyFrame.this.dispose();
					parentFrame.setVisible(true);
				} else if (e.getSource().equals(okButton)) {
					TeacherEntity tchUpdate = new TeacherEntity();
					TeacherEntityDAO tchEntity = new TeacherEntityDAOImpl();

					tchUpdate.setTeacher_Id(Integer.parseInt(IdTextField
							.getText().trim()));
					tchUpdate.setTeacher_name(nameTextField.getText().trim());
					tchUpdate.setTeacher_colleage(collegeCombo.getItemAt(collegeCombo.getSelectedIndex()));
					tchUpdate.setTeacher_faculty(facultyCombo.getItemAt(facultyCombo.getSelectedIndex()));
					tchUpdate.setTeacher_email(emailTextField.getText().trim());

					if (tchEntity.updateTeacherEntity(tchUpdate) != 0) {
						JOptionPane.showMessageDialog(null, "修改成功！",
								"", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"未选中修改", "修改失败！",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (e.getSource().equals(modifyPicButton)) {
					
					chooser.setCurrentDirectory(new File("."));
					
					int result = chooser
							.showOpenDialog(TchInfoModifyFrame.this);
					
					if (result == JFileChooser.APPROVE_OPTION)
					{
						String fileName = chooser.getSelectedFile().getPath();
						ImageIcon image = new ImageIcon(fileName);
						image.setImage(image.getImage().getScaledInstance(
								picLabel.getWidth(), picLabel.getHeight(),
								Image.SCALE_DEFAULT));
						picLabel.setIcon(image);
						TchInfoModifyFrame.this.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "修改成功！", "修改成功！",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TchInfoModifyFrame("教师修改个人信息", null);
			}
		});
	}

}