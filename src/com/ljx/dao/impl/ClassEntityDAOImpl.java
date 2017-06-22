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

import com.ljx.bean.ClassEntity;
//import com.ljx.bean.TeacherEntity;
import com.ljx.dao.ClassEntityDAO;
//import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class ClassEntityDAOImpl implements ClassEntityDAO {
	
	// 获取所有教师信息
	public ClassEntity[] queryClassEntityByStudentId(int studentId) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT classinfo.class_id, classinfo.course_name, classinfo.class_time, teacherinfo.teacher_name "
				+ "FROM classinfo, teacherinfo, classStudentinfo "
				+ "WHERE classinfo.teacher_id = teacherinfo.teacher_id AND classinfo.class_id = classStudentinfo.class_id "
				+ "AND classStudentinfo.student_id = " + studentId;
		ClassEntity[] classInfo = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			classInfo = new ClassEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				classInfo[i] = new ClassEntity();
				classInfo[i].setClass_Id(rs.getInt(1));
				classInfo[i].setCourse_name(rs.getString(2));
				classInfo[i].setClass_time(rs.getString(3));
				classInfo[i].setTeacher_name(rs.getString(4));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return classInfo;
	}	
	
	
	public ClassEntity[] queryClassEntityByTeacherId(int teacherId){
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT class_id, course_name FROM classinfo "
				+ " WHERE teacher_id = " + teacherId;
		ClassEntity[] classInfo = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			classInfo = new ClassEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				classInfo[i] = new ClassEntity();
				classInfo[i].setClass_Id(rs.getInt(1));
				classInfo[i].setCourse_name(rs.getString(2));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return classInfo;
	}
}
