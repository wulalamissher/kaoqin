package com.ljx.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.ljx.bean.ClassEntity;
import com.ljx.bean.StudentEntity;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.util.Communal;
import com.ljx.util.WindowsHandler;

public class TchQueryStuInfoFrame extends JFrame {
	JFrame parentFrame;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 380;

	public TchQueryStuInfoFrame(String title) {
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
		QueryStuInfoPanel dbInfoPanel = new QueryStuInfoPanel();
		getContentPane().add(dbInfoPanel);

		// ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class QueryStuInfoPanel extends JPanel {
		// ��������Panel�еĿؼ�Ԫ�ء�
		JLabel queryMethodLabel, queryKeyWordsLabel;
		JComboBox<String> queryCombo;
		JTextField queryKeyWordsTextField;
		JButton returnButton, queryButton, reQueryButton;
		JPanel queryPanel, queryMethodPanel, queryKeyPanel, queryResultPanel, buttonPanel;
		JScrollPane scrollPanel;
		JTable dbInfoTable;
		DefaultTableModel tableModel; 
		TableColumnModel columnModel;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public QueryStuInfoPanel() {

			String[] columnName = { "����", "ѧ��", "����ѧԺ","רҵ" };
			Object data[][] = { { "����", "1310001", "�����ѧԺ","�������" },
					{ "����", "1310002", "���ѧԺ" ,"Ӣ��+���"},
					{ "����", "1310003", "ͨ��ѧԺ","ͨ�����" } };			
			buttonPanel = new JPanel();
			queryResultPanel = new JPanel();
			queryMethodPanel = new JPanel();
			queryKeyPanel = new JPanel();
			queryPanel = new JPanel();
			
			setLayout(new BorderLayout());
			
			queryMethodLabel = new JLabel("��ѡ���ѯ��ʽ      ");			
			queryKeyWordsLabel = new JLabel("�������ѯ�ؼ��� ");
			queryCombo = new JComboBox<String>();		
			queryCombo.addItem("��������ѯ");
			queryCombo.addItem("����ѧ���ѯ");
			queryCombo.addItem("��רҵ��ѯ");	
			queryCombo.addItem("��ѧ�Ų�ѯ");	
			queryCombo.setPreferredSize(new Dimension(140,25));
			queryKeyWordsTextField = new JTextField(20);
			queryButton = new JButton("��ѯ");
						
			
			//queryMethodPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			queryMethodPanel.add(queryMethodLabel);
			queryMethodPanel.add(queryCombo);
			
			//queryKeyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			queryKeyPanel.add(queryKeyWordsLabel);
			queryKeyPanel.add(queryKeyWordsTextField);
			queryKeyPanel.add(queryButton);
            
			//create a TableModel
			tableModel = new DefaultTableModel(data,columnName);
			dbInfoTable = new JTable(tableModel);
			columnModel = dbInfoTable.getColumnModel();
			dbInfoTable.setRowHeight(20);
			dbInfoTable.setEnabled(false);
			//dbInfoTable.setAutoCreateColumnsFromModel(true);
			scrollPanel = new JScrollPane(dbInfoTable,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 180)); 
			//queryBoder = BorderFactory.createEtchedBorder();
			queryResultPanel.add(scrollPanel);
		
			queryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			queryPanel.add(queryMethodPanel);
			queryPanel.add(queryKeyPanel);
			queryPanel.add(queryResultPanel);
			add(queryPanel, BorderLayout.CENTER);
			

			returnButton = new JButton("�����ϼ��˵�");
			reQueryButton = new JButton("���¿�ʼ��ѯ");
			buttonPanel.add(reQueryButton);
			buttonPanel.add(returnButton);			
			add(buttonPanel, BorderLayout.SOUTH);

			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();
			// �¼�Դע���¼�������
			reQueryButton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
			queryButton.addActionListener(buttonAction);
		}		
		
		private void clearRequeryResultAndRepaint(String[] columnname, Object[][] rowdata){
			
			while(columnModel.getColumnCount() > 0)
			{
				columnModel.removeColumn(columnModel.getColumn(0));		
				tableModel.setColumnCount(columnModel.getColumnCount());
			};
			while(tableModel.getRowCount() > 0)
			{
				tableModel.removeRow(0);							
			};	
			
			for(int i = 0; i < columnname.length; i++)
			{
				tableModel.addColumn(columnname[i]);
			}
			
			for(int i = 0; i < rowdata.length; i++)
			{
				tableModel.addRow(rowdata[i]);
			}
		}
		

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(returnButton))
				{
					TchQueryStuInfoFrame.this.dispose();	
					parentFrame.setVisible(true);
				}
				else if(e.getSource().equals(reQueryButton))
				{
					queryCombo.setSelectedItem(queryCombo.getItemAt(0));
					queryKeyWordsTextField.setText("");
				}
				else
				{
					if (queryKeyWordsTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, 
								"��ѯ�ؼ��ֲ��ܹ�Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);						
					}
					else{
						String queryMethod = queryCombo.getItemAt(queryCombo.getSelectedIndex());
						StudentEntity student;
						StudentEntityDAO stuEntity = new StudentEntityDAOImpl();
						StudentEntity[] queryResult;
						
						if(queryMethod.equals("��ѧ�Ų�ѯ")){							
							if(Communal.isInteger(queryKeyWordsTextField.getText().trim()) == false)
							{
								JOptionPane.showMessageDialog(null, "��Чѧ��ID��ֻ��Ϊ������ʽ��",
										"����", JOptionPane.ERROR_MESSAGE);
								queryKeyWordsTextField.setText("");
							}else if ((student = stuEntity.queryStudentEntityById(Integer.parseInt(queryKeyWordsTextField.getText().trim()))) != null) {
									String[] column = {"����", "����ѧԺ", "����רҵ", "��������" };							
									Object[][] rowData = new Object[1][4];
									rowData[0][0] = new String(student.getStudent_name());
									rowData[0][1] = new String(student.getStudent_colleage());
									rowData[0][2] = new String(student.getStudent_major());	
									rowData[0][3] = new String(student.getStudent_email());	
									clearRequeryResultAndRepaint(column, rowData);	
							} else{
									JOptionPane.showMessageDialog(null, 
											"���ݿ���û����Ҫ��ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
							}					
						}else if(queryMethod.equals("��������ѯ")){
							if ((queryResult = stuEntity.queryStudentEntityByName(queryKeyWordsTextField.getText().trim())) != null 
									&& queryResult.length > 0) {
								String[] column = {"ѧ��", "����ѧԺ", "����רҵ", "��������" };	
								StudentEntity studentTemp = new StudentEntity();
								Object[][] rowData = new Object[queryResult.length][4];
								for(int i = 0; i < queryResult.length; i++){
									studentTemp = queryResult[i];
									rowData[i][0] = new Integer(studentTemp.getStudent_Id());
									rowData[i][1] = new String(studentTemp.getStudent_colleage());
									rowData[i][2] = new String(studentTemp.getStudent_major());	
									rowData[i][3] = new String(studentTemp.getStudent_email());				
								}
								clearRequeryResultAndRepaint(column, rowData);
							} else{
								JOptionPane.showMessageDialog(null, 
										"���ݿ���û����Ҫ��ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
							}
						}else if(queryMethod.equals("��רҵ��ѯ")){
							if ((queryResult = stuEntity.queryStudentEntityByMajor(queryKeyWordsTextField.getText().trim())) != null 
									&& queryResult.length > 0) {
								String[] column = {"ѧ��", "����", "����ѧԺ", "��������" };	
								StudentEntity studentTemp = new StudentEntity();
								Object[][] rowData = new Object[queryResult.length][4];
								for(int i = 0; i < queryResult.length; i++){
									studentTemp = queryResult[i];
									rowData[i][0] = new Integer(studentTemp.getStudent_Id()) ;
									rowData[i][1] = new String(studentTemp.getStudent_name());
									rowData[i][2] = new String(studentTemp.getStudent_colleage());
									rowData[i][3] = new String(studentTemp.getStudent_email());				
								}
								clearRequeryResultAndRepaint(column, rowData);
							} else{
								JOptionPane.showMessageDialog(null, 
										"���ݿ���û����Ҫ��ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
							}
							
						}else{
							if(Communal.isInteger(queryKeyWordsTextField.getText().trim()) == false)
							{
								JOptionPane.showMessageDialog(null, "��Ч��ѧ��ID��ֻ��Ϊ������ʽ��",
										"����", JOptionPane.ERROR_MESSAGE);
								queryKeyWordsTextField.setText("");
							}
							else if ((queryResult = stuEntity.queryStudentEntityByClass(Integer.parseInt(queryKeyWordsTextField.getText().trim()))) != null 
									&& queryResult.length > 0) {
								String[] column = {"ѧ��", "����", "����ѧԺ", "����רҵ", "��������" };	
								StudentEntity studentTemp = new StudentEntity();
								Object[][] rowData = new Object[queryResult.length][5];
								for(int i = 0; i < queryResult.length; i++){
									studentTemp = queryResult[i];
									rowData[i][0] = new Integer(studentTemp.getStudent_Id()) ;
									rowData[i][1] = new String(studentTemp.getStudent_name());
									rowData[i][2] = new String(studentTemp.getStudent_colleage());
									rowData[i][3] = new String(studentTemp.getStudent_major());	
									rowData[i][4] = new String(studentTemp.getStudent_email());				
								}
								clearRequeryResultAndRepaint(column, rowData);
							} else{
								JOptionPane.showMessageDialog(null, 
										"���ݿ���û����Ҫ��ѯ����Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);						
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
				new TchQueryStuInfoFrame("�鿴ѧ����Ϣ");
			}
		});
	}
}
