package com.ljx.dao.impl;

//import java.awt.Image;
//import java.awt.image.RenderedImage;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.sql.Blob;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;

import com.ljx.bean.TeacherEntity;
import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class TeacherEntityDAOImpl implements TeacherEntityDAO {
	// ��ɵ�½�ļ�飬�����½�ɹ����ظý�ʦ ��������Ϣ��
	public String login(int id, String pwd) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT * FROM teacherinfo WHERE teacher_id = " + id
				+ " AND teacher_login_pwd = " + "'" + pwd + "'";
		String stuName = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ

			if (rs.next()) {
				stuName = rs.getString("teacher_name");
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuName;
	}

	// ��ȡָ����ʦ��ŵĽ�ʦ��Ϣ
	public TeacherEntity queryTeacherEntityById(int id) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT * FROM teacherinfo WHERE teacher_id = " + id;
		TeacherEntity teacher = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ

			if (rs.next()) {
				teacher = new TeacherEntity();
				teacher.setTeacher_Id(rs.getInt("teacher_id"));
				teacher.setTeacher_name(rs.getString("teacher_name"));
				teacher.setTeacher_colleage(rs.getString("college_name"));
				teacher.setTeacher_faculty(rs.getString("faculty_name"));
				teacher.setTeacher_email(rs.getString("teacher_email"));
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}

	// ��ȡ���н�ʦ��Ϣ
	public TeacherEntity[] queryTeacherEntityAll() {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT * FROM teacherinfo";
		TeacherEntity[] teacher = null;
		try {
			// �����ɹ����Ľ����
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
			rs.last();
			teacher = new TeacherEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				teacher[i] = new TeacherEntity();
				teacher[i].setTeacher_Id(rs.getInt("teacher_id"));
				teacher[i].setTeacher_name(rs.getString("teacher_name"));
				teacher[i].setTeacher_colleage(rs.getString("college_name"));
				teacher[i].setTeacher_faculty(rs.getString("faculty_name"));
				teacher[i].setTeacher_email(rs.getString("teacher_email"));
				i++;
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacher;
	}

	// �޸��û�����
	public int updateTeacherPwd(int id, String oldPwd, String newPwd) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String querySQL = "SELECT * FROM teacherinfo WHERE teacher_id = " + id
				+ " AND teacher_login_pwd = " + "'" + oldPwd + "'";
		String updateSQL = "UPDATE teacherinfo SET teacher_login_pwd = " + "'"
				+ newPwd + "'" + " WHERE teacher_id = " + id;
		int returnVal = -1;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // ���������, ִ�в�ѯ
			if (rs.next()) {
				returnVal = stmt.executeUpdate(updateSQL); // ���������, ִ�и���
			}
			DBConnection.close(rs); // �رս����
			DBConnection.close(stmt); // �ر�Ԥ�������
			DBConnection.close(conn); // �ر����Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnVal;
	}

	// �޸��û������ַ
	public int updateTeacherEmail(int id, String email) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
		String updateSQL = "UPDATE teacherinfo SET teacher_email = " + "'"
				+ email + "'" + " WHERE teacher_id = " + id;
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

	// ���½�ʦ��Ϣ
	public int updateTeacherEntity(TeacherEntity teacher) {
		Connection conn = DBConnection.getConnection(); // ������Ӷ���
/*		String updateSQL = "UPDATE teacherinfo SET teacher_name = ?, college_name = ?, faculty_name = ?, teacher_email = ? "
				+ " WHERE teacher_id = " + student.getTeacher_Id();*/
		String updateSQL = "UPDATE teacherinfo SET teacher_name = " + "'" + teacher.getTeacher_name() + "'" 
				+ ", college_name = " + "'" + teacher.getTeacher_colleage() + "'" 
				+ ", faculty_name = " + "'" + teacher.getTeacher_faculty() + "'" 
				+ ", teacher_email = " + "'" + teacher.getTeacher_email() + "'" 
				+ " WHERE teacher_id = " + teacher.getTeacher_Id();
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
