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
import com.ljx.bean.CollegeEntity;
//import com.ljx.bean.TeacherEntity;
//import com.ljx.dao.ClassEntityDAO;
import com.ljx.dao.CollegeEntityDAO;
//import com.ljx.dao.TeacherEntityDAO;
import database.DBConnection;

public class CollegeEntityDAOImpl implements CollegeEntityDAO {

	// 获取所有教师信息
	public CollegeEntity[] queryCollegeEntityAll() {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String querySQL = "SELECT * FROM collegeinfo";
		CollegeEntity[] college = null;
		try {
			// 建立可滚动的结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(querySQL); // 声明结果集, 执行查询
			rs.last();
			college = new CollegeEntity[rs.getRow()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				college[i] = new CollegeEntity();
				college[i].setCollege_name(rs.getString("college_name"));
				college[i].setCollege_addr(rs.getString("college_address"));
				college[i].setCollege_contact(rs.getString("college_contact"));
				college[i].setCollege_tel(rs.getString("college_tel"));
				i++;
			}
			DBConnection.close(rs); // 关闭结果集
			DBConnection.close(stmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return college;
	}
}
