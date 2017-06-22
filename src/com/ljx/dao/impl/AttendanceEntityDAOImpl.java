package com.ljx.dao.impl;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ljx.bean.AttendanceEntity;
import com.ljx.bean.ClassEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.dao.AttendanceEntityDAO;
import com.ljx.dao.ClassEntityDAO;
import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class AttendanceEntityDAOImpl implements AttendanceEntityDAO {
	// ����ѧ��������Ϣ��
	public int insertAttendanceEntity(AttendanceEntity attEntity) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String insertSQL1 = "INSERT INTO attendanceinfo(class_id, record_time) VALUES(?, ?)";
		String querySQL = "SELECT attendance_id FROM attendanceinfo WHERE class_id = ? AND record_time = ?";
		String insertSQL2 = "INSERT INTO attendancerecord(attendance_Id, student_id, attendance_status) VALUES(?, ?, ?)";
		int returnedVal = -1;
		try {
			// �����ɹ����Ľ����
			PreparedStatement pstmt = conn.prepareStatement(insertSQL1);
			pstmt.setInt(1, attEntity.getClass_Id());
			pstmt.setString(2, attEntity.getAttendance_date());
			returnedVal = pstmt.executeUpdate();
			ResultSet rs = null;
			if (returnedVal != 0) {
				pstmt = conn.prepareStatement(querySQL);
				pstmt.setInt(1, attEntity.getClass_Id());
				pstmt.setString(2, attEntity.getAttendance_date());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					pstmt = conn.prepareStatement(insertSQL2);
					pstmt.setInt(1, rs.getInt(1));
					pstmt.setInt(2, attEntity.getStudent_Id());
					pstmt.setString(3, attEntity.getAttendance_status());
					returnedVal = pstmt.executeUpdate();
				}
			}

			DBConnection.close(rs); // �رս����
			DBConnection.close(pstmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnedVal;
	}

	// ���ݽ�ʦȨ�޻�ȡ���п��ڼ�¼
	public AttendanceEntity[] queryAttendanceEntityByTeacherId(int teacherId) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT DISTINCT attendanceinfo.attendance_id, attendanceinfo.class_id,"
				+ " classinfo.course_name, attendancerecord.student_id, studentinfo.student_name,"
				+ " attendanceinfo.record_time, attendancerecord.attendance_status"
				+ " FROM attendanceinfo, classinfo, attendancerecord, studentinfo, classstudentinfo"
				+ " WHERE classinfo.teacher_id = " + teacherId
				+ " AND classinfo.class_id = attendanceinfo.class_id"
				+ " AND attendanceinfo.attendance_id = attendancerecord.attendance_id"
				+ " AND classstudentinfo.student_id = attendancerecord.student_id"
				+ " AND classstudentinfo.student_id = studentinfo.student_id";

		AttendanceEntity[] attRecord = null;
		try {
			// �����ɹ����Ľ����
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
			rs.last();
			attRecord = new AttendanceEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				attRecord[i] = new AttendanceEntity();
				attRecord[i].setAttendance_id(rs.getInt(1));
				attRecord[i].setClass_Id(rs.getInt(2));
				attRecord[i].setCourse_name(rs.getString(3));
				attRecord[i].setStudent_Id(Integer.parseInt(rs.getString(4)));
				attRecord[i].setStudent_name(rs.getString(5));
				attRecord[i].setAttendance_date(rs.getString(6));
				attRecord[i].setAttendance_status(rs.getString(7));
				i++;
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attRecord;
	}
	
	// ����ѧ��Ȩ�޻�ȡ���п��ڼ�¼
	public AttendanceEntity[] queryAttendanceEntityByStuIdAndCourse(int studentId, int course_id){
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT DISTINCT attendanceinfo.class_id, classinfo.course_name,"
				+ " attendancerecord.student_id, studentinfo.student_name,"
				+ " attendanceinfo.record_time, attendancerecord.attendance_status"
				+ " FROM attendanceinfo, classinfo, attendancerecord, studentinfo, classstudentinfo"
				+ " WHERE classinfo.course_id = " + course_id
				+ " AND classinfo.class_id = attendanceinfo.class_id"
				+ " AND attendanceinfo.attendance_id = attendancerecord.attendance_id"
				+ " AND classstudentinfo.student_id = attendancerecord.student_id"
				+ " AND classstudentinfo.student_id = studentinfo.student_id"
				+ " AND studentinfo.student_id = " + studentId;
	
		AttendanceEntity[] attRecord = null;
		try {
			// �����ɹ����Ľ����
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
			rs.last();
			attRecord = new AttendanceEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				attRecord[i] = new AttendanceEntity();
				attRecord[i].setClass_Id(rs.getInt(1));
				attRecord[i].setCourse_name(rs.getString(2));
				attRecord[i].setStudent_Id(rs.getInt(3));
				attRecord[i].setStudent_name(rs.getString(4));
				attRecord[i].setAttendance_date(rs.getString(5));
				attRecord[i].setAttendance_status(rs.getString(6));
				i++;
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attRecord;
		
	}	
	
	// ����ѧ��Ȩ�޻�ȡ���п��ڼ�¼
	public AttendanceEntity[] queryAttendanceEntityByTchIdAndCourse(int teacherId, int course_id){
			Connection conn = DBConnection.getConnection(); // ������Ӷ���
			String querySQL = "SELECT DISTINCT attendanceinfo.class_id, classinfo.course_name,"
					+ " attendancerecord.student_id, studentinfo.student_name,"
					+ " attendanceinfo.record_time, attendancerecord.attendance_status"
					+ " FROM attendanceinfo, classinfo, attendancerecord, studentinfo, classstudentinfo"
					+ " WHERE classinfo.course_id = " + course_id
					+ " AND classinfo.class_id = attendanceinfo.class_id"
					+ " AND classinfo.teacher_id = " + teacherId
					+ " AND attendanceinfo.attendance_id = attendancerecord.attendance_id"
					+ " AND classstudentinfo.student_id = attendancerecord.student_id"
					+ " AND classstudentinfo.student_id = studentinfo.student_id";
		
			AttendanceEntity[] attRecord = null;
			try {
				// �����ɹ����Ľ����
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
				rs.last();
				attRecord = new AttendanceEntity[rs.getRow()];
				int i = 0;
				rs.beforeFirst();
				while (rs.next()) {
					attRecord[i] = new AttendanceEntity();
					attRecord[i].setClass_Id(rs.getInt(1));
					attRecord[i].setCourse_name(rs.getString(2));
					attRecord[i].setStudent_Id(rs.getInt(3));
					attRecord[i].setStudent_name(rs.getString(4));
					attRecord[i].setAttendance_date(rs.getString(5));
					attRecord[i].setAttendance_status(rs.getString(6));
					i++;
				}
				DBConnection.close(rs); // �رս����
				DBConnection.close(stmt); // �ر�Ԥ�������
				DBConnection.close(conn); // �ر����Ӷ���
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return attRecord;
			
		}	
	

	// ����ĳһ�����ڼ�¼
	public int updateAttendanceEntity(AttendanceEntity attEntity) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String updateSQL = "UPDATE attendancerecord SET attendance_status = " 
				+ "'" + attEntity.getAttendance_status() + "'"
				+ " WHERE attendance_id = " + attEntity.getAttendance_id()
				+ " AND student_id = " + attEntity.getStudent_Id();
		int returnVal = 0;
		try {
			Statement stmt = conn.createStatement();
			returnVal = stmt.executeUpdate(updateSQL); // ִ�и���	
			
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}

}
