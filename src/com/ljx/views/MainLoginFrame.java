package com.ljx.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class MainLoginFrame extends JFrame {

	private static final int DEFAULT_WIDTH = 300; 
	private static final int DEFAULT_HEIGHT = 300;

//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLoginFrame frame = new MainLoginFrame("欢迎登陆");
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
	public MainLoginFrame(String title) {
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
		MainLoginPanel loginPanel = new MainLoginPanel(); 
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
	private class MainLoginPanel extends JPanel { //定义登陆容器Panel中的控件元素。 
		JLabel picLabel; 
		//定义登陆容器Panel中的控件元素。 
		
		JButton stuButton;
		JButton tchButton; 
		JPanel buttonPanel,infoPanel;
		//登陆容器panel构造，将所有控件元素装入容器。 
		public MainLoginPanel() { //生成所有控件对象 
			picLabel = new JLabel(); 
			 
			stuButton = new JButton("学生登录点击"); //构造器参数为按钮显示文本 
			stuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StuLoginFrame showAB=new StuLoginFrame("学生登录");
					showAB.setVisible(true);
					MainLoginFrame.this.dispose();
				}
			});
			tchButton = new JButton("教师登陆点击"); 
			tchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TchLoginFrame showAB=new TchLoginFrame("教师登录");
					showAB.setVisible(true);
					MainLoginFrame.this.dispose();
				}
			});
			buttonPanel = new JPanel(); 
			infoPanel = new JPanel();
		//设置StuLoginPanel布局为BorderLayout。 
			setLayout(new BorderLayout());
		//依次添加pic、login信息控件到面板容器， //并设置infoPanel布局管理器为FlowLayout。 
			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
			picLabel.setSize(DEFAULT_WIDTH, 2*(DEFAULT_HEIGHT)/3);
		
		
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/bg4.jpg")); 
			image.setImage(image.getImage().getScaledInstance( picLabel.getWidth(),picLabel.getHeight(), Image.SCALE_DEFAULT)); 
			picLabel.setIcon(image); 
			infoPanel.add(picLabel); 
			
			add(infoPanel,BorderLayout.CENTER);
		//依次添加按钮控件到面板容器，buttonPanel布局管理器默认流布局， //并将buttonPanel控件作为StuLoginPanel的底端组件。 
			buttonPanel.add(stuButton); 
			buttonPanel.add(tchButton); 
			add(buttonPanel,BorderLayout.SOUTH);
			}
		}
}
