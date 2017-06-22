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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import com.ljx.bean.AttendanceEntity;
import com.ljx.dao.AttendanceEntityDAO;
import com.ljx.dao.StudentEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import com.ljx.dao.impl.AttendanceEntityDAOImpl;
import com.ljx.dao.impl.StudentEntityDAOImpl;
import com.ljx.dao.impl.TeacherEntityDAOImpl;
import com.ljx.util.WindowsHandler;

public class TchQryMdyAttInfoFrame extends JFrame {
	JFrame parentFrame;
	private AttendanceEntity[] attReuryResult;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 380;
	DefaultTableModel tableModel; 
	TableColumnModel columnModel;

	public TchQryMdyAttInfoFrame(String title, AttendanceEntity[] attRequryRes) {
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
		attReuryResult = attRequryRes;
		DBInfoPanel dbInfoPanel = new DBInfoPanel();
		getContentPane().add(dbInfoPanel);

		// ע�ᴰ�ڼ�����
		addWindowListener(new WindowsHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private class DBInfoPanel extends JPanel {
		// �����¼����Panel�еĿؼ�Ԫ�ء�
		JButton modifyBotton, returnButton;
		JPanel queryResultPanel, buttonPanel;
		JScrollPane scrollPanel;
		JTable dbInfoTable;

		// ��½����panel���죬�����пؼ�Ԫ��װ��������
		public DBInfoPanel() {
			String[] columnName = { "��ѧ���","ѧ��ID", "ѧ������", "�γ�����", "����ʱ��", "���ڼ�¼" };
			Object data[][] = { { "1310001","2014213870", "����", "���ݿ�", "2014-4-5", "����" },
					{ "1310001","2014213872", "����", "���ݿ�", "2014-4-5", "�ٵ�" },
					{ "1310001","2014213873", "����", "���ݿ�", "2014-4-5", "����" } };

			if(attReuryResult != null && attReuryResult.length > 0){
				String[] columnName1 = { "��ѧ���", "�γ�����", "ѧ��ID", "����", "����ʱ��", "���ڼ�¼" };
				AttendanceEntity attTemp = new AttendanceEntity();
				Object[][] data1 = new Object[attReuryResult.length][6];
				for(int i = 0; i < attReuryResult.length; i++){
					attTemp = (AttendanceEntity)attReuryResult[i];
					data1[i][0] = new Integer(attTemp.getClass_Id()) ;
					data1[i][1] = new String(attTemp.getCourse_name());
					data1[i][2] = new Integer(attTemp.getStudent_Id());
					data1[i][3] = new String(attTemp.getStudent_name());
					data1[i][4] = new String(attTemp.getAttendance_date());		
					data1[i][5] = new String(attTemp.getAttendance_status());	
				}
				columnName = columnName1;
				data = data1;
			}			
			
			//create a TableModel
			tableModel = new DefaultTableModel(data,columnName);
			dbInfoTable = new JTable(tableModel){
				public boolean isCellEditable(int row, int column) {
					if(column == 5)
					{
						return true;
					}
					return false;
				}
			};
			dbInfoTable.setRowHeight(20);
			columnModel = dbInfoTable.getColumnModel();
			dbInfoTable.setAutoCreateColumnsFromModel(true);
			dbInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dbInfoTable.setEnabled(false);
			scrollPanel = new JScrollPane(dbInfoTable,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH - 20, DEFAULT_HEIGHT - 90));
			// queryBoder = BorderFactory.createEtchedBorder();

			buttonPanel = new JPanel();
			queryResultPanel = new JPanel();

			setLayout(new BorderLayout(10, 10));
			queryResultPanel.add(scrollPanel);
			add(queryResultPanel, BorderLayout.CENTER);

			modifyBotton = new JButton("�޸Ŀ��ڼ�¼");
			modifyBotton.setActionCommand("�޸Ŀ��ڼ�¼");
			returnButton = new JButton("�����ϼ��˵�");
			buttonPanel.add(modifyBotton);
			buttonPanel.add(returnButton);
			add(buttonPanel, BorderLayout.SOUTH);

			// �����¼�����������
			ButtonAction buttonAction = new ButtonAction();
			// �¼�Դע���¼�������
			modifyBotton.addActionListener(buttonAction);
			returnButton.addActionListener(buttonAction);
		}

		// �����¼��������࣬ʵ���¼��������ӿ��е�actionPerformed����������һ���û�����Ĳ�����
		private class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource().equals(returnButton)) {
					TchQryMdyAttInfoFrame.this.dispose();
					parentFrame.setVisible(true);
				} else {
					if (e.getActionCommand().equals("�޸Ŀ��ڼ�¼")) {
						setEditbalColumn();
						modifyBotton.setText("�ύ�޸ļ�¼");
						modifyBotton.setActionCommand("�ύ�޸ļ�¼");
						pack();
					} else {						
                        int updateTimes = 0;
                        Boolean updateFailedFlag = false;
						for(int i = 0; i < attReuryResult.length; i++)
                        {
                        	if(!attReuryResult[i].getAttendance_status().equals(dbInfoTable.getValueAt(i, 5).toString()))
                        	{
                        		String tempAttStatus = attReuryResult[i].getAttendance_status();
                        		attReuryResult[i].setAttendance_status(dbInfoTable.getValueAt(i, 5).toString());
                        		AttendanceEntityDAO attEntity = new AttendanceEntityDAOImpl();
                        		int returnVal = attEntity.updateAttendanceEntity(attReuryResult[i]);                        		
                        		if(returnVal != 1){
                        			JOptionPane.showMessageDialog(null, 
        									"�޷����¿��ڼ�¼��Ϣ����͹���Ա��ϵ��", "����", JOptionPane.ERROR_MESSAGE);	
                        			attReuryResult[i].setAttendance_status(tempAttStatus);
                        			updateFailedFlag = true;
                        			break;
                        		}                        		
                        		updateTimes++;
                        	}                        	
                        }
						
						if(updateFailedFlag == false)
						{
							if(updateTimes > 0){
								JOptionPane.showMessageDialog(null, 
										"���ڼ�¼�޸ĳɹ���", "��ϲ", JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, 
										"��δʵ�ʸ���ѧ������״̬�����β���δִ�У�", "ע��", JOptionPane.INFORMATION_MESSAGE);
							}							
						}						
					}
				}
			}
		}
		
		
		private void setEditbalColumn() {
			//columnModel.removeColumn(columnModel.getColumn(0));
			JComboBox<String> attStatusCombo = new JComboBox<String>();
			attStatusCombo.addItem("����");
			attStatusCombo.addItem("�ٵ�");
			attStatusCombo.addItem("����");	
			attStatusCombo.addItem("���");	
			columnModel.getColumn(5).setCellEditor(new DefaultCellEditor(attStatusCombo));
			dbInfoTable.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����swing����������¼������̣߳�event dispatch thread���������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TchQryMdyAttInfoFrame("�鿴ѧ��������Ϣ", null);
			}
		});
	}
}
