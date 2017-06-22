package com.ljx.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.Color;

public class AdmFuncPortalFrame extends JFrame {

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
					AdmFuncPortalFrame frame = new AdmFuncPortalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JFrame jframe = new JFrame();

	/**
	 * Create the frame.
	 */
	public AdmFuncPortalFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 573);
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
		picPanel.setBounds(60, 10, 150, 130);
		getContentPane().add(picPanel);
		
		JLabel label = new JLabel("设置学院信息");
		label.setForeground(Color.WHITE);
		label.setBounds(102, 182, 95, 25);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(102, 234, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(102, 274, 113, 27);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(102, 314, 113, 27);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(102, 354, 113, 27);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(102, 394, 113, 27);
		contentPane.add(btnNewButton_4);
		
		JLabel label_1 = new JLabel("设置教研室信息");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(362, 185, 105, 18);
		contentPane.add(label_1);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(362, 234, 113, 27);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(362, 274, 113, 27);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setBounds(362, 314, 113, 27);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setBounds(362, 354, 113, 27);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.setBounds(362, 394, 113, 27);
		contentPane.add(btnNewButton_9);
		
		
		
		JLabel bgLabel=new JLabel();
		bgLabel.setLocation(0, 0);
		ImageIcon bg = new ImageIcon(this.getClass().getResource("/images/bg1.jpg"));
//		contentPane.getSize();
		bgLabel.setSize(560, 526);
		bg.setImage(bg.getImage().getScaledInstance(
				bgLabel.getWidth(), bgLabel.getHeight(), Image.SCALE_DEFAULT));
		bgLabel.setIcon(bg);
		contentPane.add(bgLabel);
	}
}
