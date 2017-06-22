package com.ljx.dao.impl;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;

import com.ljx.bean.AdministratorEntity;
import com.ljx.bean.TeacherEntity;
import com.ljx.dao.AdministratorEntityDAO;
import database.DBConnection;
public class AdministratorEntityDAOImpl implements AdministratorEntityDAO {
	public String login(int id, String pwd) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM admininfo WHERE admin_id = " + id
				+ " AND admin_login_pwd = " + "'" + pwd + "'";
		String admName = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询

			if (rs.next()) {
				admName = rs.getString("admin_name");
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admName;
	}
	
	// 修改用户密码
		public int updateAdministratorPwd(int id, String oldPwd, String newPwd) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String querySQL = "SELECT * FROM admininfo WHERE admin_id = " + id
					+ " AND admin_login_pwd = " + "'" + oldPwd + "'";
			String updateSQL = "UPDATE admininfo SET admin_login_pwd = " + "'"
					+ newPwd + "'" + " WHERE admin_id = " + id;
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
		public int updateAdministratorEmail(int id, String email) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String updateSQL = "UPDATE admininfo SET admin_email = " + "'"
					+ email + "'" + " WHERE admin_id = " + id;
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
		
		// 更新管理员信息
		public int updateAdministratorEntity(AdministratorEntity admin) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String updateSQL = "UPDATE admininfo SET admin_name = " + "'" + admin.getAdmin_name() + "'" 
					+ ", admin_email = " + "'" + admin.getAdmin_email() + "'" 
					+ " WHERE admin_id = " + admin.getAdmin_id();
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
	
	// 获取管理员编号的管理员信息
		public AdministratorEntity queryAdministratorEntityById(int id) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String querySQL = "SELECT * FROM admininfo WHERE admin_id = " + id;
			AdministratorEntity admin = null;
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询

				if (rs.next()) {
					admin = new AdministratorEntity();
					admin.setAdmin_id(rs.getInt("admin_id"));
					admin.setAdmin_name(rs.getString("admin_name"));
					admin.setAdmin_email(rs.getString("admin_email"));
				}
				DBConnection.close(rs); // 关闭结果集
				DBConnection.close(stmt); // 关闭预处理对象
				DBConnection.close(conn); // 关闭连接对象
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return admin;
		}
		// 获取指定教师编号的教师信息
		public TeacherEntity queryTeacherEntityById(int id) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String querySQL = "SELECT * FROM teacherinfo WHERE teacher_id = " + id;
			TeacherEntity teacher = null;
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询

				if (rs.next()) {
					teacher = new TeacherEntity();
					teacher.setTeacher_Id(rs.getInt("teacher_id"));
					teacher.setTeacher_name(rs.getString("teacher_name"));
					teacher.setTeacher_colleage(rs.getString("college_name"));
					teacher.setTeacher_faculty(rs.getString("faculty_name"));
					teacher.setTeacher_email(rs.getString("teacher_email"));
				}
				DBConnection.close(rs); // 关闭结果集
				DBConnection.close(stmt); // 关闭预处理对象
				DBConnection.close(conn); // 关闭连接对象
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return teacher;
		}
		// 获取所有教师信息
		public TeacherEntity[] queryTeacherEntityAll() {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String querySQL = "SELECT * FROM teacherinfo";
			TeacherEntity[] teacher = null;
			try {
				// 建立可滚动的结果集
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
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
				DBConnection.close(rs); // 关闭结果集
				DBConnection.close(stmt); // 关闭预处理对象
				DBConnection.close(conn); // 关闭连接对象
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return teacher;
		}
		// 更新教师信息
		public int updateTeacherEntity(TeacherEntity teacher) {
			Connection conn = DBConnection.getConnection(); // 获得连接对象
			String updateSQL = "UPDATE teacherinfo SET teacher_name = " + "'" + teacher.getTeacher_name() + "'" 
					+ ", college_name = " + "'" + teacher.getTeacher_colleage() + "'" 
					+ ", faculty_name = " + "'" + teacher.getTeacher_faculty() + "'" 
					+ ", teacher_email = " + "'" + teacher.getTeacher_email() + "'" 
					+ " WHERE teacher_id = " + teacher.getTeacher_Id();
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
