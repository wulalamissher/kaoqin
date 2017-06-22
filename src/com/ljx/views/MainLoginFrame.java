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
					MainLoginFrame frame = new MainLoginFrame("��ӭ��½");
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
		//���ô�����С��ʱ��ʾ��ͼ�꣬��ѡ�� 
		Image img = new ImageIcon(this.getClass(). getResource("/images/icon.jpg")).getImage();
		setIconImage(img);

		//���ô����������壬�������������е���ЧGUI�����Ϣ 
		MainLoginPanel loginPanel = new MainLoginPanel(); 
		setContentPane(loginPanel);
		//���ô���������ʾ���� 
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
	private class MainLoginPanel extends JPanel { //�����½����Panel�еĿؼ�Ԫ�ء� 
		JLabel picLabel; 
		//�����½����Panel�еĿؼ�Ԫ�ء� 
		
		JButton stuButton;
		JButton tchButton; 
		JPanel buttonPanel,infoPanel;
		//��½����panel���죬�����пؼ�Ԫ��װ�������� 
		public MainLoginPanel() { //�������пؼ����� 
			picLabel = new JLabel(); 
			 
			stuButton = new JButton("ѧ����¼���"); //����������Ϊ��ť��ʾ�ı� 
			stuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StuLoginFrame showAB=new StuLoginFrame("ѧ����¼");
					showAB.setVisible(true);
					MainLoginFrame.this.dispose();
				}
			});
			tchButton = new JButton("��ʦ��½���"); 
			tchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TchLoginFrame showAB=new TchLoginFrame("��ʦ��¼");
					showAB.setVisible(true);
					MainLoginFrame.this.dispose();
				}
			});
			buttonPanel = new JPanel(); 
			infoPanel = new JPanel();
		//����StuLoginPanel����ΪBorderLayout�� 
			setLayout(new BorderLayout());
		//�������pic��login��Ϣ�ؼ������������ //������infoPanel���ֹ�����ΪFlowLayout�� 
			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
			picLabel.setSize(DEFAULT_WIDTH, 2*(DEFAULT_HEIGHT)/3);
		
		
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/bg4.jpg")); 
			image.setImage(image.getImage().getScaledInstance( picLabel.getWidth(),picLabel.getHeight(), Image.SCALE_DEFAULT)); 
			picLabel.setIcon(image); 
			infoPanel.add(picLabel); 
			
			add(infoPanel,BorderLayout.CENTER);
		//������Ӱ�ť�ؼ������������buttonPanel���ֹ�����Ĭ�������֣� //����buttonPanel�ؼ���ΪStuLoginPanel�ĵ׶������ 
			buttonPanel.add(stuButton); 
			buttonPanel.add(tchButton); 
			add(buttonPanel,BorderLayout.SOUTH);
			}
		}
}
