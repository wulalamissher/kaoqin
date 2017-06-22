package com.ljx.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.dao.AdministratorEntityDAO;
import com.ljx.dao.impl.AdministratorEntityDAOImpl;
//import com.ljx.views.TchFuncPortalFrame;
import com.ljx.views.TchLoginFrame;

public class TchLoginFrame extends JFrame {

//	private JPanel contentPane;
//	private TchLoginPanel loginPanel;
	private static final int DEFAULT_WIDTH = 300; 
	private static final int DEFAULT_HEIGHT = 330;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TchLoginFrame frame = new TchLoginFrame("教师登陆");
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
	public TchLoginFrame(String title) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(title);

		Toolkit kit = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = kit.getScreenSize(); 
		int screenWidthpx = screenSize.width; 
		int screenHeightpx = screenSize.height; 
		setLocation(screenWidthpx / 3, screenHeightpx / 3); 
		setLocationByPlatform(false);
		//设置窗口最小化时显示的图标，可选。 
		Image img = new ImageIcon(this.getClass(). getResource("/images/icon.jpg")).getImage();
		setIconImage(img);

		//设置窗体的内容面板，该面板包含了所有的有效GUI组件信息 
		TchLoginPanel loginPanel = new TchLoginPanel(); 
		setContentPane(loginPanel);
		//设置窗口其他显示属性 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true); 
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	}
	private class TchLoginPanel extends JPanel { //定义登陆容器Panel中的控件元素。 
		JLabel picLabel; 
		//定义登陆容器Panel中的控件元素。 
		JLabel idLabel, pwdLabel; 
		JTextField tchIdTextField; 
		JPasswordField pwdField; 
		JButton okButton,clearButton; 
		JPanel buttonPanel,infoPanel;
		JCheckBox adminCheckBox;
		//登陆容器panel构造，将所有控件元素装入容器。 
		public TchLoginPanel() { //生成所有控件对象 
			picLabel = new JLabel(); 
			idLabel = new JLabel("教师ID："); //构造器参数为标签显示文本 
			pwdLabel = new JLabel("密 码：  "); //构造器参数为标签显示文本 
			tchIdTextField = new JTextField(20); 
			pwdField = new JPasswordField(20); 
			okButton = new JButton("登陆"); //构造器参数为按钮显示文本 
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(adminCheckBox.isSelected())
					{
						String administrator=null;
						AdministratorEntityDAO admin=new AdministratorEntityDAOImpl();
						if ((administrator = admin.login(Integer.parseInt(tchIdTextField.getText().trim()),new String(pwdField.getPassword()))) != null) {
							TchFuncPortalFrame frame = new TchFuncPortalFrame(
									"欢迎" + administrator +" 成功登陆!", 
									Integer.parseInt(tchIdTextField.getText().trim()));
							frame.parentFrame = TchLoginFrame.this;				
							TchLoginFrame.this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误",
									"登录失败", JOptionPane.ERROR_MESSAGE);
							pwdField.setText("");
						}
					}else{
						String tchName = null;
						TeacherEntityDAO tchEntity = new TeacherEntityDAOImpl();
						if ((tchName = tchEntity.login(Integer.parseInt(tchIdTextField.getText().trim()),new String(pwdField.getPassword()))) != null) {
							TchFuncPortalFrame frame = new TchFuncPortalFrame(
									"欢迎" + tchName +" 成功登陆!", 
									Integer.parseInt(tchIdTextField.getText().trim()));
							frame.parentFrame = TchLoginFrame.this;				
							TchLoginFrame.this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误",
									"登录失败", JOptionPane.ERROR_MESSAGE);
							pwdField.setText("");
						}
					}
					
				}
			});
			
			
			clearButton = new JButton("清空信息"); 
			buttonPanel = new JPanel(); 
			infoPanel = new JPanel();
			adminCheckBox=new JCheckBox("我是管理员");
		//设置StuLoginPanel布局为BorderLayout。 
			setLayout(new BorderLayout());
		//依次添加pic、login信息控件到面板容器， //并设置infoPanel布局管理器为FlowLayout。 
			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			picLabel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT/2);
		
			
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/bg5.jpg")); 
			image.setImage(image.getImage().getScaledInstance( picLabel.getWidth(),picLabel.getHeight(), Image.SCALE_DEFAULT)); 
			picLabel.setIcon(image); 
			infoPanel.add(picLabel); 
			infoPanel.add(idLabel); 
			infoPanel.add(tchIdTextField); 
			infoPanel.add(pwdLabel); 
			infoPanel.add(pwdField); 
			infoPanel.add(adminCheckBox);
			add(infoPanel,BorderLayout.CENTER);
		//依次添加按钮控件到面板容器，buttonPanel布局管理器默认流布局， //并将buttonPanel控件作为StuLoginPanel的底端组件。 
			buttonPanel.add(okButton); 
			buttonPanel.add(clearButton); 
			add(buttonPanel,BorderLayout.SOUTH);
			}
	}

}
