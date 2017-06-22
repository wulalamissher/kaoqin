package com.ljx.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class test extends JFrame {

	private JPanel contentPane;

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 500;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 510, 518);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int screenWidthpx = screenSize.width;
//		int screenHeightpx = screenSize.height;
//		setLocation(screenWidthpx / 3, screenHeightpx / 3);
		setLocationRelativeTo(null);
		setLocationByPlatform(false);
		
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		 
		JPanel picPanel;
		Border picBoder;
		
//		QueryResultPanel = new JPanel();
		getContentPane().setLayout(null);
		picBoder = BorderFactory.createEtchedBorder();
		ImageIcon image = new ImageIcon(this.getClass().getResource("/images/001.jpg"));
		
		JLabel picLabel= new JLabel(); 
		picPanel = new JPanel();
		picPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		picPanel.setBorder(picBoder);
		picLabel.setSize(150,130);
		image.setImage(image.getImage().getScaledInstance(
				picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);
		picLabel.setIcon(image);
		picPanel.add(picLabel);
		//		picPanel.add(stuIdLabel);
				picPanel.setBounds(10, 10, 150, 145);
				getContentPane().add(picPanel);
				
				JButton btnNewButton = new JButton("New button");
				btnNewButton.setBounds(164, 53, 113, 27);
				contentPane.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("New button");
				btnNewButton_1.setBounds(164, 113, 113, 27);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("New button");
				btnNewButton_2.setBounds(164, 178, 113, 27);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("New button");
				btnNewButton_3.setBounds(14, 178, 113, 27);
				contentPane.add(btnNewButton_3);
				
				JLabel bgLabel=new JLabel();
				ImageIcon bg = new ImageIcon(this.getClass().getResource("/images/bg1.jpg"));
//				contentPane.getSize();
				bgLabel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				bg.setImage(bg.getImage().getScaledInstance(
						bgLabel.getWidth(), bgLabel.getHeight(), Image.SCALE_DEFAULT));
				bgLabel.setIcon(bg);
				contentPane.add(bgLabel);
	}
}
