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

//import com.ljx.bean.ClassEntity;
//import com.ljx.bean.CollegeEntity;
import com.ljx.bean.FacultyEntity;
//import com.ljx.bean.TeacherEntity;
//import com.ljx.dao.ClassEntityDAO;
//import com.ljx.dao.CollegeEntityDAO;
import com.ljx.dao.FacultyEntityDAO;
//import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class FacultyEntityDAOImpl implements FacultyEntityDAO {

	// 获取所有教师信息
	public FacultyEntity[] queryFacultyEntityAll() {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM facultyinfo";
		FacultyEntity[] faculty = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			faculty = new FacultyEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				faculty[i] = new FacultyEntity();
				faculty[i].setFaculty_name(rs.getString("faculty_name"));
				faculty[i].setFaculty_addr(rs.getString("faculty_address"));
				faculty[i].setFaculty_contact(rs.getString("faculty_contact"));
				faculty[i].setFaculty_tel(rs.getString("faculty_tel"));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return faculty;
	}
}
