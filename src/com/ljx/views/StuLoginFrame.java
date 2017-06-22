package com.ljx.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.impl.StudentEntityDAOImpl;
//import com.ljx.views.StuFuncPortalFrame;
import com.ljx.views.StuLoginFrame;

public class StuLoginFrame extends JFrame {

//	protected static final String Str = null;
	private StuLoginPanel loginPanel;
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
					StuLoginFrame frame = new StuLoginFrame("ѧ����½");
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
	public StuLoginFrame(String title) {
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
		StuLoginPanel loginPanel = new StuLoginPanel(); 
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
	private class StuLoginPanel extends JPanel { //�����½����Panel�еĿؼ�Ԫ�ء� 
		JLabel picLabel; 
		//�����½����Panel�еĿؼ�Ԫ�ء� 
		JLabel idLabel, pwdLabel; 
		JTextField stuIdTextField; 
		JPasswordField pwdField; 
		JButton okButton,clearButton; 
		JPanel buttonPanel,infoPanel;
		//��½����panel���죬�����пؼ�Ԫ��װ�������� 
		public StuLoginPanel() { //�������пؼ����� 
			picLabel = new JLabel(); 
			idLabel = new JLabel("ѧ��ID��"); //����������Ϊ��ǩ��ʾ�ı� 
			pwdLabel = new JLabel("�� �룺"); //����������Ϊ��ǩ��ʾ�ı� 
			stuIdTextField = new JTextField(20); 
			pwdField = new JPasswordField(20); 
			okButton = new JButton("��½"); //����������Ϊ��ť��ʾ�ı� 
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String stuName = null;
					StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
					if ((stuName = stuEntity.login2(
							Integer.parseInt(stuIdTextField.getText().trim()),
							new String(pwdField.getPassword()))) != null) {
						StuFuncPortalFrame frame = new StuFuncPortalFrame(
								"��ӭ " + stuName +" �ɹ���¼!", 
								Integer.parseInt(stuIdTextField.getText().trim()));
						frame.parentFrame = StuLoginFrame.this;
						StuLoginFrame.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "�û������������",
								"��¼ʧ��", JOptionPane.ERROR_MESSAGE);
						pwdField.setText("");
					}
//					String tchName = null;
//					TeacherEntityDAO tchEntity = new TeacherEntityDAOImpl();
//					if ((tchName = tchEntity.login(Integer.parseInt(tchIdTextField.getText().trim()),new String(pwdField.getPassword()))) != null) {
//						TchFuncPortalFrame frame = new TchFuncPortalFrame(
//								"��ӭ" + tchName +" �ɹ���½!", 
//								Integer.parseInt(tchIdTextField.getText().trim()));
//						frame.parentFrame = TchLoginFrame.this;				
//						TchLoginFrame.this.setVisible(false);
//					} else {
//						JOptionPane.showMessageDialog(null, "�û������������",
//								"��¼ʧ��", JOptionPane.ERROR_MESSAGE);
//						pwdField.setText("");
//					}
				}
			});
			clearButton = new JButton("�����Ϣ"); 
			buttonPanel = new JPanel(); 
			infoPanel = new JPanel();
		//����StuLoginPanel����ΪBorderLayout�� 
			setLayout(new BorderLayout());
		//�������pic��login��Ϣ�ؼ������������ //������infoPanel���ֹ�����ΪFlowLayout�� 
			infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
			picLabel.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT/2);
		
		
			ImageIcon image = new ImageIcon(this.getClass().getResource("/images/bg1.jpg")); 
			image.setImage(image.getImage().getScaledInstance( picLabel.getWidth(),picLabel.getHeight(), Image.SCALE_DEFAULT)); 
			picLabel.setIcon(image); 
			infoPanel.add(picLabel); 
			infoPanel.add(idLabel); 
			infoPanel.add(stuIdTextField); 
			infoPanel.add(pwdLabel); 
			infoPanel.add(pwdField); 
			add(infoPanel,BorderLayout.CENTER);
		//������Ӱ�ť�ؼ������������buttonPanel���ֹ�����Ĭ�������֣� //����buttonPanel�ؼ���ΪStuLoginPanel�ĵ׶������ 
			buttonPanel.add(okButton); 
			buttonPanel.add(clearButton); 
			add(buttonPanel,BorderLayout.SOUTH);
			}
		}

}


