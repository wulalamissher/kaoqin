package com.ljx.dao.impl;

//import java.awt.Image;
//import java.awt.image.RenderedImage;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;

import com.ljx.bean.StudentEntity;
//import com.ljx.bean.TeacherEntity;
import com.ljx.dao.StudentEntityDAO;
import database.DBConnection;

public class StudentEntityDAOImpl implements StudentEntityDAO {
	// 完成登陆的检查，如果登陆成功返回该学生 的姓名信息。
	public String login(int id, String pwd) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_id = ? AND student_login_pwd = ?";
		String stuName = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(querySQL); // 获得预处理对象并赋值
			pstmt.setInt(1, id); // 设置第一个参数
			pstmt.setString(2, pwd); // 设置第二个参数
			ResultSet rs = pstmt.executeQuery(); // 声明结果集, 执行查询

			if (rs.next()) {
				stuName = rs.getString("student_name");
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuName;
	}

	public String login2(int id, String pwd) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_id = " + id
				+ " AND student_login_pwd = " + "'" + pwd + "'";
		String stuName = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询

			if (rs.next()) {
				stuName = rs.getString("student_name");
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuName;
	}

	public StudentEntity queryStudentEntityById(int id) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_id = " + id;
		StudentEntity student = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询

			if (rs.next()) {
				student = new StudentEntity();
				student.setStudent_Id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_colleage(rs.getString("student_college"));
				student.setStudent_major(rs.getString("student_major"));
				student.setStudent_email(rs.getString("student_email"));
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public StudentEntity[] queryStudentEntityByName(String name) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_name = "
				+ "'" + name + "'";
		StudentEntity[] student = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			student = new StudentEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				student[i] = new StudentEntity();
				student[i].setStudent_Id(rs.getInt("student_id"));
				student[i].setStudent_name(rs.getString("student_name"));
				student[i].setStudent_colleage(rs.getString("student_college"));
				student[i].setStudent_major(rs.getString("student_major"));
				student[i].setStudent_email(rs.getString("student_email"));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public StudentEntity[] queryStudentEntityByMajor(String major) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_major = "
				+ "'" + major + "'";
		StudentEntity[] student = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			student = new StudentEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				student[i] = new StudentEntity();
				student[i].setStudent_Id(rs.getInt("student_id"));
				student[i].setStudent_name(rs.getString("student_name"));
				student[i].setStudent_colleage(rs.getString("student_college"));
				student[i].setStudent_major(rs.getString("student_major"));
				student[i].setStudent_email(rs.getString("student_email"));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public StudentEntity[] queryStudentEntityByClass(int classId) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT studentinfo.student_id, student_name, student_college, student_major, student_email " 
				+ " FROM studentinfo, classstudentinfo WHERE classstudentinfo.class_id = " + classId 
				+ " AND classstudentinfo.student_id = studentinfo.student_id";
		StudentEntity[] student = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			student = new StudentEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				student[i] = new StudentEntity();
				student[i].setStudent_Id(rs.getInt("student_id"));
				student[i].setStudent_name(rs.getString("student_name"));
				student[i].setStudent_colleage(rs.getString("student_college"));
				student[i].setStudent_major(rs.getString("student_major"));
				student[i].setStudent_email(rs.getString("student_email"));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}
		

	// 修改用户密码
	public int updateStudentPwd(int id, String oldPwd, String newPwd) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM studentinfo WHERE student_id = " + id
				+ " AND student_login_pwd = " + "'" + oldPwd + "'";
		String updateSQL = "UPDATE studentinfo SET student_login_pwd = " + "'"
				+ newPwd + "'" + " WHERE student_id = " + id;
		int returnVal = -1;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			if (rs.next()) {
				returnVal = stmt.executeUpdate(updateSQL); // 声明结果集, 执行更新
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnVal;
	}

	// 修改用户邮箱地址
	public int updateStudentEmail(int id, String email) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String updateSQL = "UPDATE studentinfo SET student_email = " + "'"
				+ email + "'" + " WHERE student_id = " + id;
		int returnVal = 0;
		try {
			Statement stmt = conn.createStatement();
			returnVal = stmt.executeUpdate(updateSQL); // 执行更新
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnVal;
	}

}
